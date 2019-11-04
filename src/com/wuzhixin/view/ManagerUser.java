package com.wuzhixin.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;
import com.wuzhixin.DoMain.User;
import com.wuzhixin.model.UserService;

/**
 * Servlet implementation class ManagerUser
 */
public class ManagerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		//onpage ����
//		out.println("<script type='text/javascript' language='javascript'>");
//		out.println("function onPage(){var pageyenumber=document.getElementById('page');"//�õ�������ֵ��id������
//				+ "window.alert('p='+pageyenumber.value);"
//				+ "window.open('/UserManager/ManagerUser?pageNow='+pageyenumber.value,'_self');}");//��id�е�ֵ���뵽pageyenumber�� 
//		out.println("</script>");
//		out.println("<h1>�����û�����</h1>");
//		//�����ݿ���ȡ���û���Ϣ������ʾ��ǰ̨
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		Driver driver= null;
//		
//		//�����ҳ��Ҫ�ı���
//		int pageNow=2;//��ǰҳ
//		int pageSize=3;//ÿҳ��ʾ���ټ�¼
//		int pageCount=1;//��ֵͨ���㷨�������
//		int rowCount=1;//��ʾ���ж��ټ�¼�������ݿ��в�ѯ����
//		String strpageNow = (String) request.getParameter("pageNow");//�õ�url���ݵ�ҳ�룻
//		System.out.println(strpageNow);
//		if(strpageNow!=null){
//		pageNow=Integer.parseInt(strpageNow);
//		System.out.println(pageNow);
//		}
//		try {
//			driver = new com.mysql.cj.jdbc.Driver(); //��������
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		/**
//		 * �õ�����
//		 */
//		String url = "jdbc:mysql://localhost:3308/usermanager?serverTimezone = GMT";
//		Properties info = new Properties();
//		info.put("user", "root");
//		info.put("password", "111111");
//		try {
//			conn=driver.connect(url, info);//�õ�����
//			System.out.println("-==========================");
//			System.out.println(conn);
//			//����preparestatement
//			
//			String sql = "select count(*) from user";
//			ps=conn.prepareStatement(sql);
//			rs=ps.executeQuery();
//			rs.next();//�α������Ʋ��м�¼��
//			rowCount = rs.getInt(1);//�õ�rowcount
//			//System.out.println(rowCount);
//			
//		
//			int pageNowt =( pageNow -1)*3;
//			//System.out.println(pageNowt);
//			pageCount = rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
//			String sql2 = "select * from user limit "+pageNowt+",3";
//			System.out.println(sql2);
//			ps=conn.prepareStatement(sql2);
//			rs=ps.executeQuery();//ִ�в�����
//			out.println("<table border=1 bordercolor=red cellspacing=0 width=500px  >");
//			out.println("<tr><th>id</th> <th>�û���</th> <th>�����ʼ�</th> <th>���� </th></tr>");
//			while(rs.next()){
//				out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2) +"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td></tr>");
//			}
//			
//			
//			
//			out.println("</table>");
//			out.println("<br/>");
//			/**
//			 * ��ҳ
//			 */
//			
//			
//			if(pageNow>1){
//			out.println("<a href='/UserManager/ManagerUser?pageNow="+(pageNow-1)+"'>��һҳ</a>");
//			}
//			//�����ǰҳ����1�� ��һҳ
//			 
//			
//			if(pageNow<pageCount){
//			    out.println("<a href='/UserManager/ManagerUser?pageNow="+(pageNow+1)+"'>��һҳ</a>");
//			}//�����ǰҳ��С����ҳ�� ��һҳ
//			
//			out.println("&nbsp;&nbsp;&nbsp;&nbsp;��ǰҳ����"+pageNow+"/��ҳ��"+pageCount);
//			out.println("<br/>");
//			
//			out.println("��ת���ڼ�<input type='text' id='page' name='page'>ҳ <input type='button' onClick='onPage()' value='��ת'/>");//�������ť
//			//��Ӧһ������
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
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
//		  }
//		}
//		
//		
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int pageNow=1;//��ǰҳ
		int pageSize=3;//ÿҳ��ʾ���ټ�¼
		
		int rowCount=1;//��ʾ���ж��ټ�¼�������ݿ��в�ѯ����
		
		String strpageNow= request.getParameter("pageNow");
		if(strpageNow!=null){
			pageNow=Integer.parseInt(strpageNow);
		}
		System.out.println(pageNow);
		
		//onpage ����
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function onPage(){var pageyenumber=document.getElementById('page');"//�õ�������ֵ��id������
				+ "window.alert('p='+pageyenumber.value);"
				+ "window.open('/UserManager/ManagerUser?pageNow='+pageyenumber.value,'_self');}"
				+ "function isConfirm(){return window.confirm('ȷ��Ҫɾ�����û���');}");//��id�е�ֵ���뵽pageyenumber�� 
		
		out.println("</script>");
		out.println("<h1>�����û�����</h1>");
		
		UserService userservice = new UserService();
		ArrayList<User> listuser = userservice.getUserByPage(pageNow, pageSize);
		
		
		//String strpageNow = (String) request.getParameter("pageNow");//�õ�url���ݵ�ҳ�룻
		out.println("<table border=1 bordercolor=red cellspacing=0 width=500px  >");
		out.println("<tr><th>id</th> <th>�û���</th> <th>�����ʼ�</th> <th>���� </th><th>ɾ���û�</th><th>�޸��û�<th></tr>");
		for(User u : listuser){
			out.println("<tr><td>"+u.getId()+"</td><td>"+u.getName() +
					"</td><td>"+u.getEmail()+"</td><td>"+u.getGrade()+
					"</td><td><a onClick='return isConfirm();' href='/UserManager/UserControlServlet?type=delete&id="+u.getId()+"'>ɾ���û�"
							+ "</a></td><td><a href='/UserManager/UserControlServlet?type=gotoupdateview&id="+u.getId()+"'>�޸��û�</a></td></tr>");
		}
		
		
		
//		while(rs.next()){
//			out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2) +"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td></tr>");
//		}
		out.print("</table>");
		out.println("</br>");
		//��ҳ
		 int pageCount = userservice.getPageCount(pageSize);
		// System.out.println("��־��");
		 //��һҳ
		if(pageNow>1){
			out.println("<a href='/UserManager/ManagerUser?pageNow="+(pageNow-1)+"'>��һҳ</a>");
			out.println("&nbsp;");
		}
		//��ҳ
		for(int i=1;i<=pageCount;i++){
			out.println("<a href='/UserManager/ManagerUser?pageNow="+i+"'><"+i+"> </a>");
		}
		out.println("&nbsp;");
		if(pageNow<pageCount){
			out.println("<a href='/UserManager/ManagerUser?pageNow="+(pageNow+1)+"'>��һҳ</a>");
		}
		
		out.println("��ת����<input type='text' name='ye' id='page'>ҳ <input type='button' value='��ת' onClick='onPage()'>");
		out.println("��ǰҳ"+pageNow+"/"+"��ҳ��"+pageCount);
		
	}
}
