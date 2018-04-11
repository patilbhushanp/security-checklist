package com.sanbhu.security.checklist.web.html;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanbhu.security.checklist.bo.ApplicationDetails;
import com.sanbhu.security.checklist.bo.ApplicationSSLDetails;
import com.sanbhu.security.checklist.utility.GeneratePDFReport;
import com.sanbhu.security.checklist.web.BaseHtmlController;

@Controller
public class DownloadSecurityChecklistController extends BaseHtmlController {

	@Autowired
	GeneratePDFReport generatePDFReport;

	@RequestMapping(value = "/downloadReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> showDashboard(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=securitychecklist.pdf");

		final Object applicationDetailObject = request.getSession().getAttribute("ApplicationDetails");
		final Object applicationSSLDetailObject = request.getSession().getAttribute("ApplicationSSLDetails");
		final Object websiteURLObject = request.getSession().getAttribute("websiteURL");
		
		ApplicationDetails applicationDetail = null;
		ApplicationSSLDetails applicationSSLDetails = null;
		String websiteURL = "";
		ByteArrayInputStream byteArrayInputStream = null;
		if (null != websiteURLObject && websiteURLObject instanceof String) {
			websiteURL = (String) websiteURLObject;
		}
		if (null != applicationDetailObject && applicationDetailObject instanceof ApplicationDetails) {
			applicationDetail = (ApplicationDetails) applicationDetailObject;
		}
		if (applicationSSLDetailObject != null && applicationSSLDetailObject instanceof ApplicationSSLDetails) {
			applicationSSLDetails = (ApplicationSSLDetails) applicationSSLDetailObject;
		}

		if (applicationSSLDetails != null && applicationDetail != null) {
			byteArrayInputStream = generatePDFReport.generate(applicationDetail, applicationSSLDetails, websiteURL);
		}
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(byteArrayInputStream));
	}
}