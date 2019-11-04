package com.wuzhixin.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuzhixin.DoMain.User;

/**
 * Servlet implementation class UpdateUserView
 */
public class UpdateUserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		User user = (User) request.getAttribute("userInfo");
		out.println("<table border=1px bordercolor=red cellspacing=0 width=200px>");
		//out.println("<tr><th>ԭ����</th><th>�޸�����</th><</tr>");
		out.println("<tr><td>�˺�id</td><td><input type='text' name='id' value='"+user.getId()+"'/></td><</tr>");
		out.println("<tr><td>�û���</td><td><input type='text' name='username' value='"+user.getName()+"'/></td><</tr>");
		out.println("<tr><td>�����ʼ�</td><td><input type='text' name='email' value='"+user.getEmail()+"'/></td><</tr>");
		out.println("<tr><td>����</td><td><input type='text' name='grade' value='"+user.getGrade()+"'/></td><</tr>");
		out.println("<tr><td>����</td><td><input type='text' name='password' value='"+user.getPwd()+"'/></td><</tr>");
		out.println("</table>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
