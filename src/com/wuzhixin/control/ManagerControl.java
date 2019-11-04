package com.wuzhixin.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuzhixin.model.UserService;

public class ManagerControl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//准备数据 调用user service 
		PrintWriter out = response.getWriter();
		int pageSize = 3;
		int pageNow = 1;
		String newpageNow =(String)request.getParameter("pageNow");
		if(newpageNow!=null){
			pageNow =Integer.parseInt(newpageNow);
			System.out.println(pageNow);
		}
		UserService userservice = new UserService();
		int pageCount = userservice.getPageCount(pageSize);
		ArrayList  userList = userservice.getUserByPage(pageNow, pageSize);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("userList", userList);
		request.setAttribute("pageNow", pageNow);
		System.out.println("hfwhowe"+pageNow);
		
		request.getRequestDispatcher("/WEB-INF/ManagerUser.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
