package com.hellokoding.jpa.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
 
@Primary
@Configuration

public class RepositoryConfiguration{

	@Value("${spring.datasource.url}")
	public String url;
	@Value("${spring.datasource.username}")
	public String username;
	@Value("${spring.datasource.password}")
	public String pw;
	@Value("${spring.datasource.driverClassName}")
	public String driverName;

	@Bean
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().username(username).password(pw).url(url).driverClassName(driverName)
				.build();
	}
}