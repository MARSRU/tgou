package com.feicui.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.feicui.dao.LoginDao;
import com.feicui.dao.UserDao;
import com.feicui.pojo.User;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1. ���� ��ҳ�д�����������    
		String userName = request.getParameter("name");
		String userPassword = request.getParameter("password");
//		 2 �� ���� model �� dao��   �� ���ҵ��÷��� �� ��������      
		LoginDao  dao = new LoginDao() ; 

		boolean login = dao.getLogin(userName, userPassword);
		
//		response.setCharacterEncoding("utf-8");
//		3 ��  ���� json���� �������ؽ����     
		JSONObject  obj = new JSONObject();
//		4�� �ж�  �Ƿ� ��ӳɹ���   
		if(login) {
			obj.put("flag", "success");
			
		}else {
			
			obj.put("flag", "error");
		}
//		6 ������ҳ�з��ؽ��    �൱�� ���з��� 
		response.getWriter().print(obj);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
