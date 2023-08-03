package com.pb.week11.controller;

import com.pb.week11.DTO.request.LoginDTORequest;
import com.pb.week11.DTO.request.RegisterDTORequest;
import com.pb.week11.security.JwtAuthResponse;
import com.pb.week11.service.register.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTORequest request){
        return new ResponseEntity<>(registerService.register(request), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTORequest loginDTORequest){
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(registerService.login(loginDTORequest));
        return ResponseEntity.ok(jwtAuthResponse);
    }

}
