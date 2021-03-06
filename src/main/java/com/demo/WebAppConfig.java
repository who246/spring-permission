package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.demo.application.config.WebConfig;


@ComponentScan
@EntityScan
@EnableJpaRepositories("com.demo.web")
@EnableAutoConfiguration
@EnableConfigurationProperties({WebConfig.class})
public class WebAppConfig  {
 
    public static void main(String[] args) {
		SpringApplication.run(WebAppConfig.class, args);
	}

}
