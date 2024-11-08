package com.example.demo.usermanagement.userDetails;


import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.repository.UserRepository;
import com.example.demo.usermanagement.userDetails.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <User> optionalUser = userRepository.findByEmail(username);

        if(!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("username not found: " + username);
        }

        User user = optionalUser.get();
        return new MyUserDetails(user);
    }
}