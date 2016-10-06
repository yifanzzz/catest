package com.test.ca;

import java.util.ArrayList;

import org.apache.catalina.Context;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@PropertySource({ "properties/dubbo-consumer.properties"})
@ImportResource(locations = { "classpath:config/application-*.xml" })
@ServletComponentScan
public class ApplicationConfiguration {
	
	static {
		try{
			//初始化log4j
			String log4jPath = ApplicationConfiguration.class.getClassLoader().getResource("").getPath()+"properties/log4j.properties";
			PropertyConfigurator.configure(log4jPath);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer pertySourprocesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public static EmbeddedServletContainerFactory servletContainer() {
		
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();

		factory.addContextCustomizers(new TomcatContextCustomizer() {
			@Override
			public void customize(Context context) {
				/*context.addWelcomeFile("/index.html");*/
			}
		});
		return factory;
	}

	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		ArrayList<String> urlmaps = new ArrayList<String>();
		urlmaps.add("/*");
		registration.setUrlMappings(urlmaps);
		registration.setLoadOnStartup(1);
		return registration;
	}
}
