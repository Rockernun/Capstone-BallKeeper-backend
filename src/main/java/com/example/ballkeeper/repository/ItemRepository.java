package com.example.ballkeeper.repository;

import com.example.ballkeeper.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByActiveTrue();
}
