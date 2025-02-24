//package com.dailycodebuffer.client.debugging;
//
//import com.dailycodebuffer.client.debugging.service.TokenRetrieving;
//import com.nimbusds.jose.JOSEException;
//import com.nimbusds.jwt.JWT;
//import com.nimbusds.jwt.JWTClaimsSet;
//import com.nimbusds.jwt.JWTParser;
//import com.nimbusds.oauth2.sdk.auth.JWTAuthentication;
//import io.jsonwebtoken.Claims;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.text.ParseException;
//
//@Aspect
//@Component
//@Slf4j
//public class AccessTokenRetrieving
////    this class is created by me and not by the tutor. I have not completed it, I was just trying to debug some issue and then all of a sudden the issue resolved by itself due to a certain stupid fault
//{
//
//    @Autowired
//    private OAuth2AuthorizedClientManager authorizedClientManager;
//
//    @Autowired
//    private TokenRetrieving tokenRetrieving;
//
//    @Pointcut("execution(* com.dailycodebuffer.client.controller.HelloController.*(..))")
//    private void aControllerMethod() {}
//
//    @After("aControllerMethod()")
//    public void retrievingJwtToken(JoinPoint joinPoint) {
//
//            Object[] args = joinPoint.getArgs();
//
//            Authentication authentication = (Authentication) args[1];
//
//                OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("api-client-oidc")
//                        .principal(authentication)
//                        .build();
//                OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);
//
//        if (authorizedClient != null) {
//                OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
//            try {
//
//                String stringToken =accessToken.getTokenValue();
//                Claims claims = tokenRetrieving.verifyTokenAndExtractClaims(stringToken);
//
////                log.info(claims.getSubject());
////                log.info("4");
////                note related to the logging code lines in this class: I think logging affects the behavior of the application where this logging is added, the logging like interrupt the normal response or behavior of the application. in our case here, this
//
//
//////previous incomplete method --------- beginning
////                JWT jwt = JWTParser.parse(String.valueOf(accessToken));
////                JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();
////                String s = jwtClaimsSet.getSubject();
////                log.info(s);
//////previous incomplete method --------- end
//
//
//            } catch (ParseException | IOException | JOSEException e) {
////                log.info("5");
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
//
//}
