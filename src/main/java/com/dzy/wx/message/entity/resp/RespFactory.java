package com.dzy.wx.message.entity.resp;

import com.dzy.wx.global.StaticParam;
import com.dzy.wx.message.req.repository.MusicRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.dzy.wx.global.StaticParam.KEYWORD_AQXJ;
import static com.dzy.wx.global.StaticParam.KEYWORD_CANON;

/**
 * 根据请求类型，构造返回对象
 * Created by Administrator on 2016/12/27.
 * User:Administrator
 * Date:2016/12/27
 * Time:16:49
 */
@Component
public class RespFactory {
    private static final Logger LOGGER = Logger.getLogger(RespFactory.class);
    @Autowired
    private MusicRepository musicRepository;

    public RespFactory() {
    }

    /**
     * 根据请求类型，构建返回类型
     *
     * @param reqModel 请求的xml转化后的Map对象
     * @return 根据需要, 具体的响应结果
     */
    public RespMessage createResponse(Map<String, Object> reqModel) {
        String msgType = (String) reqModel.get("MsgType");
        switch (msgType) {
            //文本类型
            case StaticParam.reqMessageTypeText:
                return buildTextResp(reqModel);
            //图片类型
            case StaticParam.reqMessageTypeImage:
                return buildImageResp(reqModel);
            //语音类型
            case StaticParam.respMessageTypeVoice:
                return buildVoiceResp(reqModel);
            //视频类型
            case StaticParam.respMessageTypeVideo:
                return buildVideoResp(reqModel);
            //小视频类型
            case StaticParam.respMessageTypeShortVideo:
                return buildShortVideoResp(reqModel);
            //地理位置类型 (微信聊天中的 位置)
            case StaticParam.respMessageTypeLocation:
                return buildLocationResp(reqModel);
            //链接类型 (微信聊天中的 收藏 中的 网页链接)
            case StaticParam.respMessageTypeLink:
                return buildLinkResp(reqModel);
            //事件类型
            case StaticParam.respMessageTypeEvent:
                return buildByEvent(reqModel);

            default:
                LOGGER.error("找不到合适的返回类型");
                reqModel.forEach((String key, Object val) -> System.out.printf("%s:%s%n", key, val));
                reqModel.replace("Content", "找不到合适的返回类型");
                return buildTextResp(reqModel);
        }
    }

    /**
     * 根据事件类型，构建返回 (大多数不需要有返回)
     */
    private RespMessage buildByEvent(Map<String, Object> reqModel) {
        String eventType = (String) reqModel.get("Event");
        String fromUserName = (String) reqModel.get("ToUserName");
        String toUserName = (String) reqModel.get("FromUserName");
        switch (eventType) {
            // 扫码关注 或 正常关注
            case StaticParam.EventTypeSubscribe:
                if (reqModel.get("EventKey") != null)
                    reqModel.put("Content", "扫描的信息:EventKey-->" + reqModel.get("EventKey") + " Ticket-->" + reqModel.get("Ticket"));
                reqModel.put("Content", "终于等到你 ^_^");
                return buildTextResp(reqModel);
            // 取消关注事件
            case StaticParam.EventTypeUnSubscribe:
                reqModel.put("Content", "被取消关注了好吗");
                return buildTextResp(reqModel);
            // 扫码事件
            case StaticParam.EventTypeScan:
                reqModel.put("Content", "扫描的信息:EventKey-->" + reqModel.get("EventKey") + " Ticket-->" + reqModel.get("Ticket"));
                return buildTextResp(reqModel);
            //上报地理位置事件
            case StaticParam.EventTypeLocation:
                String latitude = (String) reqModel.get("Latitude");
                String longitude = (String) reqModel.get("Longitude");
                String precision = (String) reqModel.get("Precision");
                StringBuilder locationContent = new StringBuilder();
                locationContent.append("地理位置维度:" + latitude)
                        .append("\n")
                        .append("地理位置经度:" + longitude)
                        .append("\n")
                        .append("地理位置精度" + precision);
                reqModel.put("Content", locationContent.toString());
                //return buildTextResp(reqModel);
                return new TextResponse();
            // 点击菜单拉取消息时的事件推送
            // 1 根据eventKey 判断是哪个按钮
            // 2 根据按钮执行操作
            case StaticParam.EventTypeClick:
                reqModel.put("Content", "默认:点击菜单拉取消息时的事件推送");
                String eventKey = (String) reqModel.get("EventKey");
                if ("Level_1_TODAY_MUSIC".equals(eventKey)) {
                    Music music = musicRepository.findOneRandom();
                    Set<Music> musics = new HashSet<>();
                    musics.add(music);
                    return new MusicResponse(toUserName, fromUserName, new Date().getTime(), StaticParam.respMessageTypeMusic, musics);
                } else
                    return buildTextResp(reqModel);
                // 点击菜单跳转链接时的事件推送
            case StaticParam.EventTypeView:
                reqModel.put("Content", "默认:点击菜单跳转链接时的事件推送");
                return buildTextResp(reqModel);
            default:
                LOGGER.error("没有找到合适的事件类型处理");
                reqModel.put("Content", "默认:没有找到合适的事件类型处理");
                return buildTextResp(reqModel);
        }
    }

    /**
     * 请求的是超链接信息，用Text 返回超链接的具体信息
     *
     * @param reqModel Map对象
     * @return Text类型
     */
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

    /**
     * 请求的是小视频消息，用video 返回一个视频对象 (要从素材库中弄视频)
     *
     * @param reqModel Map对象
     * @return Video类型
     */
    private RespMessage buildShortVideoResp(Map<String, Object> reqModel) {
        return buildVideoResp(reqModel);
    }

    /**
     * 请求的是位置消息，用Text 返回一个位置信息的具体信息
     *
     * @param reqModel Map对象
     * @return Text类型
     */
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
        reqModel.replace("Content", locationContent.toString());
        return buildTextResp(reqModel);
    }

    /**
     * 同 小视频
     *
     * @param reqModel Map对象
     * @return Video类型
     */
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

    /**
     * 请求的是语音消息，用Voice返回传入的语音消息
     *
     * @param reqModel Map对象
     * @return Voice类型
     */
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

    /**
     * 请求的是图片消息，用Image返回传入的图片消息
     *
     * @param reqModel Map对象
     * @return Image类型
     */
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


    /**
     * 回复图文消息
     *
     * @param reqModel Map对象
     * @return News类型
     */
    private RespMessage buildNewsResp(Map<String, Object> reqModel) {
        return null;
    }

    /**
     * 回复文本消息,如果是关键字, 按关键字处理
     *
     * @param reqModel Map对象
     * @return Text类型 或 其他
     */
    private RespMessage buildTextResp(Map<String, Object> reqModel) {
        String fromUserName = (String) reqModel.get("ToUserName");
        String toUserName = (String) reqModel.get("FromUserName");
        String content = (String) reqModel.get("Content");
        if (!StringUtils.isEmpty(content))
            switch (content) {
                case KEYWORD_CANON:
                case KEYWORD_AQXJ:
                    Set<Music> musics = new HashSet<>();
                    Music music = musicRepository.getOneByTitle(content);
                    musics.add(music);
                    return new MusicResponse(toUserName, fromUserName, new Date().getTime(), StaticParam.respMessageTypeMusic, musics);
                default:
                    return new TextResponse(toUserName, fromUserName, new Date().getTime(), StaticParam.respMessageTypeText, content);
            }
        return null;
    }
}
