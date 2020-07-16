package com.zhanghui.yx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张辉
 * @SpringBootApplication 将三个有用的注解组合在了一起： ①：@SpringBootConfiguration：标明该类使用Spring基于Java的配置。
 * @ComponentScan：启用组件扫描，这样你写的Web控制器类和其他组件才能被 自动发现并注册为Spring应用程序上下文里的Bean
 * @EnableAutoConfiguration：这个不起眼的小注解也可以称为 @Abracadabra①，就是这一行配置开启了Spring Boot自动配置的魔力
 */
@SpringBootApplication    //开启组件扫描和自动配置
@MapperScan("com.zhanghui.yx.dao")
public class YxApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxApplication.class, args);
    }

}