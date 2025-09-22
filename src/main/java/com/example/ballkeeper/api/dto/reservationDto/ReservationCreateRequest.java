package com.example.ballkeeper.api.dto.reservationDto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservationCreateRequest(
        Long userId, Long itemId,
        LocalDateTime startTime, LocalDateTime endTime
) {}
