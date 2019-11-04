<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.wuzhixin.DoMain.*,java.util.*,java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


 <%-- 获取控制器中所有的数据 session request域中的数据--%>
<%
	User user = (User) request.getSession().getAttribute("loginuser");
	if(user==null){
		request.setAttribute("err", "请先登录！");
		request.getRequestDispatcher("/LoginServlet").forward(request, response);
		return;
	}
	
	String username = request.getParameter("username");
	String userinfo = request.getParameter("issaveuser");
	if(userinfo!=null){
		Cookie cookie1 = new Cookie("username",username);
		cookie1.setMaxAge(7*2*24*3600);
		response.addCookie(cookie1);
	}
	boolean b = false;
	String str = null;
	String nowTime1 =null;
	Cookie[] cookies = request.getCookies();
	if(cookies!=null){
		for(Cookie cookiess:cookies){
			String name = cookiess.getName();
			if("lastTime".equals(name)){
				nowTime1 = cookiess.getValue();
				b =true;
			}
		}
	}
	if(!b){
		 str ="您是第一次登陆....";
	}else{
		
		str="你上次登陆的时间是："+nowTime1;
	}
		

%>

	<img src='images/11.jpg'/><h2>欢迎<h1><%=username %></h1></h2><%=str %><br>
	<a href='http://localhost:8080/UserManager/WEB-INF/Login.jsp'>返回重新登陆界面</a>
	<hr>
	
	<h3>请选择你要进行的操作</h3>
	<a href='/UserManager/ManagerControl'>管理用户</a><br/>
	<a href=''>添加用户</a><br/>
	<a href=''>查找用户</a><br/>
	<a href=''>退出系统</a><br>
	
	
	
	<%--如果登陆成功 把第一次登陆的时间写入cookie 并设置生命周期，用response返回浏览器 --%>
	
	<%
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd:HH:mm:ss");
		String nowTime = simpleDateFormat.format(new Date());
		Cookie cookie = new Cookie("lastTime",nowTime);
		cookie.setMaxAge(3600*7*24);
		response.addCookie(cookie);
	%>
	
	
	
	

</body>
</html>