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
  
//1.   �������ݿ����� 
    public Connection getConnet(){ 	
    	try {
//          ��һ���� ע������   �൱�ڿ����ǵ��
			Class.forName(driver);
		
//			 �ڶ���  �������ݿ�����      �൱�ڿ�����ʱ�� �� �������� ָ��Ŀ�ĵ�	
		     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/study?characterEncoding=utf8","root","root");
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return conn;
    }
    
    public boolean getLogin(String userName, String userPassword) {
		try {
			// ��д��ѯ��sql���
			String sql = "select * from user where user_name=? and user_password=?";
			// ��������
			conn =getConnet();
			// ͨ�����Ӷ�����Ԥ���������
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, userName);
			ps.setString(2, userPassword);
			
			// ͨ��Ԥ�������ִ��sql���,���ҵõ������
			rs = ps.executeQuery();
			// �ж��û��������Ƿ����
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			
		 }
		// �����ж�
		return flag;
	}
}
