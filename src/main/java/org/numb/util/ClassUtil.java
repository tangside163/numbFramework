package org.numb.util;

import com.sun.deploy.util.StringUtils;
import org.apache.log4j.Logger;
import org.numb.constant.NumbConstant;
import org.numb.exception.FrameworkException;

import java.io.File;
import java.io.FileFilter;
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
	 * 方法用途:加载指定包下所有class到jvm中 <br>
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
				if (NumbConstant.URL_PROTOCOL_FILE.equals(protocol))// 加载文件夹中的class
				{
					File packageFile = new File(url.getFile());
					addClass(clazzes, packageName, packageFile, classLoader);

				} else if (NumbConstant.URL_PROTOCOL_JAR.equals(protocol)) // 从jar包中加载类
				{
					JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
					JarFile jarFile = jarURLConnection.getJarFile();
					Enumeration<JarEntry> jarEntries = jarFile.entries();
					while (jarEntries.hasMoreElements())
					{
						JarEntry jarEntry = jarEntries.nextElement();
						String entryName = jarEntry.getName();
						if (entryName.endsWith(NumbConstant.CLASS_FILE_SUFFIX))
						{
							String className = entryName.substring(0, entryName.lastIndexOf(NumbConstant.CLASS_FILE_SUFFIX))
									.replaceAll("/", ".");
							Class<?> clazz = loadClass(className, true, classLoader);
							clazzes.add(clazz);
						}
					}
				}
			}
		} catch (IOException e)
		{
			String errorMsg = "获取" + packagePath + "下的资源时发生错误";
			logger.error(errorMsg, e);
			throw new FrameworkException(errorMsg, e);
		}

		return clazzes;
	}

	/**
	 *
	 * 方法用途: 递归加载文件中的class<br>
	 * 实现步骤: <br>
	 *
	 * @param clazzes
	 * @param packageName
	 * @param packageFile
	 * @param classLoader
	 */
	public static void addClass(Set<Class<?>> clazzes, String packageName, File packageFile, ClassLoader classLoader)
	{
		File[] files = packageFile.listFiles(new FileFilter()
		{

			@Override
			public boolean accept(File file)
			{
				return file.isDirectory() || (file.isFile() && file.getName().endsWith(NumbConstant.CLASS_FILE_SUFFIX));
			}
		});

		if (files != null && files.length > 0)
		{
			String fileName = null;
			for (File pFile : files)
			{
				if (pFile.isFile())
				{
					fileName = pFile.getName();
					String className = packageName + "."
							+ fileName.substring(0, fileName.lastIndexOf(NumbConstant.CLASS_FILE_SUFFIX));
					Class<?> clazz = loadClass(className, true, classLoader);
					clazzes.add(clazz);
				} else if (pFile.isDirectory())
				{
					packageName = packageName + fileName;
					addClass(clazzes, packageName, pFile, classLoader);
				}
			}
		}
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

	/**
	 * 
	 * 方法用途: 根据类全限定名，加载该类到jvm中并返回该Class对象<br>
	 * 实现步骤: <br>
	 *
	 * @param className
	 * @param initialize
	 * @param classLoader
	 * @return
	 */
	public static Class<?> loadClass(String className, boolean initialize, ClassLoader classLoader)
	{
		try
		{
			return Class.forName(className, true, classLoader);
		} catch (ClassNotFoundException e)
		{
			String errorMsg = "加载类:" + className + "时发生错误,该类" + className + "不存在";
			logger.error(errorMsg, e);
			throw new FrameworkException(errorMsg, e);
		}

	}

}
