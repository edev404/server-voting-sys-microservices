package tech.edev404.aprendices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class AprendicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AprendicesApplication.class, args);
	}

}
