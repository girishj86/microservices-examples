package com.microservices.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	ProductFeignClient productFeignClient;
	
	public String getProductHealth() {
		return productFeignClient.getProductHealth();
	}

}
