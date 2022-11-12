package com.snort.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrudOprerationsApplication {

	private static Logger log = LoggerFactory.getLogger(SpringCrudOprerationsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudOprerationsApplication.class, args);

		log.info("SBT Login Application is Running Fine");
	}

}
