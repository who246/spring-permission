package com.demo.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(locations = "classpath:web_config.properties",prefix = "web")
public class WebConfig {
    private String welcome;
    
    
    public String getWelcome() {
        return welcome;
    }

    
    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }
     
    
    
    
}
