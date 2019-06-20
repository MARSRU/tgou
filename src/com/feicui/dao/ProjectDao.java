package com.feicui.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.feicui.pojo.ProjectInfo;
import com.mysql.jdbc.StringUtils;

public class ProjectDao {

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/study?characterEncoding=utf8";
	String username = "root";
	String password = "root";

	Connection conn = getConnet();;
	PreparedStatement ps = null;
	ResultSet rs = null;
	boolean flag = false;
	//������Ŀ��Ϣ�������ڴ洢��Ŀ��Ϣ
	ProjectInfo project ;
	// ����List���϶������ڴ洢��ѯ����������
	List<ProjectInfo> list = new ArrayList<ProjectInfo>();
			
			
	// 1. �������ݿ�����
	public Connection getConnet() {
		try {
			// ��һ���� ע������ �൱�ڿ����ǵ��
			Class.forName(driver);

			// �ڶ��� �������ݿ����� �൱�ڿ�����ʱ�� �� �������� ָ��Ŀ�ĵ�
			conn = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	 /*
	  * ��ѯ���ݿ���������Ϣ
	  * 
	  * */
	public List<ProjectInfo> getSelectProject() {
		// 1 ��дsql���
		String sql = "select project_id,project_name,project_type,project_desc,project_status from project";
		try {
			ps = conn.prepareStatement(sql);
			//2  ͨ��Ԥ�������ִ��sql���
			rs = ps.executeQuery();
			//3  ���������
			while (rs.next()) {
				//4 �����ݼ��л�ȡ����
				int projectId = rs.getInt("project_id");
				String projectName = rs.getString("project_name");
				String projectType = rs.getString("project_type");
				int projectStatus = rs.getInt("project_status");
				String projectDesc = rs.getString("project_desc");
				
				//5 ��ȡ���������ݱ����� ������������
				project = new ProjectInfo(projectId, projectName, projectType, projectDesc, projectStatus);
				//6 ��Ϊ�Ƕ������ݣ� ���Ա��뱣���� list ����������
				list.add(project);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		7 ����һ������� contoller���е���
		return list;

	}
	
	/*ģ����ѯ
	 * */
	public List<ProjectInfo> getLikeSelectProject(ProjectInfo info) {
		String sql = "select project_id,project_name,project_type,project_desc,project_status from project where 1=1 " ;
		if (null != info) {
			String projectName = info.getProjectName();
			String projectType = info.getProjectType();
			Integer projectStatus = info.getProjectStatus();
			String projectDesc = info.getProjectDesc();
			
			if (!StringUtils.isNullOrEmpty(projectName)) {
				sql = sql + " and project_name like '%" + projectName.trim() + "%'";
			}
			if (!StringUtils.isNullOrEmpty(projectType)) {
				sql = sql + " and project_type like '%" + projectType.trim() + "%'";
			}
			if (projectStatus != null) {
				sql = sql + " and project_status =" + projectStatus;
			}
			if (!StringUtils.isNullOrEmpty(projectDesc)) {
				sql = sql + " and project_desc like '%" + projectDesc.trim() + "%'";
			}
		}

		// ����List���϶������ڴ洢��ѯ����������
		List<ProjectInfo> list = new ArrayList<ProjectInfo>();
		// ������Ŀ��Ϣ�������ڴ洢��Ŀ��Ϣ
		ProjectInfo project = new ProjectInfo();
		try {

			ps = conn.prepareStatement(sql);

			// ͨ��Ԥ�������ִ��sql���
			rs = ps.executeQuery();

			// ���������
			while (rs.next()) {
				// �����ݼ��л�ȡ����
				int projectId = rs.getInt("project_id");
				String projectName = rs.getString("project_name");
				String projectType = rs.getString("project_type");
				int projectStatus = rs.getInt("project_status");
				String projectDesc = rs.getString("project_desc");
				// ��ȡ���������ݱ����� ������������
				project = new ProjectInfo(projectId, projectName, projectType, projectDesc, projectStatus);
				// ��Ϊ�Ƕ������ݣ� ���Ա��뱣���� list ����������
				list.add(project);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	
   /*
    * ͨ��id ��ѯ���ݿ��� ��Ӧ��һ������
    * 
    * */
	public ProjectInfo getIdSelectProject(String id) {
		// ��дsql���
		String sql = "select project_id,project_name,project_type,project_desc,project_status from project where project_id="
				+ id;

		// ������Ŀ��Ϣ�������ڴ洢��Ŀ��Ϣ
		ProjectInfo project = null;

		try {

			ps = conn.prepareStatement(sql);

			// ͨ��Ԥ�������ִ��sql���
			rs = ps.executeQuery();

			// ���������
			while (rs.next()) {
				// �����ݼ��л�ȡ����
				int projectId = rs.getInt("project_id");
				String projectName = rs.getString("project_name");
				String projectType = rs.getString("project_type");
				int projectStatus = rs.getInt("project_status");
				String projectDesc = rs.getString("project_desc");
				// ��ȡ���������ݱ����� ������������
				project = new ProjectInfo(projectId, projectName, projectType, projectDesc, projectStatus);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return project;

	}

	/* �޸����ݿ��е� ĳһ������*/
	public int editProjectInfo(ProjectInfo info) {
		// 1.��дsql���
		String sql = "update project set project_name=?,project_type=?,project_status=?,project_desc=? where project_id=?";
		// ͨ�����ӻ�ȡ��Ԥ�������
		PreparedStatement ps = null;
		// ����int���͵ı�����Ϊ����ֵ
		int status = 0;
		try {
			ps = conn.prepareStatement(sql);
			// ����Ԥ����
			ps.setString(1, info.getProjectName());
			ps.setString(2, info.getProjectType());
			ps.setInt(3, info.getProjectStatus());
			ps.setString(4, info.getProjectDesc());
			ps.setInt(5, info.getProjectId());
			// ִ��sql���
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
/*ɾ�����ݿ��е�ĳһ������*/
	public int deleteProjectInfo(Integer projectId) {
		// ��дsql���
		String sql = "delete from project where project_id=?";
		// ͨ�����ӻ��Ԥ�������
		PreparedStatement ps = null;
		// ����int���͵ı������ڽ��ܽ��
		int status = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, projectId);
			// ִ��sql���
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;

	}
/*�����ݿ������һ������*/
	public int addProjectInfo(ProjectInfo info) {
		// 1.��дsql���
		String sql = "insert into project(project_name,project_type,project_status,project_desc) values(?,?,?,?)";

		// ����һ��int���͵ı������ڷ��ؽ��
		int status = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getProjectName());
			ps.setString(2, info.getProjectType());
			ps.setInt(3, info.getProjectStatus());
			ps.setString(4, info.getProjectDesc());

			// ִ��sql���
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

}
