package com.quanti_learn.auth_service;

import com.quanti_learn.auth_service.utils.PreLoadData;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Auth Service", version = "1.0.0")
)
public class AuthServiceApplication implements CommandLineRunner {

	private final PreLoadData preLoadData;

	public AuthServiceApplication(PreLoadData preLoadData){
		this.preLoadData=preLoadData;
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println(args);
		preLoadData.loadRequiredData();
	}
}
