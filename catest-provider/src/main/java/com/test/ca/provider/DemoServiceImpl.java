package com.test.ca.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.test.ca.dubbo.service.DemoService;

@Service(version="testv1.0")
public class DemoServiceImpl implements DemoService{

	@Override
	public String sayHello(String prarm) {
		System.out.println("调用了sayHello");
		return prarm;
	}
}
