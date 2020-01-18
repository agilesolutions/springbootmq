package ch.agilesolutions.boot;

import static net.logstash.logback.argument.StructuredArguments.kv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import ch.agilesolutions.boot.model.User;
import ch.agilesolutions.boot.repository.UserRepository;

@SpringBootApplication
@EnableJms
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			User user1 = new User("John");
			User user2 = new User("Mary");
			repository.save(user1);
			repository.save(user2);

			logger.info("Added owners", kv("type", "SAL"));

		};
	}

}
