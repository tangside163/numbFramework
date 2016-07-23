package org.numb.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tangsi
 * @version 1.0 Copyright (c) 2016/07/17 youku, All Rights Reserved.
 * @Project numbFramework
 * @Description:
 * @Company youku
 * @Create 2016/07/17 23:06
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

    public String value() default "";

}
