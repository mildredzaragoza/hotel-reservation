package com.aspire.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class WebAppApplication implements CommandLineRunner {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
	 return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String pass1 = "1234";
		System.out.println("PASSWORD:   " + passwordEncoder.encode(pass1));
	
	}
	
	
}
