package com.dzy.wx.entity.message.res;

import com.dzy.wx.entity.StaticParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/27.
 * User:Administrator
 * Date:2016/12/27
 * Time:16:49
 */
@Component
public class RespFactory {
    private static final Logger LOGGER = Logger.getLogger(RespFactory.class);

    public RespMessage createResponse(Map<String, Object> reqModel) {
        String msgType = (String) reqModel.get("MsgType");
        switch (msgType) {
            case StaticParam.respMessageTypeText:
                return buildTextResp(reqModel);
            case StaticParam.respMessageTypeNews:
                return buildNewsResp(reqModel);
            case StaticParam.respMessageTypeMusic:
                return buildMusicResp(reqModel);
            case StaticParam.respMessageTypeImage:
                return buildImageResp(reqModel);
            case StaticParam.respMessageTypeVoice:
                return buildVoiceResp(reqModel);
            case StaticParam.respMessageTypeVideo:
                return buildVideoResp(reqModel);
            case StaticParam.respMessageTypeLocation:
                return buildLocationResp(reqModel);
            case StaticParam.respMessageTypeLink:
                return buildLinkResp(reqModel);
            case StaticParam.respMessageTypeEvent:
                return null;
            case StaticParam.respMessageTypeShortVideo:
                return buildShortVideoResp(reqModel);
            default:
                LOGGER.error("找不到合适的返回类型");
                reqModel.forEach((String key, Object val) -> {
                    System.out.printf("%s:%s%n", key, val);
                });
                return null;
        }
    }

    private RespMessage buildLinkResp(Map<String, Object> reqModel) {
        String title = (String) reqModel.get("Title");
        String description = (String) reqModel.get("Description");
        String url = (String) reqModel.get("Url");
        StringBuilder content = new StringBuilder();
        content.append("超链接信息:title:" + title).
                append("\n").
                append("description:" + description).
                append("\n").
                append("url:" + url).
                append("\n");
        reqModel.remove("Content");
        reqModel.put("Content", content.toString());
        return buildTextResp(reqModel);
    }

    private RespMessage buildShortVideoResp(Map<String, Object> reqModel) {
        return buildVideoResp(reqModel);
    }

    private RespMessage buildLocationResp(Map<String, Object> reqModel) {
        String locationX = (String) reqModel.get("Location_X");
        String locationY = (String) reqModel.get("Location_Y");
        String scale = (String) reqModel.get("Scale");
        String label = (String) reqModel.get("Label");
        StringBuilder locationContent = new StringBuilder();
        locationContent.append("地理位置维度:" + locationX)
                .append("\n")
                .append("地理位置经度:" + locationY)
                .append("\n")
                .append("地图缩放大小:" + scale)
                .append("\n")
                .append("地理位置信息:" + label);
        //reqModel.replace("Content",locationContent.toString());
        reqModel.remove("Content");
        reqModel.put("Content", locationContent.toString());
        return buildTextResp(reqModel);
    }

    private RespMessage buildVideoResp(Map<String, Object> reqModel) {
        String fromUserName = (String) reqModel.get("ToUserName");
        String toUserName = (String) reqModel.get("FromUserName");
        String mediaId = "U-RoPYanqYCPx454ccWBR-h0iR1eJpNicn7jQBX6Emc";
        Video video = new Video();
        video.setTitle("标题");
        video.setDescription("描述");
        video.setMediaId(mediaId);
        return new VideoResponse(toUserName, fromUserName, new Date().getTime(), StaticParam.respMessageTypeVideo, video);
    }

    private RespMessage buildVoiceResp(Map<String, Object> reqModel) {
        String fromUserName = (String) reqModel.get("ToUserName");
        String toUserName = (String) reqModel.get("FromUserName");
        String mediaId = (String) reqModel.get("MediaId");
        Voice voice = new Voice();
        voice.setMediaId(mediaId);
        Set<Voice> voices = new HashSet<>();
        voices.add(voice);
        return new VoiceResponse(toUserName, fromUserName, new Date().getTime(), StaticParam.respMessageTypeVoice, voices);
    }

    private RespMessage buildImageResp(Map<String, Object> reqModel) {
        String fromUserName = (String) reqModel.get("ToUserName");
        String toUserName = (String) reqModel.get("FromUserName");
        String mediaId = (String) reqModel.get("MediaId");
        Image image = new Image();
        image.setMediaId(mediaId);
        Set<Image> images = new HashSet<>();
        images.add(image);
        return new ImageResponse(toUserName, fromUserName, new Date().getTime(), StaticParam.respMessageTypeImage, images);
    }

    private RespMessage buildMusicResp(Map<String, Object> reqModel) {
        String toUserName = (String) reqModel.get("ToUserName");
        String fromUserName = (String) reqModel.get("FromUserName");
        String content = (String) reqModel.get("Content");
        return new TextResponse(toUserName, fromUserName, new Date().getTime(), StaticParam.respMessageTypeText, content);
    }

    private RespMessage buildNewsResp(Map<String, Object> reqModel) {
        return null;
    }

    private RespMessage buildTextResp(Map<String, Object> reqModel) {
        String fromUserName = (String) reqModel.get("ToUserName");
        String toUserName = (String) reqModel.get("FromUserName");
        String content = (String) reqModel.get("Content");
        switch (content) {
            case "卡农":
                Set<Music> musics = new HashSet<>();
                Music music = new Music();
                String title = "卡农";
                String description = "描述";
                String musicUrl = "http://www.on1piece.com/wx/music/Canon.mp3";
                String hQMusicUrl = "http://www.on1piece.com/wx/music/Canon.mp3";
                String thumbMediaId = "U-RoPYanqYCPx454ccWBR_ZRt3ZcHxkyv_UkNZYSkQA";
                music.setTitle(title);
                music.setDescription(description);
                music.setMusicUrl(musicUrl);
                music.sethQMusicUrl(hQMusicUrl);
                music.setThumbMediaId(thumbMediaId);
                musics.add(music);
                return new MusicResponse(toUserName, fromUserName, new Date().getTime(), StaticParam.respMessageTypeMusic, musics);
            case "图文":
                return null;
        }
        return new TextResponse(toUserName, fromUserName, new Date().getTime(), StaticParam.respMessageTypeText, content);
    }
}
