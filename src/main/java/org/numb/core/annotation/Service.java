package org.numb.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author tangsi
 * @version 1.0 Copyright (c) 2016/07/23 youku, All Rights Reserved.
 * @Project numbFramework
 * @Description:
 * @Company youku
 * @Create 2016/07/23 17:36
 */
@Target(ElementType.TYPE)
public @interface Service {

    String value() default "";

}
