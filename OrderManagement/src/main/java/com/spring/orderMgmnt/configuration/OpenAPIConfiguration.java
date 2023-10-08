package com.spring.orderMgmnt.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfiguration {

	@Bean
	public OpenAPI customOpenAPI(@Value("${application_name}") String appName,
                                 @Value("${application_description}") String appDesciption,
                                 @Value("${application_version}") String appVersion,
                                 @Value("${terms_url}") String termsUrl,
                                 @Value("${license_name}") String licenseName,
                                 @Value("${license_url}") String licenseUrl) {
	    return new OpenAPI()
	                    .info(
                    		new Info()
                            .title(appName)
                            .version(appVersion)
                            .description(appDesciption)
                            .termsOfService(termsUrl)
                            .license(new License().name(licenseName).url(licenseUrl))
	                     );
	}
	
//	@Bean
//	public GroupedOpenApi customGroupedOpenApi() {
//			return GroupedOpenApi.builder()
//            .group("default")
//            .displayName("user API")
//            .pathsToMatch("**", "/admin/**")
//            .packagesToScan("com.example.api")
//            .build();
//	}
}
