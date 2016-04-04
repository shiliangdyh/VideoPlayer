package com.i000phone.tools.network;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/4/1.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)//修饰方法
public @interface UrlString {
    String value();
}
