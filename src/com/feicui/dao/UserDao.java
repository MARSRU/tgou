package com.feicui.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.feicui.pojo.User;
import com.sun.corba.se.impl.ior.GenericTaggedComponent;

public class UserDao {

	String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/study?characterEncoding=utf8";
    String username = "root";
    String password = "root";
    Connection conn;
    int executeUpdate;
/*  想象一下 ，如果公司要把东西送到 国外 
 * 
 *     1 、试一下 车能不能开，点火
 *     
 *     2 、弄个导航 ， 指向目的地
 * 
 *     3 、 具体是什么不知道，但是先放个箱子，试一下 大小合不合适
 *     
 *     4 、 派谁开车过去送
 *     
 *     5 、到了出发日期了，有人把东西送过来了，那么就这些东西装箱
 *     
 *     6 、 出发，送东西
 *     
 *     7 、 送完东西了， 需要 汇报一下情况
 *     
 *     8 、 汇报工作， 可能用到不同的语言， 所以需要确定 汇报语言
 * 
 * */
    
    
    
//1.   创建数据库连接 
    public Connection getConnet(){ 	
    	try {
//          第一步： 注册驱动   相当于开车是点火
			Class.forName(driver);
		
//			 第二步  创建数据库连接      相当于开车的时候 ， 开启导航 指向目的地	
		     conn = DriverManager.getConnection(url,username,password);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return conn;
    }
    
//    8 修改返回数据类型， 相当于汇报工作的时候 用那种语言汇报
    public  int insert(User user) {
    	Connection  conn = getConnet() ; 
//    	3 。 创建 要发送的sql 语句 ， 相当于 送一个大箱子
    	String sql = "insert into user(user_name,account,user_password,user_phone,create_time,update_time) values (?,?,?,?,?,?)";
    	
    	try {
//        	4 。 创建 发送对象     相当于 开车的时候  谁来开着个车。
			PreparedStatement ps = conn.prepareStatement(sql);
			
//			5 。填充数据，        相当于有人把这个货物送过了 ， 需要通过参数接收一下
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getAccount());
			ps.setString(3, user.getUser_password());
			ps.setString(4, user.getUser_phone());
			ps.setString(5, user.getCreate_time());
			ps.setString(6, user.getUpdate_time());
			
//			6 、 发送sql 到数据库中  ，  相当于 开车出发了
			 executeUpdate = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	 7  、 把最终结果返回 ，  相当于开车到目的地了， 汇报一下
    	return executeUpdate ; 
    }
    
    
    
	
}
