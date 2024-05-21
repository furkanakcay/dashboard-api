package com.akcay.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DashboardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardApiApplication.class, args);
	}

}
