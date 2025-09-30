package com.example.ballkeeper.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultFunctions("createReservation", "myReservations", "cancelReservation")
                .build();
    }

    public String chat(Long userId, String userMessage) {
        // 현재 시간을 AI에게 알려주기 위해 문자열로 포맷
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        return chatClient.prompt()
                .system(String.format("""
                    당신은 BallKeeper 예약 관리 챗봇입니다.
                    사용자를 도와 예약을 생성, 조회, 취소하는 역할을 합니다.
                    현재 대화하는 사용자의 ID는 %d 입니다.
                    현재 서버 시간은 %s 입니다. "오늘", "내일" 같은 상대적인 시간은 이 시간을 기준으로 계산해야 합니다.

                    '예약 생성'을 위해서는 사용자 ID, 물품 ID, 시작 시간, 종료 시간이 필요합니다.
                    '내 예약 조회'를 위해서는 사용자 ID가 필요합니다.
                    '예약 취소'를 위해서는 사용자 ID와 예약 ID가 필요합니다.

                    **가장 중요한 규칙**: 만약 함수를 호출하는 데 필요한 모든 정보를 사용자의 메시지로부터 명확히 파악했다면,
                    절대로 사용자에게 되묻거나 확인하지 말고 즉시 함수를 호출하세요.
                    정보가 부족할 경우에만 사용자에게 추가 정보를 요청하세요.
                    """, userId, currentTime))
                .user(userMessage)
                .call()
                .content();
    }
}