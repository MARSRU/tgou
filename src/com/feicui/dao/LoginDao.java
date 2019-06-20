package com.feicui.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class LoginDao {

	
	String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/study?characterEncoding=utf8";
    String username = "root";
    String password = "root";
    
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	boolean flag = false;
  
//1.   创建数据库连接 
    public Connection getConnet(){ 	
    	try {
//          第一步： 注册驱动   相当于开车是点火
			Class.forName(driver);
		
//			 第二步  创建数据库连接      相当于开车的时候 ， 开启导航 指向目的地	
		     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/study?characterEncoding=utf8","root","root");
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return conn;
    }
    
    public boolean getLogin(String userName, String userPassword) {
		try {
			// 书写查询的sql语句
			String sql = "select * from user where user_name=? and user_password=?";
			// 创建连接
			conn =getConnet();
			// 通过连接对象获得预编译类对象
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, userName);
			ps.setString(2, userPassword);
			
			// 通过预编译对象执行sql语句,并且得到结果集
			rs = ps.executeQuery();
			// 判断用户名数据是否存在
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			
		 }
		// 返回判断
		return flag;
	}
}
