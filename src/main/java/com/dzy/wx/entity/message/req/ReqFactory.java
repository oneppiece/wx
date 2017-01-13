package com.dzy.wx.entity.message.req;

import com.dzy.wx.entity.StaticParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/27.
 * User:Administrator
 * Date:2016/12/27
 * Time:15:25
 */
@Component
public class ReqFactory {
    private Logger logger = Logger.getLogger(ReqFactory.class);

    public ReqModel createReq(Map<String, Object> map) {
        return StaticParam.reqMessageTypeEvent.equals(map.get("MsgType")) ? buildEvent(map) : buildMessage(map);
    }

    private ReqModel buildMessage(Map<String, Object> map) {
        String msgType = (String) map.get("MsgType");
        String toUserName = (String) map.get("ToUserName");
        String fromUserName = (String) map.get("FromUserName");
        Double createTime = Double.parseDouble((String) map.get("CreateTime"));
        String msgId = (String) map.get("MsgId");
        String mediaId, thumbMediaId;
        switch (msgType) {
            case StaticParam.reqMessageTypeText:
                String content = (String) map.get("Content");
                return new Text(toUserName, fromUserName, createTime, msgType, msgId, content);
            case StaticParam.reqMessageTypeLink:
                String title = (String) map.get("Title");
                String description = (String) map.get("Description");
                String url = (String) map.get("Url");
                return new Link(toUserName, fromUserName, createTime, msgType, msgId, title, description, url);
            case StaticParam.reqMessageTypeImage:
                String picUrl = (String) map.get("PicUrl");
                mediaId = (String) map.get("MediaId");
                return new Image(toUserName, fromUserName, createTime, msgType, msgId, picUrl, mediaId);
            case StaticParam.reqMessageTypeLocation:
                String locationX = (String) map.get("Location_X");
                String locationY = (String) map.get("Location_Y");
                String scale = (String) map.get("Scale");
                String label = (String) map.get("Label");
                return new Location(toUserName, fromUserName, createTime, msgType, msgId, locationX, locationY, scale, label);
            case StaticParam.reqMessageTypeVoice:
                String format = (String) map.get("Format");
                mediaId = (String) map.get("media_id");
                return new Voice(toUserName, fromUserName, createTime, msgType, msgId, mediaId, format);
            case StaticParam.reqMessageTypeVideo:
                mediaId = (String) map.get("MediaId");
                thumbMediaId = (String) map.get("ThumbMediaId");
                return new Video(toUserName, fromUserName, createTime, msgType, msgId, mediaId, thumbMediaId);
            case StaticParam.reqMessageTypeShortVideo:
                mediaId = (String) map.get("MediaId");
                thumbMediaId = (String) map.get("ThumbMediaId");
                return new ShortVideo(toUserName, fromUserName, createTime, msgType, msgId, mediaId, thumbMediaId);
            default:
                logger.error("未侦测到的消息类型");
                return null;
        }
    }

    private ReqModel buildEvent(Map<String, Object> map) {
        String msgType = (String) map.get("MsgType");
        String toUserName = (String) map.get("ToUserName");
        String fromUserName = (String) map.get("FromUserName");
        Double createTime = Double.parseDouble((String) map.get("CreateTime"));
        String event = (String) map.get("Event");
        String eventKey;
        switch (event) {
            case StaticParam.EventTypeClick:
                eventKey = (String) map.get("EventKey");
                return new ClickEvent(toUserName, fromUserName, createTime, msgType, event, eventKey);
            case StaticParam.EventTypeLocation:
                Double latitude = Double.parseDouble((String) map.get("Latitude"));
                Double longitude = Double.parseDouble((String) map.get("Longitude"));
                Double precision = Double.parseDouble((String) map.get("Precision"));
                return new LocationEvent(toUserName, fromUserName, createTime, msgType, event, latitude, longitude, precision);
            case StaticParam.EventTypeScan:
                eventKey = (String) map.get("EventKey");
                String ticket = (String) map.get("Ticket");
                return new ScanEvent(toUserName, fromUserName, createTime, msgType, event, eventKey, ticket);
            case StaticParam.EventTypeSubscribe:
                return new SubscribeEvent(toUserName, fromUserName, createTime, msgType, event);
            case StaticParam.EventTypeUnSubscribe:
                return new SubscribeEvent(toUserName, fromUserName, createTime, msgType, event);
            case StaticParam.EventTypeView:
                eventKey = (String) map.get("EventKey");
                return new ViewEvent(toUserName, fromUserName, createTime, msgType, event, eventKey);
            default:
                logger.error("未侦测到的事件类型");
                return null;
        }
    }
}
