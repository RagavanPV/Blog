package com.ragavan.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(basePackages = "com.ragavan")
public class AppConfig {
	@Bean
	public static BasicDataSource dataSource() {
		final BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/blog");
		ds.setUsername("root");
		ds.setPassword("Findme@87");
		return ds;
	}

	@Bean
	public static JdbcTemplate getJdbcTemplate() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
}
