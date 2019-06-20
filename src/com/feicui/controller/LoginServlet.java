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
//		1. 接收 网页中传过来的数据    
		String userName = request.getParameter("name");
		String userPassword = request.getParameter("password");
//		 2 、 调用 model 层 dao类   ， 并且调用方法 ， 传递数据      
		LoginDao  dao = new LoginDao() ; 

		boolean login = dao.getLogin(userName, userPassword);
		
//		response.setCharacterEncoding("utf-8");
//		3 、  创建 json对象， 用来返回结果，     
		JSONObject  obj = new JSONObject();
//		4、 判定  是否 添加成功，   
		if(login) {
			obj.put("flag", "success");
			
		}else {
			
			obj.put("flag", "error");
		}
//		6 、向网页中返回结果    相当于 进行反馈 
		response.getWriter().print(obj);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
