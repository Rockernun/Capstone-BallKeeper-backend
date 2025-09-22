//package com.example.ballkeeper.api.chat;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.ai.chat.client.ChatClient;
//
//@RestController
//@RequestMapping("/api/chat")
//public class ChatController {
//    private final ChatClient client;
//
//    public ChatController(ChatClient.Builder builder) {
//        this.chat = builder.build();
//    }
//
//    @GetMapping("/ask")
//    public String ask(@RequestParam String q){
//        var res = chat.prompt()
//                .user(q)
//                .call()
//                .content();
//        return res;
//    }
//}

