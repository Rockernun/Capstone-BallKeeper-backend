package com.example.ballkeeper.controller;

import com.example.ballkeeper.api.dto.userDto.LoginRequest;
import com.example.ballkeeper.api.dto.userDto.SignUpRequest;
import com.example.ballkeeper.api.dto.userDto.UserResponse;
import com.example.ballkeeper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody SignUpRequest req) {
        return userService.signUp(req);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest req) {
        return userService.login(req);
    }
}
