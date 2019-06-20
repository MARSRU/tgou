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
	//创建项目信息对象用于存储项目信息
	ProjectInfo project ;
	// 创建List集合对象，用于存储查询出来的数据
	List<ProjectInfo> list = new ArrayList<ProjectInfo>();
			
			
	// 1. 创建数据库连接
	public Connection getConnet() {
		try {
			// 第一步： 注册驱动 相当于开车是点火
			Class.forName(driver);

			// 第二步 创建数据库连接 相当于开车的时候 ， 开启导航 指向目的地
			conn = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	 /*
	  * 查询数据库中所有信息
	  * 
	  * */
	public List<ProjectInfo> getSelectProject() {
		// 1 书写sql语句
		String sql = "select project_id,project_name,project_type,project_desc,project_status from project";
		try {
			ps = conn.prepareStatement(sql);
			//2  通过预编译对象执行sql语句
			rs = ps.executeQuery();
			//3  遍历结果集
			while (rs.next()) {
				//4 从数据集中获取数据
				int projectId = rs.getInt("project_id");
				String projectName = rs.getString("project_name");
				String projectType = rs.getString("project_type");
				int projectStatus = rs.getInt("project_status");
				String projectDesc = rs.getString("project_desc");
				
				//5 把取出来的数据保存在 类中用来传递
				project = new ProjectInfo(projectId, projectName, projectType, projectDesc, projectStatus);
				//6 因为是多条数据， 所以必须保存在 list 集合容器中
				list.add(project);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		7 返回一个结果在 contoller类中调用
		return list;

	}
	
	/*模糊查询
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

		// 创建List集合对象，用于存储查询出来的数据
		List<ProjectInfo> list = new ArrayList<ProjectInfo>();
		// 创建项目信息对象用于存储项目信息
		ProjectInfo project = new ProjectInfo();
		try {

			ps = conn.prepareStatement(sql);

			// 通过预编译对象执行sql语句
			rs = ps.executeQuery();

			// 遍历结果集
			while (rs.next()) {
				// 从数据集中获取数据
				int projectId = rs.getInt("project_id");
				String projectName = rs.getString("project_name");
				String projectType = rs.getString("project_type");
				int projectStatus = rs.getInt("project_status");
				String projectDesc = rs.getString("project_desc");
				// 把取出来的数据保存在 类中用来传递
				project = new ProjectInfo(projectId, projectName, projectType, projectDesc, projectStatus);
				// 因为是多条数据， 所以必须保存在 list 集合容器中
				list.add(project);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	
   /*
    * 通过id 查询数据库中 对应的一条数据
    * 
    * */
	public ProjectInfo getIdSelectProject(String id) {
		// 书写sql语句
		String sql = "select project_id,project_name,project_type,project_desc,project_status from project where project_id="
				+ id;

		// 创建项目信息对象用于存储项目信息
		ProjectInfo project = null;

		try {

			ps = conn.prepareStatement(sql);

			// 通过预编译对象执行sql语句
			rs = ps.executeQuery();

			// 遍历结果集
			while (rs.next()) {
				// 从数据集中获取数据
				int projectId = rs.getInt("project_id");
				String projectName = rs.getString("project_name");
				String projectType = rs.getString("project_type");
				int projectStatus = rs.getInt("project_status");
				String projectDesc = rs.getString("project_desc");
				// 把取出来的数据保存在 类中用来传递
				project = new ProjectInfo(projectId, projectName, projectType, projectDesc, projectStatus);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return project;

	}

	/* 修改数据库中的 某一条数据*/
	public int editProjectInfo(ProjectInfo info) {
		// 1.书写sql语句
		String sql = "update project set project_name=?,project_type=?,project_status=?,project_desc=? where project_id=?";
		// 通过连接获取到预编译对象
		PreparedStatement ps = null;
		// 创建int类型的变量作为返回值
		int status = 0;
		try {
			ps = conn.prepareStatement(sql);
			// 进行预编译
			ps.setString(1, info.getProjectName());
			ps.setString(2, info.getProjectType());
			ps.setInt(3, info.getProjectStatus());
			ps.setString(4, info.getProjectDesc());
			ps.setInt(5, info.getProjectId());
			// 执行sql语句
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
/*删除数据库中的某一条数据*/
	public int deleteProjectInfo(Integer projectId) {
		// 书写sql语句
		String sql = "delete from project where project_id=?";
		// 通过连接获得预编译对象
		PreparedStatement ps = null;
		// 创建int类型的变量用于接受结果
		int status = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, projectId);
			// 执行sql语句
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;

	}
/*在数据库中添加一条数据*/
	public int addProjectInfo(ProjectInfo info) {
		// 1.书写sql语句
		String sql = "insert into project(project_name,project_type,project_status,project_desc) values(?,?,?,?)";

		// 创建一个int类型的变量用于返回结果
		int status = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getProjectName());
			ps.setString(2, info.getProjectType());
			ps.setInt(3, info.getProjectStatus());
			ps.setString(4, info.getProjectDesc());

			// 执行sql语句
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

}
