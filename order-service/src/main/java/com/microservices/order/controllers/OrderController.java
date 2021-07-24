package com.microservices.order.controllers;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.order.service.KafkaSender;
import com.microservices.order.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	KafkaSender kafkaSender;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/health/product")
	@HystrixCommand(fallbackMethod = "getProductHealthFallback")
	public String getProductHealth() {
		LOG.info("Start: Order Service's getProductHealth method");
		String productHealth = restTemplate.getForObject("http://product-service/product/health", String.class);
		if (StringUtils.isNotBlank(productHealth)) {
			return "Received product status as "+productHealth;
		}
		LOG.info("End: Order Service's getProductHealth method");
		return "Product is not reachable";
	}
	
	@PostMapping(value = "/producer")
	public String producer(@RequestBody String message) {
		kafkaSender.send(message);

		return "Message sent to the Kafka Topic test-topic Successfully";
	}
	
	@GetMapping("/product/health")
	public String getProductHealthUsingFeignClient(){
		return productService.getProductHealth();
		
	}
	
	public String getProductHealthFallback() {
		return "Product service is not available";
	}
	
}
