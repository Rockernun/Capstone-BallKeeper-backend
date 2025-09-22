package com.example.ballkeeper.controller;

import com.example.ballkeeper.api.dto.reservationDto.ReservationResponse;
import com.example.ballkeeper.domain.reservation.Reservation;
import com.example.ballkeeper.service.AdminReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reservations")
@RequiredArgsConstructor
public class AdminReservationController {

    private final AdminReservationService adminReservationService;

    // 대기중 목록
    @GetMapping("/pending")
    public List<ReservationResponse> pending() {
        return adminReservationService.pendingList().stream()
                .map(this::toDto)
                .toList();
    }

    // 승인 반려
    @PostMapping("/{id}/reject")
    public ReservationResponse reject(@RequestParam Long adminId,
                                      @PathVariable Long id,
                                      @RequestParam(required = false) String reason) {
        var r = adminReservationService.reject(adminId, id, reason);
        return toDto(r);
    }

    // 승인
    @PostMapping("/{id}/approve")
    public ReservationResponse approve(@RequestParam Long adminId, @PathVariable Long id) {
        var r = adminReservationService.approve(adminId, id);
        return toDto(r);
    }

    private ReservationResponse toDto(Reservation r) {
        return new ReservationResponse(
                r.getId(),
                r.getUser().getId(),
                r.getUser().getName(),
                r.getItem().getId(),
                r.getItem().getName(),
                r.getStartTime(),
                r.getEndTime(),
                r.getStatus()
        );
    }
}
