package com.sanbhu.security.checklist.bo;

public class ApplicationServerDetails {

	private String applicationServerTechnology;
	private String description;
	public static final Integer TOTAL_OBJECT_FIELD = 2;

	public String getApplicationServerTechnology() {
		return applicationServerTechnology;
	}

	public void setApplicationServerTechnology(String applicationServerTechnology) {
		this.applicationServerTechnology = applicationServerTechnology;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
