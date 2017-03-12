package com.dzy.wx.menu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dzy.wx.global.StaticParam;
import com.dzy.wx.global.service.AccessTokenService;
import com.dzy.wx.menu.entity.Button;
import com.dzy.wx.menu.entity.ClickButton;
import com.dzy.wx.menu.entity.Menu;
import com.dzy.wx.menu.entity.ViewButton;
import com.dzy.wx.menu.service.MenuService;
import com.dzy.wx.utils.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:17:22
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private StaticParam staticParam;
    @Autowired
    private AccessTokenService accessTokenService;

    @Override
    public String createOrUpdate() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String result = mapper.writeValueAsString(buildMenu());
            String url = staticParam.getUrlMenuCreate().replace("ACCESS_TOKEN", accessTokenService.getAccessToken());
            JSONObject jsonObject = HttpUtils.httpRequest(url, RequestMethod.POST.toString(), result);
            return jsonObject.toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "发起请求过程出错";
        }
    }

    private Menu buildMenu() {
        Menu menu = new Menu();
        ClickButton clickButton = new ClickButton();
        clickButton.setName("今日歌曲");
        clickButton.setType("click");
        clickButton.setKey("Level_1_TODAY_MUSIC");

        ViewButton viewButton = new ViewButton();
        viewButton.setName("搜索");
        viewButton.setType("view");
        viewButton.setUrl("http://www.soso.com/");
        ViewButton viewButton2 = new ViewButton();
        viewButton2.setName("测试支付");
        viewButton2.setType("view");
        viewButton2.setUrl("http://www.on1piece.com/wx/pay/payIndex");
        ClickButton clickButton1 = new ClickButton();
        clickButton1.setName("赞一下我们");
        clickButton1.setKey("LEVEL_2_GOOD");
        clickButton1.setType("click");

        Button button = new Button();
        button.setName("菜单");
        List<Button> buttons = new ArrayList<>();
        List<Button> buttons1 = new ArrayList<>();
        buttons.add(viewButton);
        buttons.add(viewButton2);
        buttons.add(clickButton1);
        button.setSubButton(buttons);
        buttons1.add(clickButton);
        buttons1.add(button);

        menu.setButton(buttons1);
        return menu;
    }

    @Override
    public String getCurrent() {
        String url = staticParam.getUrlMenuGetCurrent().replace("ACCESS_TOKEN", accessTokenService.getAccessToken());
        return HttpUtils.httpRequest(url, RequestMethod.GET.toString(), null).toString();
    }
}
