package com.packtpub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ReservationMicroserviceApp {

    public static void main(String... args){
        SpringApplication.run(ReservationMicroserviceApp.class, args);
    }
}