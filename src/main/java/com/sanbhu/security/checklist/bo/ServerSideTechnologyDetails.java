package com.sanbhu.security.checklist.bo;

public class ServerSideTechnologyDetails {

	private String serverSideTechnology;
	private String description;
	public static final Integer TOTAL_OBJECT_FIELD = 2;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServerSideTechnology() {
		return serverSideTechnology;
	}

	public void setServerSideTechnology(String serverSideTechnology) {
		this.serverSideTechnology = serverSideTechnology;
	}
}
