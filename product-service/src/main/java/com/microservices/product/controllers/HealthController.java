package com.microservices.product.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/health")
public class HealthController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Environment environment;
	
	@GetMapping
	public String getHealthStatus() {
		LOG.info("Start: Product Service's getHealthStatus method");
		String port = environment.getProperty("server.port");
		LOG.info("End: Product Service's getHealthStatus method");
		return "Product Micoservice is up and running on "+port;
	}
	

}
