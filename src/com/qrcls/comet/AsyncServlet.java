package com.qrcls.comet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qrcls.constant.Constant;

/**
 * AsyncServlet
 * 
 * 支持异步处理的Servlet 页面中隐藏的iframe通过访问此Servlet来建立HTTP长连接 从而后台能实时的推送javascript代码给页面调用
 * 
 */
@WebServlet(urlPatterns = "/Async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

	private static final long serialVersionUID = 822178713133426493L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		// 获取异步上下文
		final AsyncContext actx = req.startAsync();
		// 设置超时时间
		actx.setTimeout(Constant.DEFAULT_TIME_OUT);
		// 增加异步监听事件
		actx.addListener(new AsyncListener() {

			@Override
			public void onComplete(AsyncEvent arg0) throws IOException {
//				ClientComet.getInstance().removeAsyncContext(actx);
				System.out.println("【AsyncListener-->onComplete】");
			}

			@Override
			public void onError(AsyncEvent arg0) throws IOException {
//				ClientComet.getInstance().removeAsyncContext(actx);
				System.out.println("【AsyncListener-->onError】");
			}

			@Override
			public void onStartAsync(AsyncEvent arg0) throws IOException {
				System.out.println("【AsyncListener-->onStartAsync】");
			}

			@Override
			public void onTimeout(AsyncEvent arg0) throws IOException {
				// onTimeout -> onComplete
				System.out.println("【AsyncListener-->onTimeout】");
			}

		});
		// 管理异步上下文并开启轮询线程
		ClientComet.getInstance().addAsyncContext(actx);
		System.out.println("【长连接["+actx+"]建立】");
	}
}