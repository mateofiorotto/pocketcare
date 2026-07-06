package com.mateofiorotto.pocketcare.controller;

import com.mateofiorotto.pocketcare.dto.auth.AuthLoginRequestDTO;
import com.mateofiorotto.pocketcare.dto.auth.AuthRegisterRequestDTO;
import com.mateofiorotto.pocketcare.service.usersec.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid AuthRegisterRequestDTO userRequest) {
        return new ResponseEntity<>(this.userDetailsService.registerUser(userRequest), HttpStatus.OK);
    }

}

