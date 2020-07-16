package com.zhanghui.yx.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "message")
/**
 * mobile 用户手机号
 * password ：
 * accessKeyId:开发者自己的AK
 * accessKeySecret ：密钥的值
 * messageSignatures:短信签名
 * template  :短息模板
 * **/
public class LoginVo {
    private String mobile;
    private String password;
    private String accessKeyId;
    private String accessKeySecret;
    private String messageSignatures;
    private String template;

}
