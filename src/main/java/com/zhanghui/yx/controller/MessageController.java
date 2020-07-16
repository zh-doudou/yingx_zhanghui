package com.zhanghui.yx.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.zhanghui.yx.util.LoginVo;
import com.zhanghui.yx.util.SendMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("Message")
@Slf4j
public class MessageController {
    @Autowired
    LoginVo loginVo;
    @Autowired
    HttpSession session;
    private HashMap<String, String> map = new HashMap<>();

    /**
     * 验证码注册
     *
     * @param mobile 用户注册/登录输入的手机号
     * @return 回馈信息
     */
    @RequestMapping("authcode_get")
    public HashMap<String, String> authcode_get(String mobile) {

        //调用工具类方法进行发送
        loginVo.setMobile(mobile);
        log.info("后台接收的参数为:{}" + loginVo);
        SendSmsResponse response = SendMessageUtil.sendSms(loginVo, session);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("状态码Code=" + response.getCode());
        System.out.println("状态码描述Message=" + response.getMessage());
        if ("OK".equals(response.getCode())) {
            map.put("messages", "短信发送成功");
        } else if ("AMOUNT_NOT_ENOUGH".equals(response.getCode())) {
            map.put("messages", "账户余额不足");
        } else if ("MOBILE_NUMBER_ILLEGAL".equals(response.getCode())) {
            map.put("messages", "非法手机号");
        } else if ("AMOUNT_NOT_ENOUGH".equals(response.getCode())) {
            map.put("messages", "余额不足");
        }
        //System.out.println("RequestId=" + response.getRequestId());
        //System.out.println("BizId=" + response.getBizId());
        String yzm = (String) session.getAttribute("yzm");
        System.out.println("得到的验证码为：" + yzm);
        return map;
    }
}