package com.qrcls.comet;

import java.io.UnsupportedEncodingException;

/**
 * Javascript
 * 
 * 提供将javascript的方法调用补全到一个script标签中的功能
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