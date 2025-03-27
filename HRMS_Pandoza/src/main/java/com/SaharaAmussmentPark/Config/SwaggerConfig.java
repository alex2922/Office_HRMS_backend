package com.SaharaAmussmentPark.Config;
//
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import io.swagger.v3.oas.models.servers.Server;
//
//
//@Configuration
//public class SwaggerConfig {
//
//	 @Bean
//	    public OpenAPI customOpenAPI() {
//	        final String securitySchemeName = "bearerAuth";
//
//	        return new OpenAPI()
//	                .info(new Info()
//	                        .title("E-commerce_UserService Project")
//	                        .version("1.0")
//	                        .description("API documentation for Coffee Project"))
//	                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//	                .components(new io.swagger.v3.oas.models.Components()
//	                        .addSecuritySchemes(securitySchemeName, 
//	                                new SecurityScheme()
//	                                        .name(securitySchemeName)
//	                                        .type(SecurityScheme.Type.HTTP)
//	                                        .scheme("bearer")
//	                                        .bearerFormat("JWT")));
//	    }
////	@Bean
////	public OpenAPI customOpenAPI() {
////	    return new OpenAPI()
////	        .info(new Info()
////	            .title("E-commerce_UserService Project")
////	            .version("1.0")
////	            .description("API documentation for Coffie Project"));
//////	        .servers(List.of(new Server().url("https://tomcat.diwise.in/PolliticalStat/")));
////	}
//
//	
//}

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
@Configuration
public class SwaggerConfig {
@Bean
	public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	        .info(new Info()
	            .title("politicalTeam")
	            .version("1.0")
	            .description("API documentation for Coffie Project"));
//	        .servers(List.of(new Server().url("https://tomcat.diwise.in/PolliticalStat/")));
	}
}
