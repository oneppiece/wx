package com.dzy.wx.global.controller;

import com.dzy.wx.global.entity.Signature;
import com.dzy.wx.global.service.AccessTokenService;
import com.dzy.wx.global.service.SignatureUtils;
import com.dzy.wx.media.entity.Media;
import com.dzy.wx.media.entity.MediaPage;
import com.dzy.wx.media.enums.MediaType;
import com.dzy.wx.media.service.MediaService;
import com.dzy.wx.menu.service.MenuService;
import com.dzy.wx.message.entity.resp.RespMessage;
import com.dzy.wx.message.service.MessageService;
import com.dzy.wx.utils.XmlAndJsonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:11:37
 */
@RestController
public class CommonController {
    private static final Logger LOGGER = Logger.getLogger(CommonController.class);
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private MediaService mediaService;

    /**
     * 服务器验证:
     * 微信服务器将发送GET请求到填写的服务器地址URL上,通过检验signature对请求进行校验。
     * 若确认此次GET请求来自微信服务器,原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
     *
     * @param signature
     * @return
     */
    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public String checkSignTrue(@RequestBody Signature signature) {
        return SignatureUtils.checkSignature(signature);
    }

    /**
     * 接收普通消息:
     * 当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。
     *
     * @param reqModel
     * @return 被动回复用户消息
     */
    @RequestMapping(value = "/enter", method = RequestMethod.POST, produces = "application/xml;charset=UTF-8")
    public RespMessage processEvent(@RequestBody String reqModel) {
        Map<String, Object> hashMap = XmlAndJsonUtils.XML2HashMap(reqModel);
        return messageService.process(hashMap);
    }

    /**
     * 获取最新的AccessToken
     *
     * @return
     */
    @RequestMapping(value = "/accessToken", method = RequestMethod.GET)
    public String getAccessToken() {
        return accessTokenService.getAccessToken();
    }

//    @RequestMapping(value = "/materials", method = RequestMethod.POST)
//    public ResponseEntity<List<Media>> getMaterials(@RequestBody(required = true) MediaPage mediaPage) {
//        List<Media> pageList = mediaService.findPage();
//        return new ResponseEntity<>(pageList, HttpStatus.OK);
//    }

    @RequestMapping(value = "/wx/materials", method = RequestMethod.POST)
    public ResponseEntity<List<Media>> getMaterials() throws InterruptedException {
        mediaService.getFromWxServer(new MediaPage(MediaType.image, 0, 5));
        mediaService.getFromWxServer(new MediaPage(MediaType.news, 0, 5));
        mediaService.getFromWxServer(new MediaPage(MediaType.video, 0, 5));
        return new ResponseEntity<>(mediaService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/createMenu", method = RequestMethod.GET, produces = "application/json")
    public String createMenu() {
        return menuService.createOrUpdate();
    }

    @RequestMapping(value = "/getCurrentMenu", method = RequestMethod.GET, produces = "application/json")
    public String getCurrentMenu() {
        return menuService.getCurrent();
    }

}
