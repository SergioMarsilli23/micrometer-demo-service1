package com.gentera.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;


@EnableAutoConfiguration
@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
    	
    	SpringApplication.run(ServiceApplication.class, args);
        Hooks.enableAutomaticContextPropagation();
    }

}
