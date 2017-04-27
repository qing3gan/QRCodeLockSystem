package com.qrcls.comet;

/**
 * Javascript
 * 
 * 提供将javascript的方法调用补全到一个script标签中的功能
 */
public class Javascript {
	private String script;

	public Javascript(String func, String param) {
		String function = func + "(" + param + ")";
		script = "<script type='text/javascript'>" + "\n" + "window.parent."
				+ function + "\n" + "</script>" + "\n";
		System.out.println("【调用JS回调函数[" + function + "]】");
	}

	public String getScript() {
		return script;
	}
}