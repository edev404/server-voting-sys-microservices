package tech.edev404.estadisticas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class EstadisticasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstadisticasApplication.class, args);
	}

}
