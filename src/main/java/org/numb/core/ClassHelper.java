package org.numb.core;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tangsi
 * @version 1.0 Copyright (c) 2016/7/12 youku, All Rights Reserved.
 * @Project numbFramework
 * @Description:
 * @Company youku
 * @Create 2016/7/12 14:57
 */

public class ClassHelper
{

	/**
	 * 所有被ioc容器管理的类
	 */
	private static final Set<Class<?>> ALL_CLAZZ = new HashSet<>();

	/**
	 * 所有被Controller注解标注的类
	 */
	private static final Set<Class<?>> controllerClazz = new HashSet<>();

	/**
	 * 所有被Service注解标注的类
	 */
	private static final Set<Class<?>> serviceClazz = new HashSet<>();

}
