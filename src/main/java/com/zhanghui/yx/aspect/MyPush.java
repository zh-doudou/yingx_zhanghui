package com.zhanghui.yx.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 张辉
 */
@Target(ElementType.METHOD) //方法中使用
@Retention(RetentionPolicy.RUNTIME)
public @interface MyPush {
    String value();  //加上log必须要填写一个值

}
