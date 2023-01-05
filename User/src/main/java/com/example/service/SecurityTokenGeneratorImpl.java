package com.example.service;

import com.example.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SecurityTokenGeneratorImpl implements  SecurityTokenGenerator{

    @Override
    public Map<String, String> tokenGenerator(User user) {

        String jwtToken=null;
        jwtToken= Jwts.builder().setSubject(user.getFirstName()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"Security Key").compact();


        Map<String,String> map = new HashMap<>();
        map.put("token", jwtToken);
        map.put("message","user logged in successfully");
        return map;
    }
}
