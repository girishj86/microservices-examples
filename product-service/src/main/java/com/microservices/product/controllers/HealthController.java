package com.microservices.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/health")
public class HealthController {
	
	@Autowired
	Environment environment;
	
	@GetMapping
	public String getHealthStatus() {
		String port = environment.getProperty("server.port");
		return "Product Micoservice is up and running on "+port;
	}
	

}
