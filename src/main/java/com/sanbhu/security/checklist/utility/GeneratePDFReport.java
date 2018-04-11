package com.sanbhu.security.checklist.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.sanbhu.security.checklist.bo.ApplicationDetails;
import com.sanbhu.security.checklist.bo.ApplicationSSLDetails;
import com.sanbhu.security.checklist.bo.ApplicationServerDetails;
import com.sanbhu.security.checklist.bo.ClientSideTechnologyDetails;
import com.sanbhu.security.checklist.bo.DeploymentServerDetails;
import com.sanbhu.security.checklist.bo.ServerSideTechnologyDetails;

@Component
public class GeneratePDFReport {

	private static final String HTML_SAMPLE_DATA0_1 = "<html><head></head><body><h3><i>Security Checklist Report</i>";
	
	private static final String HTML_SAMPLE_DATA0_2 = "</h3>";
	
	private static final String HTML_SAMPLE_DATA1 = "<h5><u>Deployment Server Details</u></h5>" + "<table><thead><tr>"
			+ "<td style='background-color: #e6e6e6' >Deployment Owner Details</td>"
			+ "<td style='background-color: #e6e6e6' >Operating System</td>"
			+ "<td style='background-color: #e6e6e6' >Web Server</td>"
			+ "<td style='background-color: #e6e6e6' >IP Address</td> "
			+ "<td style='background-color: #e6e6e6' >Instance Updated On</td>" + "</tr></thead><tbody>";
	private static final String HTML_SAMPLE_DATA2 = "</tbody></table>";

	private static final String HTML_SAMPLE_DATA3 = "<h5><u>Application Server Technology</u></h5>" + "<table><thead><tr>"
			+ "<td style='background-color: #e6e6e6' >Application Server Technology</td>"
			+ "<td style='background-color: #e6e6e6' >Descriptions</td>" + "</tr></thead><tbody>";

	private static final String HTML_SAMPLE_DATA4 = "</tbody></table>";

	private static final String HTML_SAMPLE_DATA5 = "<h5><u>Server Side Programming</u></h5>" + "<table><thead><tr>"
			+ "<td style='background-color: #e6e6e6' >Server Side</td>"
			+ "<td style='background-color: #e6e6e6' >Descriptions</td>" + "</tr></thead><tbody>";

	private static final String HTML_SAMPLE_DATA6 = "</tbody></table>";

	private static final String HTML_SAMPLE_DATA7 = "<h5><u>Client Side Programming</u></h5>" + "<table><thead><tr>"
			+ "<td style='background-color: #e6e6e6' >Client Side</td>"
			+ "<td style='background-color: #e6e6e6' >Descriptions</td>" + "</tr></thead><tbody>";

	private static final String HTML_SAMPLE_DATA8 = "</tbody></table>";

	private static final String HTML_SAMPLE_DATA9 = "<h5><u>Server Certificate Details</u></h5>" + "<table><thead><tr>"
			+ "<td style='background-color: #e6e6e6' >Common Name</td>"
			+ "<td style='background-color: #e6e6e6' >Domains</td>"
			+ "<td style='background-color: #e6e6e6' >Organization</td>"
			+ "<td style='background-color: #e6e6e6' >Address</td>"
			+ "<td style='background-color: #e6e6e6' >Validity</td>"
			+ "<td style='background-color: #e6e6e6' >Signature Algorithm</td>" + "</tr></thead><tbody>";

	private static final String HTML_SAMPLE_DATA10 = "</tbody></table>";

	private static final String HTML_SAMPLE_DATA11 = "<h3><i>Vulnerabilities</i></h3><h5><u>Request Level</u></h5>" + "<table><thead><tr>"
			+ "<td style='background-color: #e6e6e6'>Vulnerabilities</td>"
			+ "<td style='background-color: #e6e6e6'>Level</td>"
			+ "</tr> </thead><tbody>";
	
	private static final String HTML_SAMPLE_DATA12 = "</tbody></table>";

	private static final String HTML_SAMPLE_DATA13 = "<h5><u>Code Level</u></h5>" + "<table><thead><tr>"
			+ "<td style='background-color: #e6e6e6'>Vulnerabilities</td>"
			+ "<td style='background-color: #e6e6e6'>Level</td>"
			+ "</tr> </thead><tbody>";
	
	private static final String HTML_SAMPLE_DATA14 = "</tbody></table>";
	
	private static final String HTML_SAMPLE_DATA15 = "</body></html>";

	public ByteArrayInputStream generate(final ApplicationDetails applicationDetail, final ApplicationSSLDetails applicationSSLDetails, final String websiteURL) {
		ByteArrayInputStream byteArrayInputStream = null;
		final Document document = new Document();
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			PdfWriter pdfWriter = PdfWriter.getInstance(document, byteArrayOutputStream);
			document.open();
			writeBody(pdfWriter, document, applicationDetail, applicationSSLDetails, websiteURL);
			document.close();
			byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return byteArrayInputStream;
	}

	private void writeBody(final PdfWriter pdfWriter, final Document document,
			final ApplicationDetails applicationDetail, final ApplicationSSLDetails applicationSSLDetails, final String websiteURL)
			throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(HTML_SAMPLE_DATA0_1);
		stringBuilder.append(" - <small>" + websiteURL + "</small>");
		stringBuilder.append(HTML_SAMPLE_DATA0_2);
		stringBuilder.append(HTML_SAMPLE_DATA1);
		getApplicationDetails(stringBuilder, applicationDetail);
		stringBuilder.append(HTML_SAMPLE_DATA2);
		stringBuilder.append(HTML_SAMPLE_DATA3);
		getApplicationTechnologyDetails(stringBuilder, applicationDetail);
		stringBuilder.append(HTML_SAMPLE_DATA4);
		stringBuilder.append(HTML_SAMPLE_DATA5);
		getServerSideTechnologyDetails(stringBuilder, applicationDetail);
		stringBuilder.append(HTML_SAMPLE_DATA6);
		stringBuilder.append(HTML_SAMPLE_DATA7);
		getClientSideTechnologyDetails(stringBuilder, applicationDetail);
		stringBuilder.append(HTML_SAMPLE_DATA8);
		stringBuilder.append(HTML_SAMPLE_DATA9);
		getServerCertificateDetails(stringBuilder, applicationSSLDetails);
		stringBuilder.append(HTML_SAMPLE_DATA10);
		stringBuilder.append(HTML_SAMPLE_DATA11);
		getSecurityCheckList(stringBuilder);
		stringBuilder.append(HTML_SAMPLE_DATA14);
		stringBuilder.append(HTML_SAMPLE_DATA15);
		StringReader stringReader = new StringReader(stringBuilder.toString());
		XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, stringReader);
	}

	private void getSecurityCheckList(final StringBuilder stringBuilder) {
		getRequestLevelSecurityCheckList(stringBuilder);
		stringBuilder.append(HTML_SAMPLE_DATA12);
		stringBuilder.append(HTML_SAMPLE_DATA13);
		getCodeLevelSecurityCheckList(stringBuilder);
	}
	
	private void getApplicationDetails(final StringBuilder stringBuilder, final ApplicationDetails applicationDetail) {
		for (DeploymentServerDetails deploymentServerDetails : applicationDetail.getDeploymentServerList()) {
			stringBuilder.append("<tr style='border: 1px solid #ddd;' >");
			stringBuilder.append(
					"<td style='border: 1px solid #ddd;' >" + deploymentServerDetails.getNetblockOwner() + "</td>");
			stringBuilder.append(
					"<td style='border: 1px solid #ddd;' >" + deploymentServerDetails.getOperatingSystem() + "</td>");
			stringBuilder
					.append("<td style='border: 1px solid #ddd;' >" + deploymentServerDetails.getWebServer() + "</td>");
			stringBuilder
					.append("<td style='border: 1px solid #ddd;' >" + deploymentServerDetails.getIPAddress() + "</td>");
			stringBuilder.append(
					"<td style='border: 1px solid #ddd;' >" + deploymentServerDetails.getLastUsedOn() + "</td>");
			stringBuilder.append("</tr>");
		}
	}

	private void getApplicationTechnologyDetails(final StringBuilder stringBuilder,
			final ApplicationDetails applicationDetail) {
		for (ApplicationServerDetails applicationServerDetails : applicationDetail.getApplicationServerList()) {
			stringBuilder.append("<tr style='border: 1px solid #ddd;' >");
			stringBuilder.append("<td style='border: 1px solid #ddd;' >"
					+ applicationServerDetails.getApplicationServerTechnology() + "</td>");
			stringBuilder.append(
					"<td style='border: 1px solid #ddd;' >" + applicationServerDetails.getDescription() + "</td>");
			stringBuilder.append("</tr>");
		}
	}

	private void getServerSideTechnologyDetails(final StringBuilder stringBuilder,
			final ApplicationDetails applicationDetail) {
		for (ServerSideTechnologyDetails serverSideTechnologyDetail : applicationDetail
				.getServerSideTechnologyDetailsList()) {
			stringBuilder.append("<tr style='border: 1px solid #ddd;' >");
			stringBuilder.append("<td style='border: 1px solid #ddd;' >"
					+ serverSideTechnologyDetail.getServerSideTechnology() + "</td>");
			stringBuilder.append(
					"<td style='border: 1px solid #ddd;' >" + serverSideTechnologyDetail.getDescription() + "</td>");
			stringBuilder.append("</tr>");
		}
	}

	private void getClientSideTechnologyDetails(final StringBuilder stringBuilder,
			final ApplicationDetails applicationDetail) {
		for (ClientSideTechnologyDetails clientSideTechnologyDetail : applicationDetail
				.getClientSideTechnologyDetailsList()) {
			stringBuilder.append("<tr style='border: 1px solid #ddd;' >");
			stringBuilder.append("<td style='border: 1px solid #ddd;' >"
					+ clientSideTechnologyDetail.getClientSideTechnology() + "</td>");
			stringBuilder.append(
					"<td style='border: 1px solid #ddd;' >" + clientSideTechnologyDetail.getDescription() + "</td>");
			stringBuilder.append("</tr>");
		}
	}

	private void getServerCertificateDetails(final StringBuilder stringBuilder,
			final ApplicationSSLDetails applicationSSLDetails) {
		stringBuilder.append("<tr style='border: 1px solid #ddd;' >");
		stringBuilder.append("<td style='border: 1px solid #ddd;' >"
				+ applicationSSLDetails.getSslDetailObject().getCommonName() + "</td>");
		stringBuilder.append("<td style='border: 1px solid #ddd;' >"
				+ applicationSSLDetails.getSslDetailObject().getSans() + "</td>");
		stringBuilder.append("<td style='border: 1px solid #ddd;' >"
				+ applicationSSLDetails.getSslDetailObject().getOrganizations() + "</td>");
		stringBuilder.append("<td style='border: 1px solid #ddd;' >"
				+ applicationSSLDetails.getSslDetailObject().getLocation() + "</td>");
		stringBuilder.append("<td style='border: 1px solid #ddd;' >"
				+ applicationSSLDetails.getSslDetailObject().getValidity() + "</td>");
		stringBuilder.append("<td style='border: 1px solid #ddd;' >"
				+ applicationSSLDetails.getSslDetailObject().getSignatureAlgorithm() + "</td>");
		stringBuilder.append("</tr>");
	}

	private void getRequestLevelSecurityCheckList(final StringBuilder stringBuilder) {
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Cookie Security: Cookie not Sent Over SSL</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Cookie</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >XML External Entity Injection</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >XML</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Cross Site Scripting : DOM</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >XML</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >File Disclosure: J2EE</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >URL Mapping</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Header Manipulation</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Header</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >JSON Injection</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >JSON Object</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >SQL Injection</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >SQL</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Open Redirect</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >URL Redirection</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Path Manipulation</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Request Parameter</td>");
		stringBuilder.append("</tr>");
	}
	
	private void getCodeLevelSecurityCheckList(final StringBuilder stringBuilder) {
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Dynamic Code Evaluation: Unsafe Deserialization</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Cookie</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >J2EE Bad Practices: Non-Serializable Object Stored in Session</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >XML</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Key Management: Hardcoded Encryption Key</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >XML</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Null Dereference</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >URL Mapping</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Password Management: Hardcoded Password</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Header</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Password Management: Password in Configuration File</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >JSON Object</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Race Condition : Singleton Member Field</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >SQL</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Unreleased Resource: Database</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >URL Redirection</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Unreleased Resource: Files & Streams</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Request Parameter</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("<tr>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Weak Encryption</td>");
			stringBuilder.append("<td  style='border: 1px solid #ddd;' >Request Parameter</td>");
		stringBuilder.append("</tr>");
	}
}
