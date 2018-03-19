package com.sanbhu.security.checklist.bo;

public class ClientSideTechnologyDetails {

	private String clientSideTechnology;
	private String description;
	public static final Integer TOTAL_OBJECT_FIELD = 2;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClientSideTechnology() {
		return clientSideTechnology;
	}

	public void setClientSideTechnology(String clientSideTechnology) {
		this.clientSideTechnology = clientSideTechnology;
	}
}
