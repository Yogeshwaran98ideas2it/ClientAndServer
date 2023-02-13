package com.example.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/**
 * The Class HealthcareApplication.
 */
/**
 * @author Yogesh
 *
 */
@SpringBootApplication
@EnableJpaAuditing

public class HealthcareApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(HealthcareApplication.class, args);
	}

}
