package com.qrcls.comet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AsyncServlet
 * 
 * ֧���첽�����Servlet ҳ�������ص�iframeͨ�����ʴ�Servlet������HTTP������ �Ӷ���̨��ʵʱ������javascript�����ҳ�����
 * 
 */
@WebServlet(urlPatterns = "/Async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

	private static final long serialVersionUID = 822178713133426493L;
	/**
	 * ��ʱʱ�䣨Сʱ��
	 */
	private final static int DEFAULT_TIME_OUT = 1 * 60 * 60 * 1000;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		// ��ȡ�첽������
		final AsyncContext actx = req.startAsync();
		// ���ó�ʱʱ��
		actx.setTimeout(DEFAULT_TIME_OUT);
		// �����첽�����¼�
		actx.addListener(new AsyncListener() {

			@Override
			public void onComplete(AsyncEvent arg0) throws IOException {
				ClientComet.getInstance().removeAsyncContext(actx);
				System.out.println("AsyncListener-->onComplete");
			}

			@Override
			public void onError(AsyncEvent arg0) throws IOException {
				ClientComet.getInstance().removeAsyncContext(actx);
				System.out.println("AsyncListener-->onError");
			}

			@Override
			public void onStartAsync(AsyncEvent arg0) throws IOException {
				System.out.println("AsyncListener-->onStartAsync");
			}

			@Override
			public void onTimeout(AsyncEvent arg0) throws IOException {
				ClientComet.getInstance().removeAsyncContext(actx);
				System.out.println("AsyncListener-->onTimeout");
			}

		});
		// �����첽�����Ĳ�������ѯ�߳�
		ClientComet.getInstance().addAsyncContext(actx);
	}
}