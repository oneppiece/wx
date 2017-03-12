package com.dzy.wx.media.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dzy.wx.global.StaticParam;
import com.dzy.wx.global.service.AccessTokenService;
import com.dzy.wx.media.entity.*;
import com.dzy.wx.media.repository.MediaRepository;
import com.dzy.wx.media.service.MediaService;
import com.dzy.wx.utils.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private StaticParam staticParam;

    @Override
    public List<Media> findPage() {
        return mediaRepository.findAll();
    }

    @Override
    public List<Media> findAll() {
        return mediaRepository.findAll();
    }

    @Override
    public Media getFromWxServer(MediaPage mediaPage) {
        ObjectMapper mapper = new ObjectMapper();
        String query = null;
        String url = staticParam.getUrlMediaMaterialGetCurrent().replace("ACCESS_TOKEN", accessTokenService.getAccessToken());
        try {
            query = mapper.writeValueAsString(mediaPage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = HttpUtils.httpRequest(url, HttpMethod.POST.toString(), query);
        JSONArray item = jsonObject.getJSONArray("item");
        int totalCount = jsonObject.getIntValue("total_count");
        int itemCount = jsonObject.getIntValue("item_count");
        Set<MediaItem> mediaItems = buildMediaItem(item);
        Media media = new Media(totalCount, itemCount, mediaItems);
        return media == null ? null : mediaRepository.save(media);
    }

    private Set<MediaItem> buildMediaItem(JSONArray item) {
        Set<MediaItem> mediaItems = new HashSet<>();
        for (int i = 0; i < item.size(); i++) {
            String mediaId = item.getJSONObject(i).getString("media_id");
            String updateTime = item.getJSONObject(i).getString("update_time");
            if (item.getJSONObject(i).get("content") == null)//其他类型
            {
                String name = item.getJSONObject(i).getString("name");
                String url = item.getJSONObject(i).getOrDefault("url", "").toString();
                mediaItems.add(new OtherMediaItem(mediaId, updateTime, name, url));
            } else {
                JSONObject content = item.getJSONObject(i).getJSONObject("content");
                JSONArray newsItem = content.getJSONArray("news_item");
                Set<NewsMediaItemContont> newsMediaItemContonts = new HashSet<>();
                for (int j = 0; j < newsItem.size(); j++) {
                    NewsMediaItemContont newsMediaItemContont = new NewsMediaItemContont();
                    newsMediaItemContont.setThumbMediaId(newsItem.getJSONObject(j).getString("thumb_media_id"));
                    newsMediaItemContont.setTitle(newsItem.getJSONObject(j).getString("title"));
                    newsMediaItemContont.setShowCoverPic(newsItem.getJSONObject(j).getIntValue("show_cover_pic") == 0 ? Boolean.FALSE : Boolean.TRUE);
                    newsMediaItemContont.setAuthor(newsItem.getJSONObject(j).getString("author"));
                    newsMediaItemContont.setDigest(newsItem.getJSONObject(j).getString("digest"));
                    newsMediaItemContont.setContent(newsItem.getJSONObject(j).getString("content"));
                    newsMediaItemContont.setUrl(newsItem.getJSONObject(j).getString("url"));
                    newsMediaItemContont.setContentSourceUrl(newsItem.getJSONObject(j).getString("content_source_url"));
                    newsMediaItemContonts.add(newsMediaItemContont);
                }
                mediaItems.add(new NewsMediaItem(mediaId, updateTime, newsMediaItemContonts));
            }
        }
        return mediaItems;
    }
}
