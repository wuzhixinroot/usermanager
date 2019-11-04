package com.wuzhixin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//action 表示提交给哪个servlet处理  应该这样写/web应用名/servlet的url 
		//response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");//告诉浏览器用字符编码为utf-8来解析
		PrintWriter out  = response.getWriter();
		String username = null;
		Cookie cookie[] = request.getCookies();
		if(cookie!=null){
			for( Cookie cookies : cookie){
				if("username".equals(cookies.getName())){
				 username = cookies.getValue();
				}
			}
		}
		
		
		
		out.println("<img src='images/11.jpg' /><hr/>");
		out.println("<h1> 用户登陆 </h1>");
		out.println("<form action = '/UserManager/LoginControlServlet' method ='post'>");
		out.println("用户名:<input type='text' name='username' value='"+username+"'/><br/>"); 
		out.println("密　码:<input type='password' name='password'/><br/>");
		out.println("请输入验证码:<input type='text' name='checkcode'/> <img src='/UserManager/CreateCode'></br> ");
		out.println("<input type='checkbox' name='issaveuser' value='userinfo'>是否保存账号到浏览器 </br>");
		out.println("<input type = 'submit' value='登陆'/><br/>");
		out.println("</form>");
	
		
		String errinfo = (String) request.getAttribute("err");
		System.out.println(errinfo);
		if(errinfo!=null){
			out.println("<font color='red'>"+errinfo+"</font>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
