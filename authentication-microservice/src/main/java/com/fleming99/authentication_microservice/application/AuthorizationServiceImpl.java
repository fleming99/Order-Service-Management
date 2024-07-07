package com.fleming99.authentication_microservice.application;

import com.fleming99.authentication_microservice.adapters.UserRepository;
import com.fleming99.authentication_microservice.core.dto.AuthenticationRequest;
import com.fleming99.authentication_microservice.core.dto.LoginResponse;
import com.fleming99.authentication_microservice.core.dto.SignupRequest;
import com.fleming99.authentication_microservice.core.entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements UserDetailsService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenServiceImpl tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username);
    }

    public ResponseEntity<Object> login(@Valid AuthenticationRequest authenticationRequest){
        AuthenticationManager authenticationManager = applicationContext.getBean(AuthenticationManager.class);
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    public ResponseEntity<Object> register(@RequestBody SignupRequest signupRequest){
        if (this.userRepository.findUserByEmail(signupRequest.getEmail()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(signupRequest.getPassword());
        User user = User.builder()
                .email(signupRequest.getEmail())
                .password(encryptedPassword)
                .userRole(signupRequest.getUserRole())
                .createdAt(new Date(System.currentTimeMillis()))
                .build();
        userRepository.save(user);
        return ResponseEntity.ok().build();

    }
}
