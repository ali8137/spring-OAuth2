package com.ali.Oauthserver.config;

import com.ali.Oauthserver.service.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DefaultSecurityConfig
{
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry.anyRequest().authenticated()
                ) // any request to any endpoint, other than the endpoints defined in other security filter chain instances, needs the user to be authenticated first
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    // add the custom authentication provider "CustomAuthenticationProvider" to the authentication manager
    @Autowired
    public void bindAuthenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder)
    {
        authenticationManagerBuilder
                .authenticationProvider(customAuthenticationProvider);
    }
}

