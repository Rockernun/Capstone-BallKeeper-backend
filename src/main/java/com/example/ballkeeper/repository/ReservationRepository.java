package com.example.ballkeeper.repository;

import com.example.ballkeeper.domain.reservation.Reservation;
import com.example.ballkeeper.domain.reservation.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("""
       select r from Reservation r
       where r.item.id = :equipmentId
         and r.status <> com.example.ballkeeper.domain.reservation.ReservationStatus.CANCELLED
         and ( (r.startTime < :end) and (r.endTime > :start) )
       """)
    List<Reservation> findOverlaps(Long equipmentId, LocalDateTime start, LocalDateTime end);

    List<Reservation> findByUserIdOrderByStartTimeDesc(Long userId);

    List<Reservation> findByStatusOrderByStartTimeAsc(ReservationStatus status);

    boolean existsByItemIdAndStatusAndStartTimeLessThanAndEndTimeGreaterThan(
            Long itemId, ReservationStatus status,
            LocalDateTime end, LocalDateTime start);
}
