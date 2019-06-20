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

//		1. 接收 网页中传过来的数据     相当于   接收货物 ，把货物 整整齐齐的卸下来 
		String userName = request.getParameter("userName");
		String account = request.getParameter("account");
		String userPassword = request.getParameter("password");
		String phone = request.getParameter("phone");
//		2      把数据放入 pojo类                卸下来之后 整合装箱 。    注意记录  送货时间 
		User user = new User();
		user.setUser_name(userName);
		user.setAccount(account);
		user.setUser_password(userPassword);
		user.setUser_phone(phone);
		//转换日期格式                 注意记录  送货时间 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
		user.setCreate_time(date);
		user.setUpdate_time(date);
//		  3 、 调用 model 层 dao类   ， 并且调用方法 ， 传递数据      相当于 选择 配送 部门中哪个 团队送
		UserDao  dao = new UserDao() ; 
		
		int status = dao.insert(user);
		

//		response.setCharacterEncoding("utf-8");
//		4 、  创建 json对象， 用来返回结果，      相当于 如果 运送完成， 那么 需要 反馈一下结果， 设定一下 反馈结果用什么  方式
		JSONObject  obj = new JSONObject();
//		5、 判定  是否 添加成功，   相当于    创建反馈结果
		if(status>0) {
			obj.put("success", status);
			
		}else {
			obj.put("error", status);
		}
//		6 、向网页中返回结果    相当于 进行反馈 
		response.getWriter().print(obj);

	}

	/*
	 * 需要确定前端发送过来的数据， 是通过那种形式  ， 需要在网页中看一下，一看是秘密发送
	 * 
	 * 
	 * 1 。 接收货物 ，  把货物 整整齐齐的卸下来 
	 * 
	 * 2 、 卸下来之后 整合装箱 。    注意记录  送货时间
	 * 
	 * 3、 选择 配送 部门中哪个 团队送
	 * 
	 * 4、 如果 运送完成， 那么 需要 反馈一下结果， 设定一下 反馈结果用什么  方式
	 * 
	 * 5 、无论成功与失败， 都要组织  反馈时的  语言
	 * 
	 * 6 、 进行反馈 。。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	
	
	
}
