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
//	    �õ�dao ���� ����������������еķ���
		ProjectDao project = 	new ProjectDao() ;
		
//		����dao ��   �õ����ݿ�����
		List<ProjectInfo> selectProject = project.getSelectProject();
		
//		 �����ݴ����  request ������
		request.setAttribute("projectList",selectProject);
//		��ת����Ӧ��jspҳ��
		request.getRequestDispatcher("project_list.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
