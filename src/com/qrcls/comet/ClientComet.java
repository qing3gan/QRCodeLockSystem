package com.qrcls.comet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;

/**
 * ClientComet
 * 
 * �����û���AsyncContext(��ӡ�ɾ��)
 * 
 * ͨ������һ���߳������ϵش�mesgQueue��ȡjavascript �������û���AsyncContext����javascript���͸�ÿ���û�
 * 
 */
public class ClientComet {
	private static ClientComet instance;
	/**
	 * �ͻ����첽�����Ķ���
	 */
	private ConcurrentLinkedQueue<AsyncContext> actxQueue;
	/**
	 * ������Ϣ����
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
	 * ������Ϣ
	 * 
	 * @param javascript
	 */
	public void callClient(Javascript javascript) {
		mesgQueue.add(javascript);
	}

	/**
	 *	��ѯ�߳�
	 */
	protected class ClientCometThread extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					Javascript javascript = mesgQueue.take();
					for (AsyncContext actx : actxQueue) {
						PrintWriter writer = actx.getResponse().getWriter();
						writer.write(javascript.getScript());
						writer.flush();
						System.out
								.println("ClientCometThread-->sendJavaScript");
					}
				} catch (InterruptedException | IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

}