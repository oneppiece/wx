package com.dzy.wx.controller;

import com.dzy.wx.Utils.XmlAndJsonUtils;
import com.dzy.wx.entity.Singature;
import com.dzy.wx.entity.media.Media;
import com.dzy.wx.entity.media.MediaPage;
import com.dzy.wx.entity.message.res.RespMessage;
import com.dzy.wx.enums.MediaType;
import com.dzy.wx.service.*;
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

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public String checkSignatrue(@RequestBody Singature singature) {
        return CheckSignatureService.checkSignature(singature);
    }

    @RequestMapping(value = "/enter", method = RequestMethod.POST, produces = "application/xml;charset=UTF-8")
    public RespMessage processEvent(@RequestBody String reqModel) {
        Map<String, Object> hashMap = XmlAndJsonUtils.XML2HashMap(reqModel);
        return messageService.process(hashMap);
    }

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
