package com.example.ballkeeper.api.dto.itemDto;

public record ItemCreateRequest(
        Long adminUserId, String name, String description
) {}
