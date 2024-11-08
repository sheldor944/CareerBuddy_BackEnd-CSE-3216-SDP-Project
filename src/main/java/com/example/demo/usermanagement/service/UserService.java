package com.example.demo.usermanagement.service;

import com.example.demo.usermanagement.UserRequest;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public User createUser(UserRequest userRequest){
       User user = new User(userRequest.getEmail(), userRequest.getPassword());
       return userRepository.save(user);

    }
    public User getUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) return optionalUser.get();
        return null;
    }
}
