package com.findworkbuddy.mainapiservice;

import com.findworkbuddy.mainapiservice.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args).setId("Find work buddy");
	}

}
