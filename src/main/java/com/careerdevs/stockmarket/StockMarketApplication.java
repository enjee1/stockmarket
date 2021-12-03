package com.careerdevs.stockmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
	Use data set 2 when developing and testing, use data set 1 to confirm your code works.
	We are limited to 500 requests a day. While this should be more than enough, it is smart to reduce the number of requests when possible.
*/
@SpringBootApplication
public class StockMarketApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(StockMarketApplication.class, args);
	}

}
