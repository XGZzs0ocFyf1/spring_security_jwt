package security.root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class NyansusApplication {

	public static void main(String[] args) {
		SpringApplication.run(NyansusApplication.class, args);
	}

}
