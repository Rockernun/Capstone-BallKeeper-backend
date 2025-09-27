package com.example.ballkeeper.api.chat;

import com.example.ballkeeper.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    public record ChatRequest(Long userId, String message) {}

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return chatService.chat(request.userId(), request.message());
    }
}
