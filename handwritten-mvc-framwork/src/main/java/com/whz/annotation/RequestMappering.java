package com.whz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 处理方法注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMappering {

    /**
     * 请求路径
     * @return
     */
    String value() default "";

    /**
     * 请求方法
     * @return
     */
    RequestMethod method() default RequestMethod.GET;

}
