package com.foodcourt.MessagingMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MessagingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingMicroserviceApplication.class, args);
	}

}
