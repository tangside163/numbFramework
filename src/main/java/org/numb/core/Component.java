package org.numb.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tangsi
 * @version 1.0 Copyright (c) 2016/7/12 youku, All Rights Reserved.
 * @Project numbFramework
 * @Description:
 * @Company youku
 * @Create 2016/7/12 15:04
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component
{

	public String value();

}
