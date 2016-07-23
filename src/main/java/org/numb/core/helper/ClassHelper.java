package org.numb.core.helper;

import org.numb.core.annotation.Controller;
import org.numb.core.annotation.Service;
import org.numb.util.ClassUtil;

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

public class ClassHelper {

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

    static {
        String scanBasePackage = ConfigHelper.getAppBasePackage();
        Set<Class<?>> clazzes = ClassUtil.loadClass(scanBasePackage);

        initAllClazz(clazzes);

    }

    private static void initAllClazz(Set<Class<?>> clazzes) {
        for (Class<?> clazz : clazzes) {
            if (clazz.isAnnotationPresent(Controller.class)) {
                controllerClazz.add(clazz);
            } else if (clazz.isAnnotationPresent(Service.class)) {
                serviceClazz.add(clazz);
            }
        }

        ALL_CLAZZ.addAll(controllerClazz);
        ALL_CLAZZ.addAll(serviceClazz);

    }

    /**
     * 获取所有被IOC容器扫描到的类
     *
     * @return
     */
    public static Set<Class<?>> getAllBeanClasses() {
        return ALL_CLAZZ;
    }

    /**
     * 获取所有被Controller标注的类
     *
     * @return
     */
    public static Set<Class<?>> getAllControllerClazzes() {
        return controllerClazz;
    }


    /**
     * 获取所有被Service标注的类
     *
     * @return
     */
    public static Set<Class<?>> getAllServiceClazzes() {
        return serviceClazz;
    }

}
