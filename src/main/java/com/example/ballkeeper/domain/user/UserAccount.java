package com.example.ballkeeper.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=100)
    private String email;

    @Column(name = "password_hash", nullable=false, length=255)
    private String password; // 지금은 시큐리티 끈 상태라임시 보관. 추후 암호화로 교체.

    @Column(nullable=false, length=50)
    private String name;

    private boolean admin; // 간단 구분 (추후 ROLE 테이블로 확장 가능)
}
