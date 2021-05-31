package com.panxk.actual;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ActualApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActualApplication.class, args);
	}


	@Bean
	public Redisson redissson(){
		Config config = new Config();
		config.useSingleServer().setAddress("redis://192.168.1.231:6379").setDatabase(0);

		return (Redisson) Redisson.create(config);
	}

}
