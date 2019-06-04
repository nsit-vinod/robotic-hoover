package com.yoti.hoover.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*
 * Adding swagger2 for api documentation.
 * @author      Vinod Kumar
 * @since       1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig  {
	/*
	 * creating Docket of SWAGGER_2 type
	 */
	@Bean
    public Docket api() {
    	return new Docket(DocumentationType.SWAGGER_2);
    }
    
    
}