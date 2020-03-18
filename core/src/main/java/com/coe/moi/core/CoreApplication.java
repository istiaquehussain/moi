package com.coe.moi.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class CoreApplication {
	private static final Logger LOGGER = LogManager.getLogger(CoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
		LOGGER.info("Core Application Service  started successfully .....");
	}

}
