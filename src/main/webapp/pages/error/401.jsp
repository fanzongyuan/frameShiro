<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>401</title>
	<style type="text/css">
		*{ margin:0; padding:0; }
		body{ width:100%; height:100%;  background:url(<%=basePath%>common/images/error-background.png) repeat; }
		.concent{ width:650px; margin:50px auto; }
		.one{ width:650px; height:246px; position:relative; }
		.Arrow{ margin-top:30px; }
		.two{ width:650px; height:102px; margin-top:34px; }
		.three{ width:650px; height:102px; margin-top:34px; }
		.four{ width:650px; height:102px; margin-top:34px; background:url(<%=basePath%>common/images/Home-Arrow1.png) no-repeat; background-size:650px 105px; }
		.four p{ font-size:60px; color:#a04d4d; line-height:105px; text-align:center; font-weight:bold; font-family:'Times New Roman';}
	</style>
</head>
<body>
<!-- 整体 -->
	<div class="concent"> 
	<!-- 第一块 -->
		<div class="one">
			<img src="<%=basePath%>common/images/Funky-Arrow.png" alt="" class="Arrow" />
		</div>
	<!-- 第二块 -->
		<div class="two"> 
			<img src="<%=basePath%>common/images/here.png" alt=""  />
		</div>
	<!-- 第四块 -->
		<div class="four"> 
			<p>Back</p>
		</div>
	</div>
</body>
</html>