package com.wuzhixin.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.Driver;
import com.wuzhixin.DoMain.User;
import com.wuzhixin.model.UserService;
import com.wuzhixin.staticdata.StaticData;



public class LoginControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("type/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//�����û��ύ���û���������
		String username =request.getParameter("username");
		String password =request.getParameter("password");
		
		//����֤��֤���Ƿ���ȷ����ȥ��֤���ݿ�
		String Scheckcode=(String) request.getSession().getAttribute("checkcode");
		String checkcode=request.getParameter("checkcode");
		System.out.println(Scheckcode);
		System.out.println(checkcode);
		System.out.println(username);
		System.out.println(password);
		if(Scheckcode.equals(checkcode)){
			
		
		
			UserService userservice = new UserService ();
			User user = new User();
			user.setName(username);
			user.setPwd(password);
			try {
				if(userservice.checkUser(user)){

					HttpSession session = request.getSession();
					session.setAttribute("loginuser", user);
					
					request.getRequestDispatcher("/WEB-INF/MainFrame.jsp").forward(request, response);
				}else{
					request.setAttribute("err", "��������û�������������");
					request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else{
			request.setAttribute("err", "���������֤�벻��ȷ����������");
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
		}
		
	}
//		System.out.println(username);
//		System.out.println(password);
//		Driver driver = null;
//		try {
//			driver = new com.mysql.cj.jdbc.Driver();//��������
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String url ="jdbc:mysql://localhost:3308/usermanager?serverTimezone = GMT";
//		Properties info = new Properties();
//		info.put("user", "root");
//		info.put("password", "111111");
//		Connection conn = null;
//		//����preparestatement66
//		PreparedStatement ps =null;
//		ResultSet rs = null;
//		try {
//			conn = driver.connect(url, info);//�õ�����
//			ps = conn.prepareStatement("select * from user where username=? and password=?");
//			ps.setString(1, username);
//			ps.setString(2, password);
//			rs=ps.executeQuery();
//			if(rs.next()){
//				request.getRequestDispatcher("/MainFrame").forward(request,response);
//			}else{
//				request.setAttribute("err", "��������û����������д�");
//				request.getRequestDispatcher("/LoginServlet").forward(request, response);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			
//			if(rs!=null){
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				rs=null;
//			}
//			if(conn!=null){
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				ps=null;
//			}
//			if(ps!=null){
//				try {
//					ps.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				ps=null;
//			}
//			
//			
//			
//			
			
			
			
			
			
			
			
			
			
			
		
		
		 
		//System.out.println("fwhifwiehf");
		
				
		//System.out.println(conn);
	
		
//		if("wuzhixin".equals(username)&&"111111".equals(password)){
//		//�����֤��ȷ�Ļ�����ת���¸�ҳ�� 
//			StaticData.name=username;
//			request.getSession().setAttribute("un", username);
//			
//			User user = new User();
//			request.getSession().setAttribute("objuser", user);
//			user.setName(username);
//			user.setPwd(password);
//			
//			response.sendRedirect("/UserManager/MainFrame?username="+username+"&pwd="+password);
//		}else{
//			response.sendRedirect("/UserManager/LoginServlet");
//		}
		
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
