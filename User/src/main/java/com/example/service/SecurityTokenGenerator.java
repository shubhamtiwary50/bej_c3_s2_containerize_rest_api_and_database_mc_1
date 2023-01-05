package com.example.service;

import com.example.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String, String> tokenGenerator(User user);

}
