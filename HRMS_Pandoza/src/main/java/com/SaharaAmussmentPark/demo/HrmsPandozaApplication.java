package com.SaharaAmussmentPark.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.SaharaAmussmentPark")
@EntityScan("com.SaharaAmussmentPark.model")
@EnableJpaRepositories("com.SaharaAmussmentPark.Repository")
@EnableEurekaClient
public class HrmsPandozaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmsPandozaApplication.class, args);
	}

}
