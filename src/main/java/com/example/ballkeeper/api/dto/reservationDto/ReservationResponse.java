package com.example.ballkeeper.api.dto.reservationDto;

import com.example.ballkeeper.domain.reservation.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResponse(
        Long id,
        Long userId,
        String userName,
        Long itemId,
        String itemName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        ReservationStatus status
) {}
