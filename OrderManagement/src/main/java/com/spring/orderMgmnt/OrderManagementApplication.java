package com.spring.orderMgmnt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@EnableScheduling
public class OrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}
	
    @Bean
	public OpenAPI customOpenAPI(@Value("${application-name}") String appName,
			@Value("${application-description}") String appDesciption,
			@Value("${application-version}") String appVersion,
			@Value("${terms-url}") String termsUrl,
			@Value("${license-name}") String licenseName,
			@Value("${license-url}") String licenseUrl) {
        return new OpenAPI()
          .info(new Info()
          .title(appName)
          .version(appVersion)
          .description(appDesciption)
          .termsOfService(termsUrl)
          .license(new License().name(licenseName).url(licenseUrl)));
    }

}