package com.example.ballkeeper.api.dto.userDto;

public record UserResponse(
        Long id, String email, String name, boolean admin
) {}
