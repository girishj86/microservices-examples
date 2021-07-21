package com.microservices.order.controllers;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/health/product")
	public String getProductHealth() {
		String productHealth = restTemplate.getForObject("http://product-service/product/health", String.class);
		if (StringUtils.isNotBlank(productHealth)) {
			return "Received product status as "+productHealth;
		}
		return "Product is not reachable";
	}

}
