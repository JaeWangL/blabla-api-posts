package com.blabla.posts.api;

import com.blabla.posts.common.outbox.EnableOutbox;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableSpringConfigured
@EnableOutbox
@EnableJpaRepositories
@EntityScan
public class PostsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostsApiApplication.class, args);
    }
}
