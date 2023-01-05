package com.example.controller;


import com.example.domain.User;
import com.example.service.SecurityTokenGenerator;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    UserService userService;
    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/adduser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }
    @GetMapping("/users/{lastName}")
    public ResponseEntity<?> getAllUsersByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(userService.getUserByLastName(lastName), HttpStatus.FOUND);
    }
    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email){
        return new ResponseEntity<>(userService.deleteUserByEmail(email), HttpStatus.OK);
    }
    @PutMapping("/user/{email}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String email) {
        return new ResponseEntity<>(userService.updateUser(user,email), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody User user){
//        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        User result= userService.loginCheck(user.getEmail(),user.getPassword());
        if(result!=null){
            Map<String, String> map = securityTokenGenerator.tokenGenerator(result);
            return new ResponseEntity<>(map,HttpStatus.OK);
        } else{
            return new ResponseEntity<>("invalid user or user not exist",HttpStatus.NOT_FOUND);
        }
    }

}