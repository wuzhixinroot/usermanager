<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String username = null;
		Cookie cookie[] = request.getCookies();
		if(cookie!=null){
			for( Cookie cookies : cookie){
				if("username".equals(cookies.getName())){
				 username = cookies.getValue();
				}
			}
		}
	%>
	<img src='images/11.jpg' /><hr/>
	<h1> 用户登陆 </h1>
	<form action = '/UserManager/LoginControlServlet' method ='post'>
	用户名：<input type='text' name='username' value='<%=username%>'/><br/> 
	密　码:<input type='password' name='password'/><br/>
	请输入验证码:<input type='text' name='checkcode'/> <img src='/UserManager/CreateCode'><br> 
	<input type='checkbox' name='issaveuser' value='userinfo'>是否保存账号到浏览器 <br>
	<input type = 'submit' value='登陆'/><br/>
	</form>
	
	
	<%
		
		String errinfo = (String) request.getAttribute("err");
		System.out.println(errinfo);
		if(errinfo!=null){
			out.println("<font color='red'>"+errinfo+"</font>");
		}
	%>
	

	
</body>
</html>