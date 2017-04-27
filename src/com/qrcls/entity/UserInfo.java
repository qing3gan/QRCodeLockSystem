package com.qrcls.entity;

/**
 * 用户信息实体类
 * 
 * @author Agony
 *
 */
public class UserInfo {

	/**
	 * 身份证
	 */
	private String idcard;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String password;

	public UserInfo(String idcard, String name, String password) {
		this.idcard = idcard;
		this.name = name;
		this.password = password;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
