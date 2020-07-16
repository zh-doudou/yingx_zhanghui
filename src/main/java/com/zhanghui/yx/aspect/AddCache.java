package com.zhanghui.yx.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //方法中使用
@Retention(RetentionPolicy.RUNTIME)
public @interface AddCache {
}
