package com.example.ballkeeper.service;

import com.example.ballkeeper.event.ReservationCreatedEvent;
import com.example.ballkeeper.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserAccountRepository userAccountRepository;

    /**
     * ReservationCreatedEvent가 발생했을 때 이 메소드가 실행
     * @EventListener 어노테이션이 이벤트 수신을 담당
     */
    @EventListener
    @Transactional(readOnly = true)
    public void handleReservationCreated(ReservationCreatedEvent event) {
        var reservation = event.getReservation();
        var admins = userAccountRepository.findByAdminTrue();

        if (admins.isEmpty()) {
            log.warn("알림을 보낼 관리자가 없습니다.");
            return;
        }

        // 모든 관리자에게 알림 보냄 (현재는 로그로 대체)
        for (var admin : admins) {
            log.info("관리자 '{}'에게 알림: 사용자 '{}'님이 '{}'에 대한 신규 예약을 생성했습니다. (예약 ID: {})",
                    admin.getName(),
                    reservation.getUser().getName(),
                    reservation.getItem().getName(),
                    reservation.getId());
            // TODO: 추후 이메일, SMS, 웹소켓 푸시 알림 등으로 확장가능
        }
    }
}