package com.sglink.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;



@Configuration
public class WebConfig implements WebMvcConfigurer {
	  private final String connectPath = "/img/sglink/**";
	  private final String resourcePath = "file:///sglink/";

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler(connectPath)
	                .addResourceLocations(resourcePath);
	    }

}





































