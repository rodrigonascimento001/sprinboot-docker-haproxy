package com.api.dockerspringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DockerspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerspringbootApplication.class, args);
	}

	@Component
	public class MyBean implements CommandLineRunner {
		private final Environment env;

		public MyBean(Environment env) {
			this.env = env;
		}

		public void run(String... args) {
			System.out.println("teste%%%%%%%%%%%%%%"+ env.getProperty("teste1"));
		}

	}
}
