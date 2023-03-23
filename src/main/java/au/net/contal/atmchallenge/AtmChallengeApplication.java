package au.net.contal.atmchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "au.net.contal.atmchallenge.controller, au.net.contal.atmchallenge.services,au.net.contal.atmchallenge.model")
public class AtmChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmChallengeApplication.class, args);
	}

}
