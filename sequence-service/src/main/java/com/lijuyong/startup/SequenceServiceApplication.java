package com.lijuyong.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SequenceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SequenceServiceApplication.class, args);
	}
}
