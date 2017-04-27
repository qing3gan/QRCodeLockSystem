<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="common.jsp"></jsp:include>
<title>QRCodeLockSystem</title>
<style type="text/css">
div {
	margin: 20px;
}

.backgroud {
	background-image: url("images/door.jpg");
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
<body class="backgroud">
	<h3 align="center">用户门禁系统</h3>
	<div class="container">
		<div class="row">
			<!-- <span class="col-md-2 col-md-offset-10" id="applyList">申请列表</span> -->
		</div>
		<div class="row">
			<button class="col-md-2 col-md-offset-5" onclick="genQRCode()">生成门禁二维码</button>
		</div>
		<div class="row" id="output" align="center"></div>
		<div class="row" id="isPass" align="center"></div>
	</div>
	<iframe id="async" src="Async" style="display: none;"> </iframe>
</body>
<script src="public/qrcode/jquery.qrcode.min.js"></script>
<script src="public/json/json2.js"></script>
<script>
	// 生成二维码
	function genQRCode() {
		// 防止重复生成二维码
		if ($("#output").html() != "") {
			$("#output").html("");
		}
		$.post("QRCodeServlet?method=genQRCode", function(userInfo) {
			if (userInfo != null && userInfo.length != 0) {
				var jsonArray = [];
				for (var i = 0; i < userInfo.length; i++) {
					// 构造json对象
					var jsonObj = {};
					jsonObj.idcard = utf16to8(userInfo[i].idcard);
					jsonObj.name = utf16to8(userInfo[i].name);
					jsonObj.password = utf16to8(userInfo[i].password);
					jsonArray.push(jsonObj);
				}
				// 将json对象解析为json字符串
				var jsonStr = JSON.stringify(jsonArray);
				// 将json字符串转换为二维码
				$("#output").qrcode(jsonStr);
			}
		},"json");// 手动判断返回数据格式类型
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

	// 审批回调
	function isAgree(userInfo) {
		if (confirm("是否同意[" + userInfo.name + "]的门禁申请?")) {
			$.post("RegistServlet?method=registed", userInfo, function(msg) {});
		}
	}

	// 推送回调
	function isPass(str) {
		$("#isPass").text(str);
	}
</script>
</html>