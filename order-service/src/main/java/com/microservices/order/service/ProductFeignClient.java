package com.microservices.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
	
	@GetMapping("/product/health")
	String getProductHealth();

}
