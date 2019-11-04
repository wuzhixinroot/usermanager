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
		}else if("gotoupdateview".equals(type)){ //�õ�type ��equals
			String id = request.getParameter("id");//�õ�id
			User user = userservice.getUserById(id);
			//Ϊ���ÿ��Ʋ㲻������ ���ݣ����ǰ�user���󴫵ݵ���һ���޸�ҳ�档
			request.setAttribute("userInfo",user);//���ﲻ����response �ض��� ���ض���
			//�� �����������������request����ͬһ������������foward
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
