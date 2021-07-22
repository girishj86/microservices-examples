package com.microservices.config.controllers;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/config-client")
public class ConfigClientController {


	@Value("${test.parameter}")
	private String testParameter;
	
	@GetMapping("/git")
	public String readFromGit() {
		if(StringUtils.isBlank(testParameter)) {
			return "Could not read from git";
		}
		return "test.parameter="+testParameter;
	}

}
