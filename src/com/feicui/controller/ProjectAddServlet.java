package com.feicui.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.feicui.dao.ProjectDao;
import com.feicui.pojo.ProjectInfo;

/**
 * Servlet implementation class ProjectAddServlet
 */
public class ProjectAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	
		//2.获取前台传递的数据
		String projectName = request.getParameter("project_name");
		projectName = new String(projectName.getBytes("iso-8859-1"),"utf-8");
		
		String projectType = request.getParameter("project_type");
		projectType = new String(projectType.getBytes("iso-8859-1"),"utf-8");
		
		String project_status = request.getParameter("project_status");
		int projectStatus = Integer.parseInt(project_status);
		
		String projectDesc = request.getParameter("project_desc");
		projectDesc = new String(projectDesc.getBytes("iso-8859-1"),"utf-8");
		//创建项目对象存储数据
		ProjectInfo info = new ProjectInfo();
		info.setProjectName(projectName);
		info.setProjectType(projectType);
		info.setProjectStatus(projectStatus);
		info.setProjectDesc(projectDesc);
		
		//调用方法
		ProjectDao pd = new ProjectDao();
		
		int status = pd.addProjectInfo(info);
		
		JSONObject obj = 	new JSONObject();
		
		obj.put("status", status);
		
		response.getWriter().print(obj);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
