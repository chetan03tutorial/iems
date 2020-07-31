package com.nataraj.management.employee.iems;

import com.nataraj.management.employee.iems.config.RootConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RootConfig.class, args);
	}

}
