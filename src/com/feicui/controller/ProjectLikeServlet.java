package com.feicui.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feicui.dao.ProjectDao;
import com.feicui.pojo.ProjectInfo;

/**
 * Servlet implementation class ProjectLikeServlet
 */
public class ProjectLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String projectName = request.getParameter("project_name");
		projectName = new String(projectName.getBytes("iso-8859-1"),"utf-8");
		
		String projectType = request.getParameter("project_type");
		projectType = new String(projectType.getBytes("iso-8859-1"),"utf-8");
		
		String projectStatus = request.getParameter("project_status");
		
		String projectDesc = request.getParameter("project_desc");
		projectDesc = new String(projectDesc.getBytes("iso-8859-1"),"utf-8");
		//创建projectInfo对象
		ProjectInfo info = new ProjectInfo();
		info.setProjectName(projectName);
		info.setProjectType(projectType);
		//判断projectStatus元素是否有值,如果有值就把它放在projectInfo对象中
		if(null != projectStatus && "" !=projectStatus ) {
			//Integer.parseInt(projectStatus)把string类型的数据转换为integer类型的数据
			info.setProjectStatus(Integer.parseInt(projectStatus));
		}
		info.setProjectDesc(projectDesc);
	
		
		System.out.println(info);
		
		ProjectDao pd = new ProjectDao();
		
        List<ProjectInfo> selectProject = pd.getLikeSelectProject(info);
		
		
		request.setAttribute("projectList",selectProject);
		
		
		request.getRequestDispatcher("project_list.jsp").forward(request, response);
	}


}
