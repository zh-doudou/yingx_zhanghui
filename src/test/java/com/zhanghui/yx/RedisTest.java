package com.zhanghui.yx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = YxApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void test1() {
        redisTemplate.opsForValue().set("name", "hhh");  //操作字符串
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
}
