package com.test.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by peterhsu on 2017/7/12.
 */
@Configuration
@ComponentScan("com.test.controller")
public class MvcConfig extends WebMvcConfigurerAdapter {
    private static Logger log = LoggerFactory.getLogger(MvcConfig.class);

    @Autowired
    private Environment env;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.profiles.active}")
    private String[] profiles;

    @PostConstruct
    public void init() {
        log.debug("***** Mvc Active Profiles : " + StringUtils.join(env.getActiveProfiles()) + " *****");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setSerializationInclusion(Include.NON_NULL);
        converter.setObjectMapper(mapper);
        converters.add(converter);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry pRegistry) {
        pRegistry.jsp("/WEB-INF/views/", ".jsp");
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//            String allowDomain = "";
//            registry.addMapping("/**")
//                    .allowedOrigins(allowDomain)
//                    .allowedMethods("POST", "GET");
//    }
}
