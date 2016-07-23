package org.numb.core.helper;

import org.apache.log4j.Logger;
import org.numb.exception.FrameworkException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author tangsi
 * @version 1.0 Copyright (c) 2016/07/23 youku, All Rights Reserved.
 * @Project numbFramework
 * @Description:
 * @Company youku
 * @Create 2016/07/23 17:42
 */
public class ConfigHelper {

    private static Logger logger = Logger.getLogger(ConfigHelper.class);

    public static String getAppBasePackage() {
        try {
            InputStream inputStream = ConfigHelper.class.getClassLoader().getResourceAsStream("numbFrameWork.properties");
            Properties properties = new Properties();

            properties.load(inputStream);
            return properties.getProperty("scanBasePackage");
        } catch (IOException e) {
            String errorMsg = "加载numbFrameWork.properties文件发生错误";
            logger.fatal(errorMsg, e);
            throw new FrameworkException(errorMsg, e);
        }
    }

}
