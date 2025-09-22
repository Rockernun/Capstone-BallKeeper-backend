package com.example.ballkeeper.controller;

import com.example.ballkeeper.domain.item.Item;
import com.example.ballkeeper.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/items")
@RequiredArgsConstructor
public class AdminItemController {

    private final ItemRepository itemRepository;

    // 물품 활성/비활성(관리자 전용)
    @PatchMapping("/{id}/active")
    public Item updateActive(@PathVariable("id") long id, @RequestParam boolean active) {
        var item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + id));
        item.setActive(active);
        return itemRepository.save(item);
    }
}
