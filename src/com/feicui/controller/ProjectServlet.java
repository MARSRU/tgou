package com.feicui.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feicui.dao.ProjectDao;
import com.feicui.pojo.ProjectInfo;


public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    得到dao 对象， 用来调用这个对象中的方法
		ProjectDao project = 	new ProjectDao() ;
		
//		调用dao 层   得到数据库数据
		List<ProjectInfo> selectProject = project.getSelectProject();
		
//		 把数据存放在  request 对象中
		request.setAttribute("projectList",selectProject);
//		跳转到对应的jsp页面
		request.getRequestDispatcher("project_list.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
