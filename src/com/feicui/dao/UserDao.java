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
/*  ����һ�� �������˾Ҫ�Ѷ����͵� ���� 
 * 
 *     1 ����һ�� ���ܲ��ܿ������
 *     
 *     2 ��Ū������ �� ָ��Ŀ�ĵ�
 * 
 *     3 �� ������ʲô��֪���������ȷŸ����ӣ���һ�� ��С�ϲ�����
 *     
 *     4 �� ��˭������ȥ��
 *     
 *     5 �����˳��������ˣ����˰Ѷ����͹����ˣ���ô����Щ����װ��
 *     
 *     6 �� �������Ͷ���
 *     
 *     7 �� ���궫���ˣ� ��Ҫ �㱨һ�����
 *     
 *     8 �� �㱨������ �����õ���ͬ�����ԣ� ������Ҫȷ�� �㱨����
 * 
 * */
    
    
    
//1.   �������ݿ����� 
    public Connection getConnet(){ 	
    	try {
//          ��һ���� ע������   �൱�ڿ����ǵ��
			Class.forName(driver);
		
//			 �ڶ���  �������ݿ�����      �൱�ڿ�����ʱ�� �� �������� ָ��Ŀ�ĵ�	
		     conn = DriverManager.getConnection(url,username,password);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return conn;
    }
    
//    8 �޸ķ����������ͣ� �൱�ڻ㱨������ʱ�� ���������Ի㱨
    public  int insert(User user) {
    	Connection  conn = getConnet() ; 
//    	3 �� ���� Ҫ���͵�sql ��� �� �൱�� ��һ��������
    	String sql = "insert into user(user_name,account,user_password,user_phone,create_time,update_time) values (?,?,?,?,?,?)";
    	
    	try {
//        	4 �� ���� ���Ͷ���     �൱�� ������ʱ��  ˭�����Ÿ�����
			PreparedStatement ps = conn.prepareStatement(sql);
			
//			5 ��������ݣ�        �൱�����˰���������͹��� �� ��Ҫͨ����������һ��
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getAccount());
			ps.setString(3, user.getUser_password());
			ps.setString(4, user.getUser_phone());
			ps.setString(5, user.getCreate_time());
			ps.setString(6, user.getUpdate_time());
			
//			6 �� ����sql �����ݿ���  ��  �൱�� ����������
			 executeUpdate = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	 7  �� �����ս������ ��  �൱�ڿ�����Ŀ�ĵ��ˣ� �㱨һ��
    	return executeUpdate ; 
    }
    
    
    
	
}
