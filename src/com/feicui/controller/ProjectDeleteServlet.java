package com.feicui.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.feicui.dao.ProjectDao;

/**
 * Servlet implementation class ProjectDeleteServlet
 */
public class ProjectDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		//接收前台传过来的参数
		String project_id = request.getParameter("projectId");
		//字符串转整型
		Integer projectId = new Integer(project_id);
		
		int status = 0;
	     
		JSONObject obj  =    new JSONObject();
		
		//判断project_id不为空
		if(null!=projectId) {
			ProjectDao pd = new ProjectDao();
			
			status = pd.deleteProjectInfo(projectId);
			//通过userServiceImp类对象名调用类中的方法
			obj.put("keys", status);
		}
		response.getWriter().print(obj);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
