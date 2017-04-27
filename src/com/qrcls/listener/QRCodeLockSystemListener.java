package com.qrcls.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.qrcls.util.UserPropUtil;

/**
 * 应用Servlet监听器
 */
@WebListener
public class QRCodeLockSystemListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent arg0) {
		// 门禁开门次数
		arg0.getServletContext().setAttribute("openCount", 0);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// 服务器关闭时同步内存数据至磁盘
		while (!UserPropUtil.storeToDisk());
		System.out.println("【保存用户信息至磁盘】");
	}

}
