package com.example.demo.usermanagement.controller;


import com.example.demo.usermanagement.AuthDTO;
import com.example.demo.usermanagement.UserDTO;
import com.example.demo.usermanagement.UserRequest;
import com.example.demo.usermanagement.authentication.AuthenticationResponse;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.userDetails.MyUserDetails;
import com.example.demo.usermanagement.service.UserService;
import com.example.demo.usermanagement.userDetails.MyUserDetailsService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private JwtUtil jwtUtil;
    private ResponseEntity <?> getAuthenticationToken(UserRequest authenticationRequest) throws Exception {
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    @PostMapping("/create-user")
    public UserDTO createUser(@RequestBody UserRequest userRequest){
        User user = userService.createUser(userRequest);
        System.out.println(user.getEmail());
        MyUserDetails myUserDetails = new MyUserDetails(user);
        String token = jwtUtil.generateToken(myUserDetails);
        System.out.println(token );
//        return new AuthDTO(token);
        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail());
        return new UserDTO(user.getId(), user.getEmail());
    }


}
