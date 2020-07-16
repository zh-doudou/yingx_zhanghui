package com.zhanghui.yx;

import com.alibaba.fastjson.JSON;
import com.zhanghui.yx.eChartsVO.PushStatVO;
import com.zhanghui.yx.service.YxUserService;
import io.goeasy.GoEasy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = YxApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class goeasyTest {
    @Resource
    private YxUserService yxUserService;

    /**
     * 服务端发送消息
     **/
    @Test
    public void goueasysendmassage() {
        List<PushStatVO> pushStatVOS = yxUserService.selectAllBySexAndCreateDate();
        String connten = JSON.toJSONString(pushStatVOS);
        //创建goeasy对象  配置参数：地区地址    appkey
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-2557e7bba400456192dad9d9386f117e");
        //发送消息
        goEasy.publish("zh_channel", connten);

    }
}
