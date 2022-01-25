package ch.egroup.interview;

import ch.egroup.interview.security.RestSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RestSecurityConfig.class)
public class GavRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(GavRestApplication.class, args);
	}
}