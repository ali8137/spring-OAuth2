package com.ali.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.Principal;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
public class HelloController {

    @Autowired
    private WebClient webClient;

//    @GetMapping("/api/hello")
//    public String hello(Principal principal) {
//        return "Hello " + principal.getName() + ",Welcome to Daily Code Buffer!!";
//    }

    @GetMapping("/api/users")
    public String[] users(
            @RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client
    ) {
        return this.webClient // WebClient is a non-blocking, reactive HTTP client provided by Spring WebFlux
                .get()
                .uri("http://127.0.0.1:8090/api/users")
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(client))
                .retrieve() // sends the request and retrieves the response
                .bodyToMono(String[].class)
                .block(); // blocks the execution until the response is available, and then returns the result
    }
}
