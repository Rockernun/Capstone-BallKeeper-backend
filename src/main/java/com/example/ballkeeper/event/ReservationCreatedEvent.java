package com.example.ballkeeper.event;

import com.example.ballkeeper.domain.reservation.Reservation;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 예약이 생성되었을 때 발생하는 이벤트를 나타내는 클래스.
 */
@Getter
public class ReservationCreatedEvent extends ApplicationEvent {

    private final Reservation reservation;

    public ReservationCreatedEvent(Object source, Reservation reservation) {
        super(source);
        this.reservation = reservation;
    }
}