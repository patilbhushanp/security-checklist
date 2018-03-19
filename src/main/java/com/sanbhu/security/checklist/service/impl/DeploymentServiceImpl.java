package com.sanbhu.security.checklist.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Service;

import com.sanbhu.security.checklist.bo.ApplicationDetails;
import com.sanbhu.security.checklist.bo.ApplicationServerDetails;
import com.sanbhu.security.checklist.bo.ClientSideTechnologyDetails;
import com.sanbhu.security.checklist.bo.DeploymentServerDetails;
import com.sanbhu.security.checklist.bo.ServerSideTechnologyDetails;
import com.sanbhu.security.checklist.service.DeploymentService;

@Service("deploymentService")
public class DeploymentServiceImpl implements DeploymentService {
	private static final Log logger = LogFactory.getLog("GetDeploymentDetails");
	private static final String APPLICATION_SERVER = "Application Servers";
	private static final String SERVER_SIDE_PROGRAMMING = "Server-Side";
	private static final String CLIENT_SIDE_PROGRAMMING = "Client-Side";
	private static final String CLIENT_SIDE_FRAMEWORKS = "Client-Side Scripting Frameworks";

	private WebDriver generateHtmlUnitDriver() {
		logger.info("Generating HtmlUnit driver...");
		final DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		capabilities.setJavascriptEnabled(true);
		return new HtmlUnitDriver(capabilities);
	}

	@Override
	public ApplicationDetails getApplicationDetails(String websiteURL) throws Exception {
		String url = "https://toolbar.netcraft.com/site_report?url=" + websiteURL;
		ApplicationDetails applicationDetails = new ApplicationDetails();
		WebDriver driver = generateHtmlUnitDriver();
		driver.get(url);

		getDeploymentDetails(driver, applicationDetails);
		getServerDetails(driver, applicationDetails);

		return applicationDetails;
	}

	private void getDeploymentDetails(final WebDriver driver, final ApplicationDetails applicationDetails) {
		final List<WebElement> deploymentDetailElementList = driver
				.findElements(By.xpath("//*[@id=\"history_table\"]/div[2]/table/tbody/tr"));
		for (final WebElement deploymentDetailElement : deploymentDetailElementList) {
			final List<WebElement> detailList = deploymentDetailElement.findElements(By.tagName("td"));
			final DeploymentServerDetails deploymentServerDetails = new DeploymentServerDetails();
			int count = 0;
			if (detailList.size() >= DeploymentServerDetails.TOTAL_OBJECT_FIELD) {
				deploymentServerDetails.setNetblockOwner(detailList.get(count++).getText().trim());
				deploymentServerDetails.setIPAddress(detailList.get(count++).getText().trim());
				deploymentServerDetails.setOperatingSystem(detailList.get(count++).getText().trim());
				deploymentServerDetails.setWebServer(detailList.get(count++).getText().trim());
				deploymentServerDetails.setLastUsedOn(detailList.get(count++).getText().trim());
			}
			applicationDetails.getDeploymentServerList().add(deploymentServerDetails);
		}
	}

	private void getServerDetails(final WebDriver driver, final ApplicationDetails applicationDetails) {
		int counter = 1;
		WebElement applicationServerElement = driver
				.findElement(By.xpath("//*[@id=\"technology_table\"]/div[2]/ul/li[" + counter + "]/h3"));

		if (applicationServerElement != null
				&& APPLICATION_SERVER.equalsIgnoreCase(applicationServerElement.getText().trim())) {
			final List<WebElement> applicationServerElementList = driver.findElements(
					By.xpath("//*[@id=\"technology_table\"]/div[2]/ul/li[" + counter++ + "]/div[2]/table/tbody/tr"));
			getApplicationServerDetails(applicationServerElementList, applicationDetails);
			applicationServerElement = driver
					.findElement(By.xpath("//*[@id=\"technology_table\"]/div[2]/ul/li[" + counter + "]/h3"));
		}

		if (applicationServerElement != null
				&& SERVER_SIDE_PROGRAMMING.equalsIgnoreCase(applicationServerElement.getText().trim())) {
			final List<WebElement> serverSideTechnologyElementList = driver.findElements(
					By.xpath("//*[@id=\"technology_table\"]/div[2]/ul/li[" + counter++ + "]/div[2]/table/tbody/tr"));
			getServerSideTechnologyDetails(serverSideTechnologyElementList, applicationDetails);
			applicationServerElement = driver
					.findElement(By.xpath("//*[@id=\"technology_table\"]/div[2]/ul/li[" + counter + "]/h3"));
		}

		if (applicationServerElement != null
				&& CLIENT_SIDE_PROGRAMMING.equalsIgnoreCase(applicationServerElement.getText().trim())) {
			final List<WebElement> serverSideTechnologyElementList = driver.findElements(
					By.xpath("//*[@id=\"technology_table\"]/div[2]/ul/li[" + counter++ + "]/div[2]/table/tbody/tr"));
			getClientSideTechnologyDetails(serverSideTechnologyElementList, applicationDetails);
			applicationServerElement = driver
					.findElement(By.xpath("//*[@id=\"technology_table\"]/div[2]/ul/li[" + counter + "]/h3"));
		}

		if (applicationServerElement != null
				&& CLIENT_SIDE_FRAMEWORKS.equalsIgnoreCase(applicationServerElement.getText().trim())) {
			final List<WebElement> serverSideTechnologyElementList = driver.findElements(
					By.xpath("//*[@id=\"technology_table\"]/div[2]/ul/li[" + counter++ + "]/div[2]/table/tbody/tr"));
			getClientSideTechnologyDetails(serverSideTechnologyElementList, applicationDetails);
			applicationServerElement = driver
					.findElement(By.xpath("//*[@id=\"technology_table\"]/div[2]/ul/li[" + counter + "]/h3"));
		}
	}

	private void getApplicationServerDetails(List<WebElement> applicationServerElementList,
			final ApplicationDetails applicationDetails) {
		for (final WebElement applicationServerDetailElement : applicationServerElementList) {
			final List<WebElement> detailList = applicationServerDetailElement.findElements(By.tagName("td"));
			final ApplicationServerDetails applicationServerDetails = new ApplicationServerDetails();
			int count = 0;
			if (detailList.size() >= ApplicationServerDetails.TOTAL_OBJECT_FIELD) {
				applicationServerDetails.setApplicationServerTechnology(detailList.get(count++).getText().trim());
				applicationServerDetails.setDescription(detailList.get(count++).getText().trim());
			}
			applicationDetails.getApplicationServerList().add(applicationServerDetails);
		}
	}

	private void getServerSideTechnologyDetails(List<WebElement> serverSideTechnologyElementList,
			final ApplicationDetails applicationDetails) {
		for (final WebElement serverSideTechnologyDetailElement : serverSideTechnologyElementList) {
			final List<WebElement> detailList = serverSideTechnologyDetailElement.findElements(By.tagName("td"));
			final ServerSideTechnologyDetails serverSideTechnologyDetails = new ServerSideTechnologyDetails();
			int count = 0;
			if (detailList.size() >= ServerSideTechnologyDetails.TOTAL_OBJECT_FIELD) {
				serverSideTechnologyDetails.setServerSideTechnology(detailList.get(count++).getText().trim());
				serverSideTechnologyDetails.setDescription(detailList.get(count++).getText().trim());
			}
			applicationDetails.getServerSideTechnologyDetailsList().add(serverSideTechnologyDetails);
		}

	}

	private void getClientSideTechnologyDetails(List<WebElement> clientSideTechnologyElementList,
			final ApplicationDetails applicationDetails) {
		for (final WebElement clientSideTechnologyDetailElement : clientSideTechnologyElementList) {
			final List<WebElement> detailList = clientSideTechnologyDetailElement.findElements(By.tagName("td"));
			final ClientSideTechnologyDetails clientSideTechnologyDetails = new ClientSideTechnologyDetails();
			int count = 0;
			if (detailList.size() >= ServerSideTechnologyDetails.TOTAL_OBJECT_FIELD) {
				clientSideTechnologyDetails.setClientSideTechnology(detailList.get(count++).getText().trim());
				clientSideTechnologyDetails.setDescription(detailList.get(count++).getText().trim());
			}
			applicationDetails.getClientSideTechnologyDetailsList().add(clientSideTechnologyDetails);
		}

	}

}
