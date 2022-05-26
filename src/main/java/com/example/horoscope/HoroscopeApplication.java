package com.example.horoscope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HoroscopeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoroscopeApplication.class, args);
	}

}
