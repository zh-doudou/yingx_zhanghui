package com.zhanghui.yx;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.zhanghui.yx.util.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = YxApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class zhTest {
    @Autowired
    private LoginVo loginVo;
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    static final String product = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    static final String domain = "dysmsapi.aliyuncs.com";

    /**
     * TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
     */
  /*  static final String accessKeyId = "LTAI4GKk9sRLQBP25dBYBmkE";
    static final String accessKeySecret = "M7B1dHqF5leZOYrHfxZVRyfofG9sfp";*/
    @Test
    public void add() throws InterruptedException {
        loginVo.setMobile("18513291993");
        //发短信
        SendSmsResponse response = sendSms(loginVo);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
        Thread.sleep(3000L);
    }

    public static SendSmsResponse sendSms(LoginVo loginVo) {
        /**
         * 可自助调整超时时间
         * */
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        /**
         * 初始化acsClient,暂不支持region化
         * */
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", loginVo.getAccessKeyId(), loginVo.getAccessKeySecret());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        /**
         * 组装请求对象-具体描述见控制台-文档部分内容
         * */
        SendSmsRequest request = new SendSmsRequest();
        /**
         * 必填:待发送手机号
         * */
        request.setPhoneNumbers(loginVo.getMobile());
        /**
         * 必填:短信签名-可在短信控制台中找到
         * */
        request.setSignName(loginVo.getMessageSignatures());

        /**
         * 必填:短信模板-可在短信控制台中找到
         * */
        request.setTemplateCode(loginVo.getTemplate());
        /**
         * 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
         * 发送的验证码6666
         *
         * */
        String code = "1" + RandomStringUtils.randomNumeric(5);
        //request.setTemplateParam("{\"code\":\"5201\"}");
        request.setTemplateParam("{\"code\":\"" + code + "\"}");


        /**
         * 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
         * */
        request.setOutId("yourOutId");

        /**
         * hint 此处可能会抛出异常，注意catch
         * */
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return sendSmsResponse;
    }

}
