package com.example.service;


import com.example.domain.User;

import java.util.List;

public interface UserService
{
    User saveUser(User user) ;
    List<User> getAllUsers();
    User updateUser(User user, String email );
    boolean deleteUserByEmail(String email);
    List<User> getUserByLastName(String lastName);
    User loginCheck(String email, String password);
}
