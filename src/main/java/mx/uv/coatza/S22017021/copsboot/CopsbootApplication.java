package mx.uv.coatza.S22017021.copsboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CopsbootApplication {

	@RequestMapping("/")
	public String home() {
		return """
				Kevin Sebastian Frias García
				S22017021
				Copsboot application
				""";
	}

	public static void main(String[] args) {
		SpringApplication.run(CopsbootApplication.class, args);
	}

}
