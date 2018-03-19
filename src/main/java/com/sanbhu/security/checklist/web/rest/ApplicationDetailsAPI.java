package com.sanbhu.security.checklist.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanbhu.security.checklist.bo.ApplicationDetails;
import com.sanbhu.security.checklist.bo.ApplicationServerDetails;
import com.sanbhu.security.checklist.bo.ClientSideTechnologyDetails;
import com.sanbhu.security.checklist.bo.DeploymentServerDetails;
import com.sanbhu.security.checklist.bo.ServerSideTechnologyDetails;
import com.sanbhu.security.checklist.service.DeploymentService;
import com.sanbhu.security.checklist.web.BaseRestController;

@RestController
public class ApplicationDetailsAPI extends BaseRestController {

	@Autowired
	private DeploymentService deploymentService;

	@RequestMapping("/getApplicationDetails")
	public ApplicationDetails getApplicationDetails(
			@RequestParam(value = "websiteURL", defaultValue = "NULL") String websiteURL,
			final HttpServletRequest request) {
		ApplicationDetails applicationDetails = null;
		try {
			applicationDetails = deploymentService.getApplicationDetails(websiteURL);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		request.getSession().setAttribute("ApplicationDetails", applicationDetails);

		return applicationDetails;
	}

	@RequestMapping("/getDeploymentServerDetails")
	public Map<String, List<DeploymentServerDetails>> getDeploymentServerDetails(final HttpServletRequest request) {
		Map<String, List<DeploymentServerDetails>> resultData = new ConcurrentHashMap<String, List<DeploymentServerDetails>>();
		List<DeploymentServerDetails> deploymentServerDetailList = new ArrayList<DeploymentServerDetails>();
		Object applicationDetailObject = request.getSession().getAttribute("ApplicationDetails");
		if (null != applicationDetailObject && applicationDetailObject instanceof ApplicationDetails) {
			ApplicationDetails applicationDetail = (ApplicationDetails) applicationDetailObject;
			deploymentServerDetailList = applicationDetail.getDeploymentServerList();
		}
		resultData.put("data", deploymentServerDetailList);
		return resultData;
	}

	@RequestMapping("/getApplicationServerDetails")
	public Map<String, List<ApplicationServerDetails>> getApplicationServerDetails(final HttpServletRequest request) {
		Map<String, List<ApplicationServerDetails>> resultData = new ConcurrentHashMap<String, List<ApplicationServerDetails>>();
		List<ApplicationServerDetails> applicationServerDetailList = new ArrayList<ApplicationServerDetails>();
		Object applicationDetailObject = request.getSession().getAttribute("ApplicationDetails");
		if (null != applicationDetailObject && applicationDetailObject instanceof ApplicationDetails) {
			ApplicationDetails applicationDetail = (ApplicationDetails) applicationDetailObject;
			applicationServerDetailList = applicationDetail.getApplicationServerList();
		}
		resultData.put("data", applicationServerDetailList);
		return resultData;
	}

	@RequestMapping("/getServerSideTechnologyDetails")
	public Map<String, List<ServerSideTechnologyDetails>> getServerSideTechnologyDetails(final HttpServletRequest request) {
		Map<String, List<ServerSideTechnologyDetails>> resultData = new ConcurrentHashMap<String, List<ServerSideTechnologyDetails>>();
		List<ServerSideTechnologyDetails> serverSideTechnologyDetailList = new ArrayList<ServerSideTechnologyDetails>();
		Object applicationDetailObject = request.getSession().getAttribute("ApplicationDetails");
		if (null != applicationDetailObject && applicationDetailObject instanceof ApplicationDetails) {
			ApplicationDetails applicationDetail = (ApplicationDetails) applicationDetailObject;
			serverSideTechnologyDetailList = applicationDetail.getServerSideTechnologyDetailsList();
		}
		resultData.put("data", serverSideTechnologyDetailList);
		return resultData;
	}

	@RequestMapping("/getClientSideTechnologyDetails")
	public Map<String, List<ClientSideTechnologyDetails>> getClientSideTechnologyDetails(final HttpServletRequest request) {
		Map<String, List<ClientSideTechnologyDetails>> resultData = new ConcurrentHashMap<String, List<ClientSideTechnologyDetails>>();
		List<ClientSideTechnologyDetails> clientSideTechnologyDetailList = new ArrayList<ClientSideTechnologyDetails>();
		Object applicationDetailObject = request.getSession().getAttribute("ApplicationDetails");
		if (null != applicationDetailObject && applicationDetailObject instanceof ApplicationDetails) {
			ApplicationDetails applicationDetail = (ApplicationDetails) applicationDetailObject;
			clientSideTechnologyDetailList = applicationDetail.getClientSideTechnologyDetailsList();
		}
		resultData.put("data", clientSideTechnologyDetailList);
		return resultData;
	}
}
