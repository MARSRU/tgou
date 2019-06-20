package com.feicui.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.feicui.dao.UserDao;
import com.feicui.pojo.User;



public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		1. ���� ��ҳ�д�����������     �൱��   ���ջ��� ���ѻ��� ���������ж���� 
		String userName = request.getParameter("userName");
		String account = request.getParameter("account");
		String userPassword = request.getParameter("password");
		String phone = request.getParameter("phone");
//		2      �����ݷ��� pojo��                ж����֮�� ����װ�� ��    ע���¼  �ͻ�ʱ�� 
		User user = new User();
		user.setUser_name(userName);
		user.setAccount(account);
		user.setUser_password(userPassword);
		user.setUser_phone(phone);
		//ת�����ڸ�ʽ                 ע���¼  �ͻ�ʱ�� 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
		user.setCreate_time(date);
		user.setUpdate_time(date);
//		  3 �� ���� model �� dao��   �� ���ҵ��÷��� �� ��������      �൱�� ѡ�� ���� �������ĸ� �Ŷ���
		UserDao  dao = new UserDao() ; 
		
		int status = dao.insert(user);
		

//		response.setCharacterEncoding("utf-8");
//		4 ��  ���� json���� �������ؽ����      �൱�� ��� ������ɣ� ��ô ��Ҫ ����һ�½���� �趨һ�� ���������ʲô  ��ʽ
		JSONObject  obj = new JSONObject();
//		5�� �ж�  �Ƿ� ��ӳɹ���   �൱��    �����������
		if(status>0) {
			obj.put("success", status);
			
		}else {
			obj.put("error", status);
		}
//		6 ������ҳ�з��ؽ��    �൱�� ���з��� 
		response.getWriter().print(obj);

	}

	/*
	 * ��Ҫȷ��ǰ�˷��͹��������ݣ� ��ͨ��������ʽ  �� ��Ҫ����ҳ�п�һ�£�һ�������ܷ���
	 * 
	 * 
	 * 1 �� ���ջ��� ��  �ѻ��� ���������ж���� 
	 * 
	 * 2 �� ж����֮�� ����װ�� ��    ע���¼  �ͻ�ʱ��
	 * 
	 * 3�� ѡ�� ���� �������ĸ� �Ŷ���
	 * 
	 * 4�� ��� ������ɣ� ��ô ��Ҫ ����һ�½���� �趨һ�� ���������ʲô  ��ʽ
	 * 
	 * 5 �����۳ɹ���ʧ�ܣ� ��Ҫ��֯  ����ʱ��  ����
	 * 
	 * 6 �� ���з��� ����
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	
	
	
}
