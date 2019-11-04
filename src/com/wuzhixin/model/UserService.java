package com.wuzhixin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;
import com.wuzhixin.DoMain.User;
import com.wuzhixin.util.SqlHelper;

/**
 * ��֤�û��Ƿ���ȷ  ���շ�ҳ����ȡ�û�
 * @author ��־��
 *
 */

public class UserService { 
	//��ȡpagecount
	
	public int getPageCount(int pageSize){
		int rowCount=0;
		String sql="select count(*) from user";
		ResultSet rs =SqlHelper.executeQuery(sql, null);
		try {
			rs.next();
			rowCount=rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(),SqlHelper.getConn());
		}
		
		return rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
	
	}
	
	
	// ���շ�ҳ����ȡ�û�
	public ArrayList getUserByPage(int pageNow,int pageSize){
		ArrayList<User> userlist= new ArrayList<>();
		int start = (pageNow-1)*pageSize;
		String sql ="select * from user limit "+start+","+pageSize;
		System.out.println(sql);
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		//���η�װ��list
		try {
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setGrade(rs.getString(4));
				
				userlist.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConn());
		}
		return userlist;
				
	}
	
	
//	Driver driver=null;
//	Connection conn = null;
//	PreparedStatement ps = null;
//	ResultSet rs = null;
	
	//��֤�û�
	public boolean checkUser(User user) throws SQLException {
		
		boolean b = false;
		String sql="select * from user where username=? and password=?";
		String parameters[]={user.getName(),user.getPwd()};
		//ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		ArrayList list =SqlHelper.executeQuery2(sql, parameters);
		if(list.size()==1){
			b=true;
		}
		
		return b;
	}
	/**
	 * ͨ��id����ɾ���û�
	 * @param id
	 * @return
	 */
	
	public boolean deleteUser(String id){
		boolean b = true;
		String sql = "delete from user where id = ?";
		String parameters[] ={id};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
		}
		return b;	
	}

 //����id������ѯ Ҫ�޸ĵ����� ��װ��user���󷵻�
	public User getUserById(String id) {
		String sql ="select * from user where id= ?";
		String parameters[]={id};
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		User user =null;
		try {
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setGrade(rs.getString(4));
				user.setPwd(rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConn());
		}
		return user;
	}

}

		
		
//		try {
//			driver = new com.mysql.cj.jdbc.Driver();
//			String url = "jdbc:mysql://localhost:3308/usermanager?serverTimezone = GMT";
//			Properties info = new Properties();
//		    info.put("user", "root");
//			info.put("password", "111111");
//			conn=driver.connect(url, info);
//			String sql = "select * from user where username =? and password=?";
//			ps=conn.prepareStatement(sql);
//			
//			ps.setString(1, user.getName());//ע��
//			ps.setString(2, user.getPwd());
//			
//			rs=ps.executeQuery();
//			if(rs.next()){
//				c=true;
//			}
//			
//			
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
//		    }
//		}
//		return c;
		
		
