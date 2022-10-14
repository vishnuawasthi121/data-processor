package com.ogivetechnology.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.ogivetechnology.processor.repository")
@SpringBootApplication
public class DataProcessorApplication {

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(DataProcessorApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter()); // register PID write to spring boot. It will write PID to file
		springApplication.run(args);
	}

}
