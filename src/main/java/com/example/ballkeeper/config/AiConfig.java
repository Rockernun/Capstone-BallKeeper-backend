package com.example.ballkeeper.config;

import com.example.ballkeeper.service.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class AiConfig {

    @Bean
    @Description("사용자를 위해 물품을 예약합니다. 예약을 위해서는 사용자 ID, 물품 ID, 시작 시간, 종료 시간이 반드시 필요합니다.")
    public Function<ReservationRequest, String> createReservation(ReservationService reservationService) {
        return request -> {
            try {
                var reservation = reservationService.create(
                        request.userId, request.itemId, request.startTime, request.endTime);
                return "예약이 성공적으로 생성되었습니다. 예약 ID는 " + reservation.getId() + " 입니다.";
            } catch (Exception e) {
                return "예약 생성에 실패했습니다. 원인: " + e.getMessage();
            }
        };
    }

    @Bean
    @Description("특정 사용자의 모든 예약 목록을 조회합니다. 이 기능을 사용하려면 사용자 ID가 반드시 필요합니다.")
    public Function<MyReservationsRequest, String> myReservations(ReservationService reservationService) {
        return request -> {
            var reservations = reservationService.myReservations(request.userId);
            if (reservations.isEmpty()) {
                return "아직 등록된 예약이 없습니다.";
            }
            String reservationList = reservations.stream()
                    .map(r -> String.format("- 예약번호 %d: %s, 시간: %s ~ %s (%s)",
                            r.getId(), r.getItem().getName(), r.getStartTime(), r.getEndTime(), r.getStatus()))
                    .collect(Collectors.joining("\n"));
            return "총 " + reservations.size() + "개의 예약이 있습니다:\n" + reservationList;
        };
    }

    public record ReservationRequest(Long userId, Long itemId, LocalDateTime startTime, LocalDateTime endTime) {}
    public record MyReservationsRequest(Long userId) {}
}