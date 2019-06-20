package com.feicui.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feicui.dao.ProjectDao;
import com.feicui.pojo.ProjectInfo;

/**
 * Servlet implementation class ProjectUpdateServlet
 */
public class ProjectUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String projectId = request.getParameter("projectId");
		
		System.out.println(projectId+"1111111111");
		// �ж�����projectId
		if(null != projectId&& !"".equals(projectId)) {
			ProjectDao pd =	new ProjectDao();
			ProjectInfo projectInfo = pd.getIdSelectProject(projectId);
			
			if(null !=projectInfo) { // �ж϶����Ƿ����
				projectInfo.setProjectId(Integer.parseInt(projectId));
				// ����request������
				request.setAttribute("projectInfo", projectInfo);
			}
		}
		
		request.getRequestDispatcher("project_update.jsp").forward(request, response);
		
	}

	
}
