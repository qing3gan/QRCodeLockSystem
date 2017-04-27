package com.qrcls.entity;

/**
 * 返回消息实体类
 * 
 * @author Agony
 *
 */
public class MsgInfo {

	/**
	 * 消息状态码
	 */
	private String status;

	/**
	 * 消息内容
	 */
	private String msg;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
