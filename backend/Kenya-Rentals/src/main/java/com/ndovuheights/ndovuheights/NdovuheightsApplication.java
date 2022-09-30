package com.ndovuheights.ndovuheights;

import com.ndovuheights.ndovuheights.controller.ApartmentRestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;

@SpringBootApplication
@EnableWebMvc
@EnableSwagger2
public class NdovuheightsApplication {


	public static void main(String[] args) {
		SpringApplication.run(NdovuheightsApplication.class, args);
	}

}
