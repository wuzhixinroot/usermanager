package com.wuzhixin.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class SqlHelper {
	//定义所需的变量
	private static Connection conn = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	
	//连接数据库所需的参数
	private static String driver="";
	private static String url="";
	private static String username ="";
	private static String pwd="";
	
	static Properties pp = null;
	static InputStream fis = null;
	
	//加载驱动
	
	static{
		try {
			pp=new Properties();
			//当使用Java web的时候 要用类加载器 
			//fis = new FileInputStream("mysql.properties");
			try {
				fis =SqlHelper.class.getClassLoader().getResourceAsStream("mysql.properties");//使用类加载器到web项目的主目录下
				pp.load(fis);
				url=pp.getProperty("url");
				username=pp.getProperty("username");
				pwd=pp.getProperty("pwd");
				driver=pp.getProperty("driver");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Class.forName(driver);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//得到连接
	
	public static Connection getConnection(){
		try {
			conn=DriverManager.getConnection(url,username,pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//然后操作数据库
	//先写一个update /delete/insert
	//sql格式 update 表名 set 字段名 =？ where 字段 =？
	
	public static void executeUpdate(String sql ,String[] parameters){
		//创建一个ps对象
		
		try {
			conn=SqlHelper.getConnection();//得到连接
			ps=conn.prepareStatement(sql);
			if(parameters!=null){
				for(int i =0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
					System.out.println("hdfowhfw");
				}
			}
			ps.executeUpdate();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
			//抛出异常
			throw new RuntimeException( e.getMessage());
		}finally{
			close(rs,ps,conn);
		}
	}
	
	public static Connection getConn() {
		return conn;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	//多种语句执行 ，就要用事务
	public static void executeUpdate2(String sql[],String [][] parameters){
		conn=getConnection();//得到连接
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<sql.length;i++){
			try {
				ps=conn.prepareStatement(sql[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(parameters[i]!=null){
				for(int j =0;j<parameters[i].length;j++){
					try {
						ps.setString(j+1, parameters[i][j]);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			try {
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	/**
	 * 操作查询，返回结果集
	 * 
	 */
	
	public static ResultSet executeQuery(String sql ,String [] parameters){
		conn=SqlHelper.getConnection();//得到连接对象
		try {
			ps=conn.prepareStatement(sql);
			if(parameters!=null){
				for(int i=0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}
			}
			rs=ps.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//close(rs,ps,conn);
		}
		return rs;
		
		
	}
	 
	
	//查询语句那利用资源 ，那里关闭
	// 对查询到的结果集先得到列数，在封装到数组中 进行一次封装，在进行二次 封装到
	//集合中 这样保证了内存的合理利用，并释放资源
	
	public static ArrayList executeQuery2(String sql,String [] parameters){
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet =null;
		
		connection = SqlHelper.getConnection();
		
		try {
			preparedStatement=conn.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(parameters!=null){
			for(int i = 0; i<parameters.length;i++){
				try {
					preparedStatement.setObject(i+1, parameters[i]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList list = new ArrayList();
		
 		//ResultSetMetaData resultSetMetaData = resultSet.getMetaData(); 
		try {
			int counts =resultSet.getMetaData().getColumnCount();//得到resultset的列数
			while(resultSet.next()){
				Object [] object = new Object[counts];
				for(int i = 1;i<=counts;i++){
					object[i-1]=resultSet.getObject(i);
				}
				
				list.add(object);	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlHelper.close(resultSet, preparedStatement, connection);
		}
		
		return list;
	}
	
	//调用存储过程
	public static void callPro1(String sql,String[] parameters){
		conn=SqlHelper.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			if(parameters!=null){
				for(int i=0;i<parameters.length;i++){
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//分页问题
	
	public static void close(ResultSet rs,PreparedStatement ps,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs=null;
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps=null;
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps=null;
	    }
		
	}
	

}
