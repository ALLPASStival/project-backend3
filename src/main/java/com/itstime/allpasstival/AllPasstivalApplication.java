package com.itstime.allpasstival;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AllPasstivalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllPasstivalApplication.class, args);
	}

}
