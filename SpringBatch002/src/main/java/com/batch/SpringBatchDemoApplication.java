package com.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@EnableScheduling
public class SpringBatchDemoApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchDemoApplication.class, args);
	}
	
	@Bean("dataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource customDataSource() {

//       DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//        dataSource.setUrl("jdbc:oracle:thin:@10.9.48.13:1555:rptdb");
//        dataSource.setUsername("rptusr");
//        dataSource.setPassword("Rptusr#1608");
//        return dataSource;
        
    	final HikariDataSource ds = new HikariDataSource();
    	   ds.setMaximumPoolSize(30);
    	   ds.setPoolName("BRSHikariDatasourcePool");
    	  
    	/*   ds.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");
    	   ds.addDataSourceProperty("serverName", "10.9.48.13");
    	  // ds.addDataSourceProperty("port", "1555");
    	   ds.addDataSourceProperty("databaseName", "rptdb");
    	   ds.addDataSourceProperty("user", "rptusr");
    	   ds.addDataSourceProperty("password", "Rptusr#1608");
    	  */
    	   
    	   return ds;

    	/*
    	DataSourceBuilder dsb=DataSourceBuilder.create();
    	return dsb.build();
    	*/
        
    }
}
