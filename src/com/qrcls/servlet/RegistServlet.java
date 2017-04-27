package com.qrcls.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.qrcls.comet.ClientComet;
import com.qrcls.comet.Javascript;
import com.qrcls.constant.Constant;
import com.qrcls.entity.MsgInfo;
import com.qrcls.entity.UserInfo;
import com.qrcls.util.RequestUtil;
import com.qrcls.util.ResponseUtil;
import com.qrcls.util.UserPropUtil;

/**
 * 注册Servlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MsgInfo msgInfo = new MsgInfo();
		String method = request.getParameter("method");
		// 注册中
		if ("registing".equals(method)) {
			// 已注册
			UserInfo userInfo = RequestUtil.toUserInfo(request);// ISO-8859-1
			if (UserPropUtil.isUserRegisted(userInfo.getIdcard())) {
				msgInfo.setStatus(Constant.MSG_STATUS_FAIL);
				msgInfo.setMsg(Constant.MSG_REGIST_FAIL);
				System.out.println("【用户[" + userInfo.getName() + "]非法注册】");
			} else {
				// 未注册，进行审批
				String isAgreeFunc = "isAgree";
				ClientComet.getInstance().callClient(
						new Javascript(isAgreeFunc, JSONObject
								.toJSONString(userInfo)));
				msgInfo.setStatus(Constant.MSG_STATUS_ING);
				msgInfo.setMsg(Constant.MSG_REGIST_ING);
				System.out.println("【用户[" + userInfo.getName() + "]等待审批】");
			}
		} else if ("registed".equals(method)) {
			// 审批通过，注册
			UserInfo userInfo = RequestUtil.toUserInfo2(request);
			UserPropUtil.addUserInfo(userInfo);
			msgInfo.setStatus(Constant.MSG_STATUS_SUCC);
			msgInfo.setMsg(Constant.MSG_REGIST_SUCC);
			System.out.println("【用户[" + userInfo.getName() + "]注册成功】");
		}
		ResponseUtil.write(response, msgInfo);
	}
}
