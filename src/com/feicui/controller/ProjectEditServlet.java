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
 * Servlet implementation class ProjectEditServlet
 */
public class ProjectEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        // ��ȡ�ú��޸ĵ����� 
		String project_id = request.getParameter("projectId");
		int projectId = Integer.parseInt(project_id);
		
		String projectName = request.getParameter("projectName");
         //	Ϊ�˽�� �������룬 ��Ҫ �����ַ���
		projectName = new String(projectName.getBytes("iso-8859-1"),"utf-8");
		
		String projectType = request.getParameter("projectType");
		projectType = new String(projectType.getBytes("iso-8859-1"),"utf-8");
		
		String project_status = request.getParameter("projectStatus");
		int projectStatus = Integer.parseInt(project_status);
		
		
		String projectDesc = request.getParameter("projectDesc");
		projectDesc = new String(projectDesc.getBytes("iso-8859-1"),"utf-8");
		// ����projectInfo����
		ProjectInfo info = new ProjectInfo(projectId, projectName, projectType, projectDesc, projectStatus);

		ProjectDao pd = new ProjectDao();

		int status = pd.editProjectInfo(info);
		
		JSONObject  obj = new JSONObject();
		
		if (status > 0) {
			obj.put("success", status);
		} 

		response.getWriter().print(obj);
	}

}
