package com.ragavan.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConnectionUtil {
	private ConnectionUtil() {

	}

	public static BasicDataSource dataSource() {
		final BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/blog");
		ds.setUsername("root");
		ds.setPassword("Findme@87");
		return ds;
	}

	public static JdbcTemplate getJdbcTemplate() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
}