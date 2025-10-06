package com.app.NotificationMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.app.NotificationMicroService.repository")
@EnableDiscoveryClient
@EnableFeignClients
public class NotificationMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationMicroServiceApplication.class, args);
	}

}
