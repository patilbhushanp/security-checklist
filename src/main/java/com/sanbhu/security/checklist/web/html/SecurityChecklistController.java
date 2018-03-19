package com.sanbhu.security.checklist.web.html;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanbhu.security.checklist.web.BaseHtmlController;

@Controller
public class SecurityChecklistController extends BaseHtmlController {

	@RequestMapping("/")
	public String showDashboard(Map<String, Object> model) {
		return "checklist/checklist";
	}

}