package app.learn.cacheddataserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisWithDataBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisWithDataBaseApplication.class, args);
	}
}
