package com.sanbhu.security.checklist.bo;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDetails {
	final List<DeploymentServerDetails> deploymentServerList = new ArrayList<DeploymentServerDetails>();
	final List<ApplicationServerDetails> applicationServerList = new ArrayList<ApplicationServerDetails>();
	final List<ServerSideTechnologyDetails> serverSideTechnologyDetailsList = new ArrayList<ServerSideTechnologyDetails>();
	final List<ClientSideTechnologyDetails> clientSideTechnologyDetailsList = new ArrayList<ClientSideTechnologyDetails>();
	
	public List<DeploymentServerDetails> getDeploymentServerList() {
		return deploymentServerList;
	}
	
	public List<ApplicationServerDetails> getApplicationServerList() {
		return applicationServerList;
	}
	
	public List<ServerSideTechnologyDetails> getServerSideTechnologyDetailsList() {
		return serverSideTechnologyDetailsList;
	}
	
	public List<ClientSideTechnologyDetails> getClientSideTechnologyDetailsList() {
		return clientSideTechnologyDetailsList;
	}
}
