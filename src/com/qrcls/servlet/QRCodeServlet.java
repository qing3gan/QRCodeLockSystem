package com.qrcls.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qrcls.comet.ClientComet;
import com.qrcls.comet.Javascript;

/**
 * 访问此Servlet能够给ClientComet的mesgQueue添加对象
 */
@WebServlet(urlPatterns = "/QRCodeServlet")
public class QRCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String isPassFunc = "isPass";
		String isPass = request.getParameter("isPass");
		if ("true".equalsIgnoreCase(isPass)) {
			ClientComet.getInstance().callClient(
					new Javascript(isPassFunc+"('Open Success')"));
		} else if ("false".equalsIgnoreCase(isPass)) {
			ClientComet.getInstance().callClient(
					new Javascript(isPassFunc+"('Open Fail')"));
		} else {
			ClientComet.getInstance().callClient(
					new Javascript(isPassFunc+"('Illegal Param')"));
		}
		PrintWriter writer = response.getWriter();
		writer.write("{\"status\":\"1\",\"msg\":\"success\"}");
		writer.flush();
		System.out.println("QRCodeServlet-->callClient");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
