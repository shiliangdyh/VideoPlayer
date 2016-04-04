package com.i000phone.tools.viewutil;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/4/1.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindView {
    int resId();
    String bindMethod() default "";
    Class bindType() default Object.class;
    Class viewType() default View.class;
    String adapterMethod() default "";
}
