<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="common.jsp"></jsp:include>
<title>注册</title>
</head>
<body>
	<form action="RegistServlet?method=registing" method="post">
		<input name="idcard" type="text" placeholder="身份证" /> <input
			name="name" type="text" placeholder="姓名" /> <input name="password"
			type="text" placeholder="密码" /> <input type="submit" value="注册" />
	</form>
</body>
</html>