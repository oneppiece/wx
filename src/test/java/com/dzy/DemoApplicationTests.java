package com.dzy;

import com.dzy.wx.global.StaticParam;
import com.dzy.wx.global.service.AccessTokenService;
import com.dzy.wx.global.service.WxServerMessageService;
import com.dzy.wx.media.entity.Media;
import com.dzy.wx.media.entity.MediaPage;
import com.dzy.wx.media.enums.MediaType;
import com.dzy.wx.media.service.MediaService;
import com.dzy.wx.menu.service.MenuService;
import com.dzy.wx.message.entity.resp.RespFactory;
import com.dzy.wx.message.service.MessageService;
import com.dzy.wx.user.repository.UserRepository;
import com.dzy.wx.utils.XmlAndJsonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    private RespFactory respFactory;
    MockMvc mockMvc;


    //@Autowired
    private AccessTokenService accessTokenService;
    // @Autowired
    private WxServerMessageService wxServerMessageService;
    // @Autowired
    private MenuService menuService;
    //@Autowired
    private MediaService mediaService;
    //  @Autowired
    private UserRepository userRepository;
    // @Autowired
    private StaticParam staticParam;

    // @Test
    public void contextLoads() {
        System.out.println(accessTokenService.getAccessToken());
    }

    //  @Test
    public void testStaticParams() {
        System.out.println(staticParam.getUrlOauthGetUserInfo());
    }

    // @Test
    public void WxServerMessageImpl() {
        wxServerMessageService.getWxServerIPFromWxServer();
    }

    //@Test
    public void menuService() {
    }

    //@Test
//    public void respFactory() {
//        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        String url = "/enter";
//        String keyWordMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
//        try {
//            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url).content(keyWordMessage).accept(org.springframework.http.MediaType.TEXT_XML)).andReturn();
//            int status = mvcResult.getResponse().getStatus();
//            Exception resolvedException = mvcResult.getResolvedException();
//            System.out.println(resolvedException.getMessage());
//            String contentAsString = mvcResult.getResponse().getContentAsString();
//            Assert.assertEquals("正確的應該是200", 200, status);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Autowired
    MessageService messageService;

    @Test
    public void respFactory() {
        String keyWordMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[卡农]]></Content><MsgId>1234567890123456</MsgId></xml>";
        String muneClickEventMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event><EventKey><![CDATA[Level_1_TODAY_MUSIC]]></EventKey></xml>";
        String locationEventMessage = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[LOCATION]]></Event><Latitude>23.137466</Latitude><Longitude>113.352425</Longitude><Precision>119.385040</Precision></xml>";


        Map<String, Object> hashMap = XmlAndJsonUtils.XML2HashMap(locationEventMessage);
        messageService.process(hashMap);
    }

    //@Test
    public void mediaService() {
        MediaPage mediaPage = new MediaPage(MediaType.image, 0, 20);
        Media fromWxServer = mediaService.getFromWxServer(mediaPage);

    }

    //@Test
    public void singalTest() {
        JSONObject jsonObject = JSONObject.fromObject("{\"item\":[{\"media_id\":\"U-RoPYanqYCPx454ccWBR_ZRt3ZcHxkyv_UkNZYSkQA\",\"name\":\"kanong0.jpg\",\"update_time\":1483435206,\"url\":\"http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/GiaCsSRv2ysAEYknMiaXqcUftebmbleIjOjibN7FFY4rao9fwxnZLPUjeTZib9NobfAjibDWiaUCQ65zmHTEojYeVVtw\\/0?wx_fmt=jpeg\"},{\"media_id\":\"U-RoPYanqYCPx454ccWBR_ZRt3ZcHxkyv_UkNZYSkQA\",\"name\":\"kanong1.jpg\",\"update_time\":1483435206,\"url\":\"http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/GiaCsSRv2ysAEYknMiaXqcUftebmbleIjOjibN7FFY4rao9fwxnZLPUjeTZib9NobfAjibDWiaUCQ65zmHTEojYeVVtw\\/0?wx_fmt=jpeg\"}],\"total_count\":1,\"item_count\":1}");
        JSONArray item = jsonObject.getJSONArray("item");
        for (int i = 0; i < item.size(); i++) {
            item.getJSONObject(i).forEach((Object k, Object v) ->
                    System.out.println(k.toString() + ":" + v.toString())
            );
        }
    }

    //@Test
    public void testOauth2() {
        System.out.println(userRepository.findOneByOpenId("o1L8jwLVvhImlH5uiVrXFl_hjfqE").toString());
    }
}
