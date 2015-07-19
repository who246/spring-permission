package com.demo.web.back.download.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DownLoadConfig {
    @Value("${back.download.savePath}")
    public String savePath;
}
