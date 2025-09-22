package com.example.ballkeeper.repository;

import com.example.ballkeeper.domain.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    boolean existsByEmail(String email);
    Optional<UserAccount> findByEmail(String email);

    List<UserAccount> findByAdminTrue(); // 관리자(admin=true)인 모든 사용자를 찾는 기능
}
