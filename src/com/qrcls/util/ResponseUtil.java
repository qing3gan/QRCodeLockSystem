package com.qrcls.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qrcls.entity.MsgInfo;
import com.qrcls.entity.UserInfo;

/**
 * 响应体工具类
 * 
 * @author Agony
 *
 */
public class ResponseUtil {

	/**
	 * 向响应体流中写入返回信息
	 * 
	 * @param response
	 * @param msgInfo
	 */
	public static void write(HttpServletResponse response, MsgInfo msgInfo) {
		try {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(JSONObject.toJSONString(msgInfo));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向响应体流中写入返回信息
	 * 
	 * @param response
	 * @param msgInfo
	 */
	public static void write(HttpServletResponse response,
			List<UserInfo> userInfos) {
		try {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(JSONArray.toJSONString(userInfos));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
