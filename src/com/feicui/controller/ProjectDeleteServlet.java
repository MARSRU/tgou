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
		
	
		//����ǰ̨�������Ĳ���
		String project_id = request.getParameter("projectId");
		//�ַ���ת����
		Integer projectId = new Integer(project_id);
		
		int status = 0;
	     
		JSONObject obj  =    new JSONObject();
		
		//�ж�project_id��Ϊ��
		if(null!=projectId) {
			ProjectDao pd = new ProjectDao();
			
			status = pd.deleteProjectInfo(projectId);
			//ͨ��userServiceImp��������������еķ���
			obj.put("keys", status);
		}
		response.getWriter().print(obj);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
