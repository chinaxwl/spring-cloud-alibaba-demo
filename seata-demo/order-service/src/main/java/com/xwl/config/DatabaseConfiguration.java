package com.xwl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author xuewl
 */
@Configuration
public class DatabaseConfiguration {

//  druid don't support GraalVM now because of there is CGlib proxy
//	@Bean
//	@Primary
//	@ConfigurationProperties("spring.datasource")
//	public DataSource storageDataSource() {
//		return new DruidDataSource();
//	}

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("TRUNCATE TABLE order_tbl");

        return jdbcTemplate;
    }

}
