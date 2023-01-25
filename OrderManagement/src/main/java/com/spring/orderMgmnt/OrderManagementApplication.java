package com.spring.orderMgmnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class OrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}
	
    	@Bean
    	public Docket api() { 
        	return new Docket(DocumentationType.SWAGGER_2)  
          		.select()                                  
          		.apis(RequestHandlerSelectors.any())              
          		.paths(PathSelectors.any())                          
          		.build();                                           
    	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
    		registry.addResourceHandler("swagger-ui.html")
      		.addResourceLocations("classpath:/META-INF/resources/");

    		registry.addResourceHandler("/webjars/**")
      		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
