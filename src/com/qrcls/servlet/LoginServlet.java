package com.qrcls.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qrcls.constant.Constant;
import com.qrcls.entity.MsgInfo;
import com.qrcls.entity.UserInfo;
import com.qrcls.util.RequestUtil;
import com.qrcls.util.ResponseUtil;
import com.qrcls.util.UserPropUtil;

/**
 * 登录Servlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MsgInfo msgInfo = new MsgInfo();
		UserInfo userInfo = RequestUtil.toUserInfo(request);
		// 存在，登录成功
		if (UserPropUtil.isUserRegisted(userInfo.getIdcard())) {
			UserInfo _userInfo = UserPropUtil.getUserInfoByIdcard(userInfo
					.getIdcard());
			if (_userInfo.getName().equals(userInfo.getName())
					&& _userInfo.getPassword().equals(userInfo.getPassword())) {
				msgInfo.setStatus(Constant.MSG_STATUS_SUCC);
				msgInfo.setMsg(Constant.MSG_LOGIN_SUCC);
			} else {
				msgInfo.setStatus(Constant.MSG_STATUS_FAIL);
				msgInfo.setMsg(Constant.MSG_LOGIN_UNCORRECT);
			}
			System.out.println("【用户[" + userInfo.getName() + "]登录】");
		} else {
			// 不存在，登录失败
			msgInfo.setStatus(Constant.MSG_STATUS_FAIL);
			msgInfo.setMsg(Constant.MSG_LOGIN_UNEXIST);
			System.out.println("【用户[" + userInfo.getName() + "]非法登录】");
		}
		ResponseUtil.write(response, msgInfo);
	}

}
