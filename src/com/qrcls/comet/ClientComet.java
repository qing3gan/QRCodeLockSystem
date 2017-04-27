package com.qrcls.comet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;

/**
 * ClientComet
 * 
 * 管理用户的AsyncContext(添加、删除)
 * 
 * 通过开启一个线程来不断地从mesgQueue获取javascript 并遍历用户的AsyncContext来吧javascript推送给每个用户
 * 
 */
public class ClientComet {
	private static ClientComet instance;
	/**
	 * 客户端异步上下文队列
	 */
	private ConcurrentLinkedQueue<AsyncContext> actxQueue;
	/**
	 * 推送消息队列
	 */
	private LinkedBlockingQueue<Javascript> mesgQueue;

	private ClientComet() {
		actxQueue = new ConcurrentLinkedQueue<AsyncContext>();
		mesgQueue = new LinkedBlockingQueue<Javascript>();
		new ClientCometThread().start();
	}

	public static ClientComet getInstance() {
		if (instance == null) {
			instance = new ClientComet();
		}
		return instance;
	}

	public void addAsyncContext(AsyncContext actx) {
		actxQueue.add(actx);
	}

	public void removeAsyncContext(AsyncContext actx) {
		actxQueue.remove();
	}

	/**
	 * 推送消息
	 * 
	 * @param javascript
	 */
	public void callClient(Javascript javascript) {
		mesgQueue.add(javascript);
	}

	/**
	 * 轮询线程
	 */
	protected class ClientCometThread extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					Javascript javascript = mesgQueue.take();
					for (AsyncContext actx : actxQueue) {
						actx.getResponse().setContentType(
								"text/html;charset=utf-8");
						PrintWriter writer = actx.getResponse().getWriter();
						writer.write(javascript.getScript());
						writer.flush();
						System.out.println("【长连接[" + actx + "]响应】");
					}
				} catch (InterruptedException | IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

}