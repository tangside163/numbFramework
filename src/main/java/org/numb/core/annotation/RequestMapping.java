package org.numb.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tangsi
 * @version 1.0 Copyright (c) 2016/07/23 youku, All Rights Reserved.
 * @Project numbFramework
 * @Description:
 * @Company youku
 * @Create 2016/07/23 13:49
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String value() default "";

    /**
     * 请求类型是post还是get
     *
     * @return
     */
    MethodTypeEnum[] method() default {};

    public static enum MethodTypeEnum {
        GET, POST
    }

}
