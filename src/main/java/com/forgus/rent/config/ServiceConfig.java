package com.forgus.rent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by cwb on 2016/3/8.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.forgus.rent.repository"
)
@ImportResource({"classpath:service_config_dev.xml"})
public class ServiceConfig {
}
