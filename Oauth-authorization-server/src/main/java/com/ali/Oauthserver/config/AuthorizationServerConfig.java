package com.ali.Oauthserver.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0 by Initializing `OidcConfigurer`

        return http.formLogin(Customizer.withDefaults()).build();
    }

    // create a new RegisteredClient object with specific details such as client ID, client secret, supported grant types, redirect URIs, and scopes:
    @Bean
    public RegisteredClientRepository registeredClientRepository() { // The client is then stored in an in-memory repository (InMemoryRegisteredClientRepository)
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("api-client")
                .clientSecret(passwordEncoder.encode("secret"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/api-client-oidc")
                .redirectUri("http://127.0.0.1:8080/authorized")
                .scope(OidcScopes.OPENID)
                .scope("api.read")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    // create the private key, which will be used to generate the digital signature of the token by the authorization server, and create the associated public key of this private key:
    @Bean
    public JWKSource<SecurityContext> jwkSource() { // the authorization server can provide the necessary public keys for clients and resource servers to verify the digital signatures on tokens
        // generate an RSA key pair:
        RSAKey rsaKey = generateRsa(); // An RSA key pair consists of a public key and a private key. The public key is used for verifying signatures, and the private key is used for generating signatures.

        // Create a JSON Web Key Set (JWK Set) using the generated RSA key:
        JWKSet jwkSet = new JWKSet(rsaKey);

        return ((jwkSelector, securityContext) -> jwkSelector.select(jwkSet));
    }

    // generate an RSA key pair:
    private static RSAKey generateRsa()
    {
        KeyPair keyPair = generateRsaKey();

        // extracts the public key (RSAPublicKey) and private key (RSAPrivateKey) from the generated key pair above:
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        // configure RSA keys:
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    // generate an RSA key pair:
    private static KeyPair generateRsaKey()
    {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    // configure settings specific to the authorization server:
    @Bean
    public AuthorizationServerSettings providerSettings()
    {
        return AuthorizationServerSettings.builder()
                .issuer("http://auth-server:9000")
                .build();
    }

}