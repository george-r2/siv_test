package com.sivale.test.jama_test.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.sivale.test.jama_test")
@Import({
	RepoConfig.class,
	SocialConfig.class,
	SecurityConfig.class,
	WebViewConfig.class
})
public class ConfigurationApp extends WebMvcConfigurerAdapter{

	@Bean
	@Qualifier("messageSource")
	public ResourceBundleMessageSource getMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setFallbackToSystemLocale(true);
//		messageSource.addBasenames("classpath:application");
		messageSource.addBasenames("classpath:message");
		return messageSource;
	}
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//		.allowedOrigins("*")
//		.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
//		.allowedHeaders("*")
//		.allowCredentials(true);
////		.maxAge(MAX_AGE_SECS);
//	}
}