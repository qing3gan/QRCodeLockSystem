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

	public static UserInfo toUserInfo(HttpServletRequest request) {
		String idcard = "";
		String name = "";
		String password = "";
		try {
			idcard = new String(request.getParameter("idcard").getBytes(
					"ISO-8859-1"), "UTF-8");
			name = new String(request.getParameter("name").getBytes(
					"ISO-8859-1"), "UTF-8");
			password = new String(request.getParameter("password").getBytes(
					"ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserInfo userInfo = new UserInfo(idcard, name, password);
		return userInfo;
	}

}
