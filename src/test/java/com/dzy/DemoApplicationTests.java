package com.dzy;

import com.dzy.wx.entity.media.Media;
import com.dzy.wx.entity.media.MediaPage;
import com.dzy.wx.enums.MediaType;
import com.dzy.wx.repository.UserRepository;
import com.dzy.wx.service.AccessTokenService;
import com.dzy.wx.service.MediaService;
import com.dzy.wx.service.MenuService;
import com.dzy.wx.service.WxServerMessageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

    // @Autowired
    private AccessTokenService accessTokenService;
    // @Autowired
    private WxServerMessageService wxServerMessageService;
    // @Autowired
    private MenuService menuService;
    //@Autowired
    private MediaService mediaService;
    //  @Autowired
    private UserRepository userRepository;

    // @Test
    public void contextLoads() {
        System.out.println(accessTokenService.getAccessToken());
    }

    // @Test
    public void WxServerMessageImpl() {
        wxServerMessageService.getWxServerIPFromWxServer();
    }

    //@Test
    public void menuService() {
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
