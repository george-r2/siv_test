package com.sivale.test.jama_test.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.sivale.test.jama_test")
@Import({
	WebViewConfig.class,
	RepoConfig.class,
	SocialConfig.class,
	SecurityConfig.class
})
//@PropertySource("classpath:message.properties")
public class ConfigurationApp{

	@Bean
//	@Qualifier("messageSource")
	public MessageSource  messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
//		messageSource.setFallbackToSystemLocale(false);
//		messageSource.addBasenames("classpath:application");
		messageSource.addBasenames("message");
		return messageSource;
	}
	
	
	@Bean
	public  PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
//		configurer.setIgnoreResourceNotFound(true);
//		configurer.setIgnoreUnresolvablePlaceholders(false);
//		configurer.setLocations(new ClassPathResource("classpath:application.properties"));
		return configurer;
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
