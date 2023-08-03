package com.pb.week11;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@OpenAPIDefinition(
		info = @Info(
				title = "Spring boot Week 11 App Rest API's",
				description = "Spring boot Wekk 11 documentaion",
				version = "v1.0",
				contact = @Contact(
						name="extremehi",
						email="extremehi@gmail.com",
						url="extremehi.net"
				),
				license = @License(
						name = "Apache 2.0",
						url="extremehi.net"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot doc",
				url="extremehi.net"
		)
)
public class Week11Application {

	public static void main(String[] args) {
		SpringApplication.run(Week11Application.class, args);
	}

}
