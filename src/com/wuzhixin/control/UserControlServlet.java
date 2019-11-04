package com.wuzhixin.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuzhixin.DoMain.User;
import com.wuzhixin.model.UserService;

/**
 * Servlet implementation class DeleteControlServlet
 */
public class UserControlServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;chaset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		UserService userservice = new UserService();
		if("delete".equals(type)){
			String id = request.getParameter("id");
			if(userservice.deleteUser(id)){
			request.getRequestDispatcher("/OK").forward(request, response);
			}else{
			request.getRequestDispatcher("/Err").forward(request, response);
			} 
		}else if("gotoupdateview".equals(type)){ //得到type 再equals
			String id = request.getParameter("id");//得到id
			User user = userservice.getUserById(id);
			//为了让控制层不不处理 数据，我们把user对象传递到下一个修改页面。
			request.setAttribute("userInfo",user);//这里不能用response 重定向 ，重定向
			//后 浏览器发送两次请求，request不是同一个对象，这里用foward
			request.getRequestDispatcher("/UpdateUserView").forward(request, response);
			
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
