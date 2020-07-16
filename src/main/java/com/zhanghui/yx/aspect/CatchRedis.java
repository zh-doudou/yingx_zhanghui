package com.zhanghui.yx.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;


@Configuration
@Aspect
@Slf4j
public class CatchRedis {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 根据注解进行查询加入缓存
     **/
    @Around("@annotation(com.zhanghui.yx.aspect.AddCache)")
    public Object addCatchRedis(ProceedingJoinPoint proceedingJoinPoint) {

        System.out.println("==========进入环绕通知==========");
        /**解决缓存乱码*/
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        //获取类的全限定名
        String ClassName = proceedingJoinPoint.getTarget().getClass().getName();
        System.out.println("类的全限定名为：" + ClassName);
        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println("执行的方法名为：" + methodName);
        //创建一个可变长字符串
        StringBuilder sb = new StringBuilder();
        sb.append(methodName);
        //获取所有的参数
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            sb.append(arg);
        }
        String key = sb.toString();
        HashOperations hash = redisTemplate.opsForHash();
        //根据类名 方法名进行查询是否存在
        Boolean aBoolean = hash.hasKey(ClassName, key);
        Object result = null;
        if (aBoolean) {
            //如果存在 则把结果返回
            result = hash.get(ClassName, key);
            System.out.println("存在直接不查数据库");
        } else {
            try {
                System.out.println("不存在直接查数据库");
                //不存在放行查询拿到结果
                result = proceedingJoinPoint.proceed();

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            //将从数据库查询到的数据存入到hash中
            hash.put(ClassName, key, result);
        }
        return result;
    }

    /**
     * 根据注解进行清空缓存
     */
    @After("@annotation(com.zhanghui.yx.aspect.DelCache)")
    public void delCache(JoinPoint joinPoint) {
        System.out.println("---------修改操作清空-----------");
        /**
         *key=String value=String
         *hash
         *KEY=(类的全限定名)String, value=(key=(方法名+实参)String,value=(数据)String)
         * */
        //获取类的全限定名
        String ClassName = joinPoint.getTarget().getClass().getName();
        Boolean delete = stringRedisTemplate.delete(ClassName);
        System.out.println("删除结果" + delete);

/*        Set<String> keys = stringRedisTemplate.keys("*");
        for (String key : keys) {
            if (key.startsWith(ClassName)) {
                //删除key
                stringRedisTemplate.delete(key);
            }
        }*/
    }
}
