//package com.ali.client.debugging.service;
//
//import com.nimbusds.jose.JOSEException;
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.openid.connect.sdk.claims.ClaimsSet;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.security.PublicKey;
//import java.text.ParseException;
//
//@Service
//public class TokenRetrieving
////    this class is created by me and not by the tutor. I have not completed it, I was just trying to debug some issue and then all of a sudden the issue resolved by itself due to a certain stupid fault
//{
//
//
//    public PublicKey retrievePublickey(String jwkSetEndpoint) throws IOException, ParseException, JOSEException {
//
//        JWKSet jwkSet = JWKSet.load(new URL(jwkSetEndpoint));
//
//        RSAKey rsaKey = (RSAKey) jwkSet.getKeys().get(0);
//
//        return rsaKey.toRSAPublicKey();
//    }
//
//    public Claims verifyTokenAndExtractClaims(String jwtToken) throws IOException, ParseException, JOSEException {
//        PublicKey publicKey = retrievePublickey("http://auth-server:9000/oauth2/jwks");
//
//        return Jwts
//                .parser()
//                .verifyWith(publicKey)
//                .build()
//                .parseSignedClaims(jwtToken)
//                .getPayload();
//    }
//
//
//}
