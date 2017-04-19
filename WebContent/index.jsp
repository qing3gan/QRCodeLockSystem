<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="jquery.min.js"></script>
<script src="jquery.qrcode.min.js"></script>
<script src="json2.js"></script>
<style type="text/css">
body {
	padding: 0px;
	margin: 0px;
}

div {
	padding: 10px;
}
</style>
<title>QRCodeLockSystem</title>
</head>
<body>
	<h3 align="center">用户门禁系统</h3>
	<div align="center">
		<form id="input">
			<label>姓名：</label><input type="text" name="name" id="name" /> <label>密码：</label><input
				type="password" name="password" id="password" /> <label>身份证：</label><input
				type="text" name="idcard" id="idcard" />
		</form>
		<button onclick="genQRCode()">生成门禁二维码</button>
	</div>
	<div id="output" align="center"></div>
	<h2 id="isPass" align="center"></h2>
	<iframe src="Async" style="display: none;"> </iframe>
</body>
<script>
	// 生成二维码
	function genQRCode() {
		// 防止重复生成二维码
		if ($("#output").html() != "") {
			$("#output").html("");
		}
		// 构造json对象
		var jsonObj = {};
		jsonObj.name = utf16to8($("#name").val());
		jsonObj.password = utf16to8($("#password").val());
		jsonObj.idcard = utf16to8($("#idcard").val());
		// 将json对象解析为json字符串
		var jsonStr = JSON.stringify(jsonObj);
		// 将json字符串转换为二维码
		$("#output").qrcode(jsonStr);
	}

	// utf16 转 utf8编码，用于支持中文二维码的编解码
	function utf16to8(str) {
		var out, i, len, c;
		out = "";
		len = str.length;
		for (i = 0; i < len; i++) {
			c = str.charCodeAt(i);
			if ((c >= 0x0001) && (c <= 0x007F)) {
				out += str.charAt(i);
			} else if (c > 0x07FF) {
				out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
				out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
				out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
			} else {
				out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
				out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
			}
		}
		return out;
	}

	// 推送回调
	function isPass(str) {
		$("#isPass").text(str);
	}
</script>
</html>