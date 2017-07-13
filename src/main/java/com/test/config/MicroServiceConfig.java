package com.test.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.annotation.PostConstruct;

/**
 * Created by peterhsu on 2017/7/12.
 */
@Configuration
@ComponentScan("com.test.service")
@Import(MvcConfig.class)
public class MicroServiceConfig {
    private static final Logger log = LoggerFactory.getLogger(MicroServiceConfig.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.profiles.active}")
    private String[] profiles;

    @PostConstruct
    public void init() {
        log.debug("== Application Name: " + applicationName + ", Profiles: " + StringUtils.join(profiles, ", ") + " ==");
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
