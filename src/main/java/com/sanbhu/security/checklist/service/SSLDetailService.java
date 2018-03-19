package com.sanbhu.security.checklist.service;

import com.sanbhu.security.checklist.bo.ApplicationSSLDetails;

public interface SSLDetailService {
	ApplicationSSLDetails getApplicationSSLDetails(String websiteURL) throws Exception;
}
