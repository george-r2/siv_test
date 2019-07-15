package com.sivale.test.jama_test.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
//import org.apache.log4j.lf5.util.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.sivale.test.jama_test.repository"})
@EnableTransactionManagement(proxyTargetClass=true)
@PropertySource(value="classpath:application.properties")
public class RepoConfig {

	private static final Logger LOGGER = Logger.getLogger(RepoConfig.class);
	
	@Value("${db.username}")
	private String dbUsername;
	@Value("${db.url}")
	private String dbHostUrl;
	@Value("${db.password}")
	private String dbPasswd;
	
	@Bean
 	public DataSource getDataSource() {
		LOGGER.info("las properties son");
		Properties p = System.getProperties();
		//TODO quitar estos logs, y dejar el unico mecanismo de conexion via jndi
		LOGGER.info("creating datasource....");
		//si se configura por conexion directa
		LOGGER.info("username:"+dbUsername);
		LOGGER.info("hostname:"+dbHostUrl);
		BasicDataSource dataSourceAps = new BasicDataSource();
		dataSourceAps.setDriverClassName("com.mysql.jdbc.Driver");
		dataSourceAps.setUrl(dbHostUrl);
		dataSourceAps.setUsername(dbUsername);
		dataSourceAps.setPassword(dbPasswd);
		return dataSourceAps;
 	}
	
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
 
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
 
		entityManagerFactoryBean.setDataSource(getDataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.sivale.test.jama_test.repository");
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		jpaProperties.put("hibernate.format_sql","true");
		jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
//		jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		jpaProperties.put("hibernate.show_sql", true);
		 
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
 
		return entityManagerFactoryBean;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
//		configurer.setIgnoreResourceNotFound(true);
//		configurer.setIgnoreUnresolvablePlaceholders(false);
//		configurer.setLocations(new ClassPathResource("classpath:application.properties"));
		return configurer;
	}
	
}
