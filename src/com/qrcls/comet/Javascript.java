package com.qrcls.comet;

import java.io.UnsupportedEncodingException;

/**
 * Javascript
 * 
 * �ṩ��javascript�ķ������ò�ȫ��һ��script��ǩ�еĹ���
 */
public class Javascript {
	private String script;

	public Javascript(String func) {
		script = "<script type='text/javascript'>" + "\n" + "window.parent."
				+ func + "\n" + "</script>" + "\n";
	}

	public String getScript() {
		return script;
	}
}