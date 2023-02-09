package com.example.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// TODO: Auto-generated Javadoc
/**
 * The Class HealthcareApplication.
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
