/**
 * 
 */
package com.util;

/**
 * 
 * @author Jacky
 * @version V1.0
 * @since 2008-04-23
 */
public class ItemNotFoundException extends Exception {

	private static final long serialVersionUID = 0;

	/**
	 * 
	 */
	public ItemNotFoundException() {
		// TODO 自动生成构造函数存根
		super("找不到指定的项目，无法取得指定的配置信息！");
	}

	/**
	 * @param message
	 */
	public ItemNotFoundException(String message) {
		super(message);
		// TODO 自动生成构造函数存根
	}

	/**
	 * @param cause
	 */
	public ItemNotFoundException(Throwable cause) {
		super(cause);
		// TODO 自动生成构造函数存根
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO 自动生成构造函数存根
	}

}
