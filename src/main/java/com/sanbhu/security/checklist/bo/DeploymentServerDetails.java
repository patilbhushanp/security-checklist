package com.sanbhu.security.checklist.bo;

public class DeploymentServerDetails {
	private String netblockOwner;
	private String IPAddress;
	private String operatingSystem;
	private String webServer;
	private String lastUsedOn;
	public static final Integer TOTAL_OBJECT_FIELD = 5; 
		
	public String getNetblockOwner() {
		return netblockOwner;
	}

	public void setNetblockOwner(String netblockOwner) {
		this.netblockOwner = netblockOwner;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getWebServer() {
		return webServer;
	}

	public void setWebServer(String webServer) {
		this.webServer = webServer;
	}

	public String getLastUsedOn() {
		return lastUsedOn;
	}

	public void setLastUsedOn(String lastUsedOn) {
		this.lastUsedOn = lastUsedOn;
	}

}
