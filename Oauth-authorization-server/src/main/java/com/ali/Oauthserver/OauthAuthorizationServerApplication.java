package com.ali.Oauthserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OauthAuthorizationServerApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(OauthAuthorizationServerApplication.class, args);
	}

//	@Override
//	public void run(String... args) {
//		System.out.println("MYSQL_DB_USERNAME: " + System.getenv("MYSQL_DB_USERNAME"));
//		System.out.println("MYSQL_DB_PASSWORD: " + System.getenv("MYSQL_DB_PASSWORD"));
//	}

}
