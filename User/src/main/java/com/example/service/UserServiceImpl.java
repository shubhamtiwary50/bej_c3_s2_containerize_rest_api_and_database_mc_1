package com.example.service;


import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user)  {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User updateUser(User user, String email) {
        Optional<User> optUser = userRepository.findById(email);
        if(optUser.isEmpty())
        {
            return null;
        }
        User existingUser = optUser.get();
        if(user.getFirstName()!=null){
            existingUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null){
            existingUser.setLastName(user.getLastName());
        }
        if(user.getPassword()!=null){
            existingUser.setPassword(user.getPassword());
        }
        return userRepository.save(existingUser);
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        userRepository.deleteById(email);
        return true;
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);

    }

    @Override
    public User loginCheck(String email, String password) {

        if(userRepository.findById(email).isPresent()){

            User user = userRepository.findById(email).get();

            if(user.getPassword().equals(password)){

                return user;

            }else {
                return null;
            }
        } else {
            return null;
        }
    }


}
