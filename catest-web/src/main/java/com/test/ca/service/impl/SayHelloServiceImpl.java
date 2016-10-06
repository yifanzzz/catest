package com.test.ca.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.test.ca.dubbo.service.DemoService;
import com.test.ca.service.ISayHelloService;

@Component
public class SayHelloServiceImpl implements ISayHelloService {
	
	@Reference(version="testv1.0")
	private DemoService demoService;

	@Override
	public String sayHello(String param) {
		
		return demoService.sayHello(param);
	}
	
}
