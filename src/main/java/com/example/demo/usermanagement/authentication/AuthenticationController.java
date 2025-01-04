package com.example.demo.usermanagement.authentication;

import com.example.demo.usermanagement.UserDTO;
import com.example.demo.usermanagement.models.User;
import com.example.demo.usermanagement.repository.UserRepository;
import com.example.demo.usermanagement.service.UserService;
import com.example.demo.usermanagement.userDetails.MyUserDetailsService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@RestController
public class AuthenticationController {
    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;



    private ResponseEntity <?> getAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }



    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> getToken(@RequestParam String username, @RequestParam String password) {
        System.out.println("user login attempt");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println("login authenticated");
        }
        catch (BadCredentialsException e) {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUser(username);

        if(!user.isVerified()) {
            return new ResponseEntity<>("User is not verified yet", HttpStatus.BAD_REQUEST);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        HashMap<String,String> token = new HashMap<>();

        token.put("token_type", "Bearer");
        token.put("access_token", jwt);

        return ResponseEntity.ok(token);
    }

    @PostMapping(value = "/authenticate/user_login")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println("user login attem11pt");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
            System.out.println("login authenticated");
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        catch (Exception e){
            System.out.println(e);
            throw  new Exception(e);
        }
        System.out.println("here");
//        User user = userService.getUser(authenticationRequest.getEmail());
//        if(!user.getUserRoles().equals(ROLE_USER)) {
//            throw new Exception("Role did not match");
//        }
//
//        if(!user.isVerified()) {
//            throw new Exception("User is not verified yet");
//        }
        User user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("user not found: " ));
        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(),user.getName());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticate/employee_login", method = RequestMethod.POST)
    public ResponseEntity<?> loginEmployee(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        User user = userService.getUser(authenticationRequest.getEmail());
        if(!user.getUserRoles().equals(ROLE_EMPLOYEE)) {
            throw new Exception("Role did not match");
        }

        return getAuthenticationToken(authenticationRequest);
    }

    @RequestMapping(value = "/authenticate/admin_login", method = RequestMethod.POST)
    public ResponseEntity<?> loginAdmin(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        User user = userService.getUser(authenticationRequest.getEmail());
        if(!user.getUserRoles().equals(ROLE_ADMIN)) {
            throw new Exception("Role did not match");
        }

        return getAuthenticationToken(authenticationRequest);
    }
}