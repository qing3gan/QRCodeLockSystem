package com.qrcls.constant;

/**
 * 常量类
 * 
 * @author Agony
 *
 */
public interface Constant {

	/**
	 * AsyncContext超时时间（小时）
	 */
	int DEFAULT_TIME_OUT = 24 * 1 * 60 * 60 * 1000;

	/**
	 * 失败代码
	 */
	String MSG_STATUS_FAIL = "0";

	/**
	 * 成功消息
	 */
	String MSG_STATUS_SUCC = "1";

	/**
	 * 进行中代码
	 */
	String MSG_STATUS_ING = "2";

	/**
	 * 注册失败消息
	 */
	String MSG_REGIST_FAIL = "该用户已注册!";

	/**
	 * 注册成功消息
	 */
	String MSG_REGIST_SUCC = "注册成功~";

	/**
	 * 进行中消息
	 */
	String MSG_REGIST_ING = "审批中...";

	/**
	 * 登录失败消息
	 */
	String MSG_LOGIN_UNEXIST = "该用户不存在!";

	/**
	 * 登录失败消息
	 */
	String MSG_LOGIN_UNCORRECT = "用户密码错误!";

	/**
	 * 登录成功消息
	 */
	String MSG_LOGIN_SUCC = "登录成功~";
	
	/**
	 * 开门成功消息
	 */
	String MSG_OPEN_SUCC = "'芝麻开门~'";
	
	/**
	 * 开门失败消息
	 */
	String MSG_OPEN_FAIL = "'阿里巴巴不在家~'";
	
	/**
	 * 开门非法消息
	 */
	String MSG_OPEN_ILLEGAL = "'App的锅,服务器不接!'";
}
