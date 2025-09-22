package com.example.ballkeeper.api.dto.userDto;

public record SignUpRequest(
        String email, String password, String name, boolean admin
) {}
