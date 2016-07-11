package org.numb.exception;

/**
 * @author tangsi
 * @version 1.0 Copyright (c) 2016/7/11 youku, All Rights Reserved.
 * @Project numbFramework
 * @Description:
 * @Company youku
 * @Create 2016/7/11 17:14
 */

public class FrameworkException extends RuntimeException
{

	/**
	 * 
	 * 方法用途: <br>
	 * 实现步骤: <br>
	 *
	 * @param msg
	 * @param e
	 */
	public FrameworkException(String msg, Exception e)
	{
		super(msg, e);
	}

}
