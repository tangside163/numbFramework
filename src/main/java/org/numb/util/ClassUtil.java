package org.numb.util;

import org.apache.log4j.Logger;
import org.numb.constant.NumbConstant;
import org.numb.exception.FrameworkException;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author tangsi
 * @version 1.0 Copyright (c) 2016/7/11 youku, All Rights Reserved.
 * @Project numbFramework
 * @Description:类加载工具类
 * @Company youku
 * @Create 2016/7/11 16:17
 */

public class ClassUtil
{

	private static final Logger logger = Logger.getLogger(ClassUtil.class);

	/**
	 * 
	 * 方法用途:加载包下所有class到jvm中 <br>
	 * 实现步骤: <br>
	 *
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> loadClass(String packageName)
	{
		Set<Class<?>> clazzes = new HashSet<Class<?>>();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replaceAll("\\.", "/");
		try
		{
			Enumeration<URL> urls = classLoader.getResources(packagePath);
			while (urls.hasMoreElements())
			{
				URL url = urls.nextElement();
				String protocol = url.getProtocol();
				if (NumbConstant.URL_PROTOCOL_FILE.equals(protocol))
				{


				} else if (NumbConstant.URL_PROTOCOL_JAR.equals(protocol))
				{
					JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
					JarFile jarFile = jarURLConnection.getJarFile();
					Enumeration<JarEntry> jarEntrys = jarFile.entries();
					while(jarEntrys.hasMoreElements())
					{
						JarEntry jarEntry = jarEntrys.nextElement();
						String entryName = jarEntry.getName();
						if(entryName.endsWith(".class"))
						{

						}
					}
				}
			}
		} catch (IOException e)
		{
			String errorMsg = "获取" + packagePath + "下的资源时发生错误";
		}

		return clazzes;
	}

	/**
	 * 
	 * 方法用途: <br>
	 * 实现步骤: <br>
	 *
	 * @return
	 */
	public static ClassLoader getCurrentClassLoader()
	{
		return Thread.currentThread().getContextClassLoader();
	}

}
