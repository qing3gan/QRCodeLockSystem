package com.qrcls.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.qrcls.entity.UserInfo;

/**
 * 请求体工具类
 * 
 * @author Agony
 *
 */
public class RequestUtil {

	/**
	 * JSP-ISO-8859-1
	 * 
	 * @param request
	 * @return
	 */
	public static UserInfo toUserInfo(HttpServletRequest request) {
		String idcard = request.getParameter("idcard");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		try {
			idcard = new String(idcard.getBytes("ISO-8859-1"), "UTF-8");
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			password = new String(password.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserInfo userInfo = new UserInfo(idcard, name, password);
		return userInfo;
	}

	/**
	 * AJAX-UTF8
	 * 
	 * @param request
	 * @return
	 */
	public static UserInfo toUserInfo2(HttpServletRequest request) {
		String idcard = request.getParameter("idcard");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		UserInfo userInfo = new UserInfo(idcard, name, password);
		return userInfo;
	}

}
