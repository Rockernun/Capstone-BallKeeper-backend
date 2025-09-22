package com.example.ballkeeper.service;

import com.example.ballkeeper.api.dto.itemDto.ItemCreateRequest;
import com.example.ballkeeper.api.dto.itemDto.ItemResponse;
import com.example.ballkeeper.domain.item.Item;
import com.example.ballkeeper.repository.ItemRepository;
import com.example.ballkeeper.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional
    public ItemResponse create(ItemCreateRequest req) {
        var admin = userAccountRepository.findById(req.adminUserId())
                .orElseThrow(() -> new IllegalArgumentException("관리자 사용자 없음"));
        if (!admin.isAdmin()) throw new IllegalArgumentException("관리자 권한이 없습니다.");

        var eq = Item.builder()
                .name(req.name())
                .description(req.description())
                .build();
        itemRepository.save(eq);
        return new ItemResponse(eq.getId(), eq.getName(), eq.getDescription());
    }
}
