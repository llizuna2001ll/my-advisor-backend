package com.hgsplanet.discoveyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveyServiceApplication.class, args);
    }

}
