package com.example.ballkeeper.controller;

import com.example.ballkeeper.api.dto.itemDto.ItemCreateRequest;
import com.example.ballkeeper.api.dto.itemDto.ItemResponse;
import com.example.ballkeeper.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemResponse create(@RequestBody ItemCreateRequest req) {
        return itemService.create(req);
    }
}
