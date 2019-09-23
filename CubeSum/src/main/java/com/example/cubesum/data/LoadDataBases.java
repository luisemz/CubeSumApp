package com.example.cubesum.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDataBases {
	final Logger logger = LoggerFactory.getLogger(LoadDataBases.class);
	
	@Bean
	CommandLineRunner initDataBaseST(StoreDataRepository repositoryData) {
		return args -> {
			logger.info("DB Connect and creation StoreData: " + repositoryData.save(new StoreData()));
		};
	}
	
	@Bean
	CommandLineRunner initDataBaseP3(Point3DRepository repositoryPoint) {
		return args -> {
			logger.info("DB Connect Point3D");
		};
	}
}
