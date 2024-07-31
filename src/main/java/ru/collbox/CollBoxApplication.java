package ru.collbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.collbox.config.EnvConfig;

@SpringBootApplication
public class CollBoxApplication {

	public static void main(String[] args) {
		EnvConfig.loadEnv();
		SpringApplication.run(CollBoxApplication.class, args);
	}
}
