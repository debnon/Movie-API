package com.MovieAPI;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class MovieApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApiApplication.class, args);
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("movies-api-public")
                .pathsToMatch("/api/v1/**")
                .pathsToExclude("/api/v1/admin/**", "/api/v1/tmdb/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("movies-api-admin")
                .pathsToMatch("/api/v1/**", "/actuator/info", "/actuator/health")
                .pathsToExclude("/api/v1/movie/**", "/api/v1/user/**")
                .build();
    }

    @Bean
    public OpenAPI moviesOpenAPIInformation() {
        return new OpenAPI()
                .info(new Info().title("Movies API")
                        .description("Need to know details about movies..? Here is the API for you...!!!")
                        .version("1.0"));
    }

}


