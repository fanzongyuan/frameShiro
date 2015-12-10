<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYpE html pUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>
<link rel="stylesheet" href="<%=basePath%>common/css/login/login.css" />
<script src="<%=basePath%>common/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="<%=basePath%>common/js/login/login.js" type="text/javascript"></script>
</head>
<body>
	<form id="loginForm" action="<%=basePath%>login" method="post">
		<div class="top_div"></div>
		<div class="content">
			<div class="cat">
				<div class="tou"></div>
				<div class="initial_left_hand" id="left_hand"></div>
				<div class="initial_right_hand" id="right_hand"></div>
			</div>
			<p class="p_error mt20">
				<span class="span_error uerror">&nbsp;</span>
			</p>
			<p class="pInput">
				<span class="licon u_logo"></span> <span class="inputTip"> 
				<span class="tip">请输入用户名</span> 
					<input class="ipt" id="username" name="userName" type="text" value="" />
				</span>
			</p>
			<p class="p_error">
				<span class="span_error perror">&nbsp;</span>
			</p>
			<p class="pInput">
				<span class="licon p_logo"></span> <span class="inputTip"> 
				<span class="tip">请输入密码</span> 
					<input class="ipt" id="password" name="password" type="password" value="" />
				</span>
			</p>
			<div class="passwordMain">
				<span class="fl"> </span> <span style="margin: 0 auto;"> 
					<input id="btnlogin" class="loginBtn" type="submit" value="登录"/>
				</span>
			</div>
		</div>
	</form>
</body>
</html>
