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

import com.sanbhu.security.checklist.bo.ApplicationSSLDetails;
import com.sanbhu.security.checklist.bo.SSLDetails;
import com.sanbhu.security.checklist.service.SSLDetailService;
import com.sanbhu.security.checklist.web.BaseRestController;

@RestController
public class ApplicationSSLDetailsAPI extends BaseRestController {

	@Autowired
	private SSLDetailService sslDetailService;

	@RequestMapping("/getApplicationSSLDetails")
	public ApplicationSSLDetails getApplicationSSLDetails(
			@RequestParam(value = "websiteURL", defaultValue = "NULL") String websiteURL,
			final HttpServletRequest request) {
		ApplicationSSLDetails applicationSSLDetails = null;
		try {
			applicationSSLDetails = sslDetailService.getApplicationSSLDetails(websiteURL);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		request.getSession().setAttribute("websiteURL", websiteURL);
		request.getSession().setAttribute("ApplicationSSLDetails", applicationSSLDetails);
		return applicationSSLDetails;
	}

	@RequestMapping("/getSSLDetails")
	public Map<String, List<SSLDetails>> getApplicationSSLDetails(final HttpServletRequest request) {
		Map<String, List<SSLDetails>> resultData = new ConcurrentHashMap<String, List<SSLDetails>>();
		List<SSLDetails> applicationSSLDetailList = new ArrayList<SSLDetails>();
		ApplicationSSLDetails applicationSSLDetails = null;
		Object applicationSSLDetailObject = request.getSession().getAttribute("ApplicationSSLDetails");
		if (applicationSSLDetailObject != null && applicationSSLDetailObject instanceof ApplicationSSLDetails) {
			applicationSSLDetails = (ApplicationSSLDetails) applicationSSLDetailObject;
			
			applicationSSLDetailList.add(applicationSSLDetails.getSslDetailObject());
		}
		resultData.put("data", applicationSSLDetailList);
		return resultData;
	}
}
