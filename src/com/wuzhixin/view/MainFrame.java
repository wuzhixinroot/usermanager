package com.wuzhixin.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuzhixin.DoMain.User;
import com.wuzhixin.staticdata.StaticData;

/**
 * Servlet implementation class MainFrame
 */
public class MainFrame extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		User user = (User) request.getSession().getAttribute("loginuser");
		if(user==null){
			request.setAttribute("err", "���ȵ�¼��");
			request.getRequestDispatcher("/LoginServlet").forward(request, response);
			return;
		}
//		User user = (User) request.getSession().getAttribute("objuser");
//		String u = user.getName();
//		String p = user.getPwd();
//		out.println("<h1>������</h1>"+request.getParameter("username")+request.getParameter("pwd")+request.getSession().getAttribute("un")+"\n"+
//	u+
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
			 str ="���ǵ�һ�ε�½....";
		}else{
			
			str="���ϴε�½��ʱ���ǣ�"+nowTime1;
		}
		
		out.println("<img src='images/11.jpg'/><h2>��ӭ<h1>"+request.getParameter("username")+"</h1></h2>"+str+"</br><a href='http://localhost:8080/UserManager/LoginServlet'>����"
				+ "���µ�½����</a><hr/>");
		//out.println("<a href='http://localhost:8080/UserManager/LoginServlet'>�������µ�½����</a>");
		//out.println(x);
		out.println("<h3>��ѡ����Ҫ���еĲ���</h3>");
		out.println("<a href='/UserManager/ManagerUser'>�����û�</a><br/>");
		out.println("<a href=''>����û�</a><br/>");
		out.println("<a href=''>�����û�</a><br/>");
		out.println("<a href=''>�˳�ϵͳ</a<br/>>");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd:HH:mm:ss");
		String nowTime = simpleDateFormat.format(new Date());
		Cookie cookie = new Cookie("lastTime",nowTime);
		cookie.setMaxAge(3600*7*24);
		response.addCookie(cookie);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
