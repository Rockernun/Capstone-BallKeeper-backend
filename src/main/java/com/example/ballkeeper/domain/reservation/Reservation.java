package com.example.ballkeeper.domain.reservation;

import com.example.ballkeeper.domain.item.Item;
import com.example.ballkeeper.domain.user.UserAccount;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(indexes = {
        @Index(name="idx_resv_item_start_end", columnList = "item_id,startTime,endTime"),
        @Index(name="idx_resv_user", columnList = "user_id")
})
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    private UserAccount user;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    private Item item;

    @Column(nullable=false)
    private LocalDateTime startTime;

    @Column(nullable=false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private ReservationStatus status = ReservationStatus.PENDING;

    @Column(length=255)
    private String reason; // 거절 사유 등
}
