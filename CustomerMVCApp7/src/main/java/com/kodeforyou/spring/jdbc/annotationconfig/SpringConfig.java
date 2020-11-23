package com.kodeforyou.spring.jdbc.annotationconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = "com.kodeforyou")
@Configuration
@PropertySource(value="classpath:db.properties", ignoreResourceNotFound=true)
public class SpringConfig extends WebMvcConfigurerAdapter {
	@Value("${jdbc.jndi}")
	private String jndiName;
	
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		JndiDataSourceLookup lookup = new JndiDataSourceLookup();
		System.out.println("$$$$$#### jndiName:"+jndiName);
		return lookup.getDataSource(jndiName);
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jt = new JdbcTemplate();
		jt.setDataSource(dataSource());
		return jt;
	}
	
	/*@Bean
	public PropertyPlaceholderConfigurer getProPlConfigurer() {
		PropertyPlaceholderConfigurer pphc = new PropertyPlaceholderConfigurer();
		pphc.setLocation(new ClassPathResource("db.properties"));
		return pphc;
	}*/

	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);

		viewResolver.setPrefix("/WEB-INF/pages/");

		viewResolver.setSuffix(".jsp");

		return viewResolver;

	}

	/*
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	*/
	

	

}