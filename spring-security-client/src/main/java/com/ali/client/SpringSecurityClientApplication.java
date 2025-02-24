package com.ali.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// run first the module "Oauth-authorization-server" and then the module "Oauth-resource-server"
@SpringBootApplication
public class SpringSecurityClientApplication
{

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityClientApplication.class, args);
	}

}