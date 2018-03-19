package com.sanbhu.security.checklist.service;

import com.sanbhu.security.checklist.bo.ApplicationDetails;

public interface DeploymentService {

	ApplicationDetails getApplicationDetails(String websiteURL) throws Exception;
}
