<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.wuzhixin.DoMain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<table border=1 bordercolor=red cellspacing=0 width=500px  >
		<tr><th>id</th> <th>用户名</th> <th>电子邮件</th> <th>级别 </th><th>删除用户</th><th>修改用户<th></tr>
		
		<%--得到用户集合 循环显示 --%>
		<%
			ArrayList<User> userList =(ArrayList<User>) request.getAttribute("userList");
			for(User user:userList){
				%>
				<tr>
					<td><%=user.getId() %></td>
					<td><%=user.getName() %></td>
					<td><%=user.getEmail() %></td>
					<td><%=user.getGrade() %></td>
					<td>删除用户</td>
					<td>修改用户</td>
				</tr>
				<% 
			}
		%>
		
		</table><br>
		
		<%--接收 页数 --%>>
		<%
		int pageCount =(Integer)request.getAttribute("pageCount");
		int pageNow = (Integer)request.getAttribute("pageNow");
		
		%>
		
		 <%--如果页数大于1则有上一页 --%>
		<%
			if(pageNow>1){
				int newPageNow = pageNow-1;
		%>
		
		<a href="/UserManager/ManagerControl?pageNow=<%=newPageNow%> ">上一页</a>
		<% 
		  }
		%>
		
		
		<%--得到总页数 分页 --%>
		<%
			
			for(int i=1;i<=pageCount;i++){
				%>
				<a href="/UserManager/ManagerControl?pageNow=<%=i %>"><<%=i %>></a>
				<% 
			}
		%>
		
		<%--如果页数小于总页数 则有下一页 --%>
		
		
		<%
			if(pageCount>pageNow){
				int newPageNow = pageNow+1;
		%>
		
		 <a href="/UserManager/ManagerControl?pageNow=<%=newPageNow %> ">下一页</a>
		<% 
		  }
		%>
		
		
		
		

</body>
</html>