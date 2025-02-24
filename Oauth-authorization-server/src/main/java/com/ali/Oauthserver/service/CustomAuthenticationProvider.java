package com.ali.Oauthserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // authenticate the user based on the provided username and password:
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // extract the username and password from the authentication object:
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // load the user details using a custom user details service:
        UserDetails user = customUserDetailsService.loadUserByUsername(username);

        // check if the provided password matches the user's password in the database:
        return checkPassword(user, password);
    }

    // check if the provided password matches the user's password in the database:
    private Authentication checkPassword(UserDetails user, String rawPassword) {
        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
            );
        }
        else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    // check if the authentication provider supports the provided authentication token:
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
