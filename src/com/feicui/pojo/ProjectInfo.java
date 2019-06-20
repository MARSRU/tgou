package com.feicui.pojo;

public class ProjectInfo {

	private Integer projectId;
	private String projectName;
	private String projectType;
	private String projectDesc;
	private Integer projectStatus;
	
	public ProjectInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectInfo(Integer projectId, String projectName, String projectType, String projectDesc,
			Integer projectStatus) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectType = projectType;
		this.projectDesc = projectDesc;
		this.projectStatus = projectStatus;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public Integer getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}

	public ProjectInfo(String projectName, String projectType, String projectDesc, Integer projectStatus) {
		super();
		this.projectName = projectName;
		this.projectType = projectType;
		this.projectDesc = projectDesc;
		this.projectStatus = projectStatus;
	}

	@Override
	public String toString() {
		return "ProjectInfo [projectId=" + projectId + ", projectName=" + projectName + ", projectType=" + projectType
				+ ", projectDesc=" + projectDesc + ", projectStatus=" + projectStatus + "]";
	}
	
	
	
}
