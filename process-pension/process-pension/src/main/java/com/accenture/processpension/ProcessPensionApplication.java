package com.accenture.processpension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableFeignClients
//@EnableEurekaClient
//@EnableCircuitBreaker
//@EnableHystrix
public class ProcessPensionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessPensionApplication.class, args);
	}

}
