package com.dauo.project.common.config;

import com.dauo.project.common.config.properties.ApplicationProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = ApplicationProps.class)
public class PropertiesConfig {
    
}