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
//		//onpage 函数
//		out.println("<script type='text/javascript' language='javascript'>");
//		out.println("function onPage(){var pageyenumber=document.getElementById('page');"//得到输入框的值，id的名称
//				+ "window.alert('p='+pageyenumber.value);"
//				+ "window.open('/UserManager/ManagerUser?pageNow='+pageyenumber.value,'_self');}");//把id中的值传入到pageyenumber中 
//		out.println("</script>");
//		out.println("<h1>管理用户界面</h1>");
//		//从数据库中取出用户信息，并显示在前台
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		Driver driver= null;
//		
//		//定义分页需要的变量
//		int pageNow=2;//当前页
//		int pageSize=3;//每页显示多少记录
//		int pageCount=1;//该值通过算法计算出来
//		int rowCount=1;//表示共有多少记录，从数据库中查询出来
//		String strpageNow = (String) request.getParameter("pageNow");//得到url传递的页码；
//		System.out.println(strpageNow);
//		if(strpageNow!=null){
//		pageNow=Integer.parseInt(strpageNow);
//		System.out.println(pageNow);
//		}
//		try {
//			driver = new com.mysql.cj.jdbc.Driver(); //加载驱动
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		/**
//		 * 得到连接
//		 */
//		String url = "jdbc:mysql://localhost:3308/usermanager?serverTimezone = GMT";
//		Properties info = new Properties();
//		info.put("user", "root");
//		info.put("password", "111111");
//		try {
//			conn=driver.connect(url, info);//得到连接
//			System.out.println("-==========================");
//			System.out.println(conn);
//			//创建preparestatement
//			
//			String sql = "select count(*) from user";
//			ps=conn.prepareStatement(sql);
//			rs=ps.executeQuery();
//			rs.next();//游标往下移才有记录；
//			rowCount = rs.getInt(1);//得到rowcount
//			//System.out.println(rowCount);
//			
//		
//			int pageNowt =( pageNow -1)*3;
//			//System.out.println(pageNowt);
//			pageCount = rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
//			String sql2 = "select * from user limit "+pageNowt+",3";
//			System.out.println(sql2);
//			ps=conn.prepareStatement(sql2);
//			rs=ps.executeQuery();//执行操作。
//			out.println("<table border=1 bordercolor=red cellspacing=0 width=500px  >");
//			out.println("<tr><th>id</th> <th>用户名</th> <th>电子邮件</th> <th>级别 </th></tr>");
//			while(rs.next()){
//				out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2) +"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td></tr>");
//			}
//			
//			
//			
//			out.println("</table>");
//			out.println("<br/>");
//			/**
//			 * 分页
//			 */
//			
//			
//			if(pageNow>1){
//			out.println("<a href='/UserManager/ManagerUser?pageNow="+(pageNow-1)+"'>上一页</a>");
//			}
//			//如果当前页数比1大 上一页
//			 
//			
//			if(pageNow<pageCount){
//			    out.println("<a href='/UserManager/ManagerUser?pageNow="+(pageNow+1)+"'>下一页</a>");
//			}//如果当前页数小于总页数 下一页
//			
//			out.println("&nbsp;&nbsp;&nbsp;&nbsp;当前页数："+pageNow+"/总页数"+pageCount);
//			out.println("<br/>");
//			
//			out.println("跳转到第几<input type='text' id='page' name='page'>页 <input type='button' onClick='onPage()' value='跳转'/>");//让这个按钮
//			//响应一个函数
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
		int pageNow=1;//当前页
		int pageSize=3;//每页显示多少记录
		
		int rowCount=1;//表示共有多少记录，从数据库中查询出来
		
		String strpageNow= request.getParameter("pageNow");
		if(strpageNow!=null){
			pageNow=Integer.parseInt(strpageNow);
		}
		System.out.println(pageNow);
		
		//onpage 函数
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function onPage(){var pageyenumber=document.getElementById('page');"//得到输入框的值，id的名称
				+ "window.alert('p='+pageyenumber.value);"
				+ "window.open('/UserManager/ManagerUser?pageNow='+pageyenumber.value,'_self');}"
				+ "function isConfirm(){return window.confirm('确认要删除该用户吗？');}");//把id中的值传入到pageyenumber中 
		
		out.println("</script>");
		out.println("<h1>管理用户界面</h1>");
		
		UserService userservice = new UserService();
		ArrayList<User> listuser = userservice.getUserByPage(pageNow, pageSize);
		
		
		//String strpageNow = (String) request.getParameter("pageNow");//得到url传递的页码；
		out.println("<table border=1 bordercolor=red cellspacing=0 width=500px  >");
		out.println("<tr><th>id</th> <th>用户名</th> <th>电子邮件</th> <th>级别 </th><th>删除用户</th><th>修改用户<th></tr>");
		for(User u : listuser){
			out.println("<tr><td>"+u.getId()+"</td><td>"+u.getName() +
					"</td><td>"+u.getEmail()+"</td><td>"+u.getGrade()+
					"</td><td><a onClick='return isConfirm();' href='/UserManager/UserControlServlet?type=delete&id="+u.getId()+"'>删除用户"
							+ "</a></td><td><a href='/UserManager/UserControlServlet?type=gotoupdateview&id="+u.getId()+"'>修改用户</a></td></tr>");
		}
		
		
		
//		while(rs.next()){
//			out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2) +"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td></tr>");
//		}
		out.print("</table>");
		out.println("</br>");
		//分页
		 int pageCount = userservice.getPageCount(pageSize);
		// System.out.println("吴志新");
		 //上一页
		if(pageNow>1){
			out.println("<a href='/UserManager/ManagerUser?pageNow="+(pageNow-1)+"'>上一页</a>");
			out.println("&nbsp;");
		}
		//总页
		for(int i=1;i<=pageCount;i++){
			out.println("<a href='/UserManager/ManagerUser?pageNow="+i+"'><"+i+"> </a>");
		}
		out.println("&nbsp;");
		if(pageNow<pageCount){
			out.println("<a href='/UserManager/ManagerUser?pageNow="+(pageNow+1)+"'>下一页</a>");
		}
		
		out.println("跳转到第<input type='text' name='ye' id='page'>页 <input type='button' value='跳转' onClick='onPage()'>");
		out.println("当前页"+pageNow+"/"+"总页数"+pageCount);
		
	}
}
