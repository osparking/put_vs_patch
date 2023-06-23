package space.bumtiger.http.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import space.bumtiger.http.domain.HeavyRepository;
import space.bumtiger.http.domain.HeavyResource;

@Configuration
public class TestConfig {
	final static Logger logger = LoggerFactory.getLogger(TestConfig.class);

	@Bean
	CommandLineRunner loadIngredient(HeavyRepository hRepository) {
		return args -> {
			var resource1 = new HeavyResource("탱크", "육군 전승부대 1번지");
			var resource2 = new HeavyResource("팬텀", "공군 비행단 수원기지");

			var reso = hRepository.save(resource1);
			logger.info(reso.getId().toString());
			reso = hRepository.save(resource2);
		};
	}
}
