package com.nathan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Override
	public void configureContentNegotiation(final ContentNegotiationConfigurer contentNegotiationConfigurer) {
		contentNegotiationConfigurer.favorPathExtension(false).favorParameter(true)
			.defaultContentType(MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);
	}
}