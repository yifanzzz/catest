package com.test.ca.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.ca.service.ISayHelloService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private Logger logger=Logger.getLogger(TestController.class);
	
	@Autowired
	private ISayHelloService sayHelloService;
	
	@RequestMapping("/sayHello")
	@ResponseBody
	public String sayHello(HttpServletRequest request){
		logger.info("请求了"+request.getRequestURI());
		return sayHelloService.sayHello("测试123");
	}
}
