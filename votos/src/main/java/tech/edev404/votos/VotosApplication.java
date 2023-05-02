package tech.edev404.votos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class VotosApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotosApplication.class, args);
	}

}
