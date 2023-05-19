package tech.fabricatic.votaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class VotacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotacionesApplication.class, args);
	}

}
