package com.sanbhu.security.checklist.service.impl;

import java.util.Arrays;
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

import com.sanbhu.security.checklist.bo.ApplicationSSLDetails;
import com.sanbhu.security.checklist.service.SSLDetailService;

@Service("sslDetailService")
public class SSLDetailServiceImpl implements SSLDetailService {
	private static final Log logger = LogFactory.getLog("SSLDetailServiceImpl");

	@Override
	public ApplicationSSLDetails getApplicationSSLDetails(String websiteURL) throws Exception {
		String url = "https://www.sslshopper.com/ssl-checker.html#hostname=" + websiteURL;
		ApplicationSSLDetails applicationSSLDetails = new ApplicationSSLDetails();
		WebDriver driver = generateHtmlUnitDriver();
		driver.get(url);
		try {
			Thread.sleep(5000);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		getSSLDetails(driver, applicationSSLDetails);
		return applicationSSLDetails;
	}

	private WebDriver generateHtmlUnitDriver() {
		logger.info("Generating HtmlUnit driver...");
		final DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		capabilities.setJavascriptEnabled(true);
		return new HtmlUnitDriver(capabilities);
	}

	private void getSSLDetails(final WebDriver driver, final ApplicationSSLDetails applicationSSLDetails) {
		List<WebElement> sslDetailList = driver
				.findElements(By.xpath("//*[@id=\"checkData\"]/table[2]/tbody/tr[1]/td[2]"));
		for (WebElement sslDetail : sslDetailList) {
			String sslDetailStr = sslDetail.getText();
			List<String> sslDetails = Arrays.asList(sslDetailStr.split("\\n"));
			for (String detail : sslDetails) {
				if (detail.indexOf(":") >= 0) {
					String[] stringArray = detail.split(":");
					if (stringArray.length == 2) {
						String key = stringArray[0].trim();
						String value = stringArray[1].trim();
						switch (key.trim()) {
						case "Common name":
							applicationSSLDetails.getSslDetailObject().setCommonName(value);
							break;
						case "SANs":
							applicationSSLDetails.getSslDetailObject().setSans(value);
							break;
						case "Organization":
							applicationSSLDetails.getSslDetailObject().setOrganizations(value);
							break;
						case "Location":
							applicationSSLDetails.getSslDetailObject().setLocation(value);
							break;
						case "Signature Algorithm":
							applicationSSLDetails.getSslDetailObject().setSignatureAlgorithm(value);
							break;
						}
					}
				} else {
					applicationSSLDetails.getSslDetailObject().setValidity(detail);
				}
			}
		}

	}

}
