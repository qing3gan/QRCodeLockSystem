package com.qrcls.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qrcls.comet.ClientComet;
import com.qrcls.comet.Javascript;
import com.qrcls.constant.Constant;
import com.qrcls.entity.MsgInfo;
import com.qrcls.entity.UserInfo;
import com.qrcls.util.ResponseUtil;
import com.qrcls.util.UserPropUtil;

/**
 * 访问此Servlet能够给ClientComet的mesgQueue添加对象
 */
@WebServlet(urlPatterns = "/QRCodeServlet")
public class QRCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("scanQRCode".equals(method)) {
			scanQRCodeLogic(request, response);
		} else if ("genQRCode".equals(method)) {
			genQRCodeLogic(request, response);
		}
	}

	private void scanQRCodeLogic(HttpServletRequest request,
			HttpServletResponse response) {
		MsgInfo msgInfo = new MsgInfo();
		String isPassFunc = "isPass";
		String isPass = request.getParameter("isPass");
		if ("true".equalsIgnoreCase(isPass)) {
			Integer openCount = (Integer) request.getServletContext()
					.getAttribute("openCount");
			ClientComet.getInstance().callClient(
					new Javascript(isPassFunc, Constant.MSG_OPEN_SUCC + "+'"
							+ ++openCount + "次'"));
			request.getServletContext().setAttribute("openCount", openCount);
			msgInfo.setStatus(Constant.MSG_STATUS_SUCC);
			msgInfo.setMsg(Constant.MSG_OPEN_SUCC);
		} else if ("false".equalsIgnoreCase(isPass)) {
			ClientComet.getInstance().callClient(
					new Javascript(isPassFunc, Constant.MSG_OPEN_FAIL));
			msgInfo.setStatus(Constant.MSG_STATUS_FAIL);
			msgInfo.setMsg(Constant.MSG_OPEN_FAIL);
		} else {
			ClientComet.getInstance().callClient(
					new Javascript(isPassFunc, Constant.MSG_OPEN_ILLEGAL));
			msgInfo.setStatus(Constant.MSG_STATUS_FAIL);
			msgInfo.setMsg(Constant.MSG_OPEN_ILLEGAL);
		}
		ResponseUtil.write(response, msgInfo);
	}

	private void genQRCodeLogic(HttpServletRequest request,
			HttpServletResponse response) {
		List<UserInfo> userInfos = UserPropUtil.getAllUserInfo();
		ResponseUtil.write(response, userInfos);
	}

}
