package com.communityhub.notification_service.domain.strategy;

import org.springframework.stereotype.Component;

@Component
public class EmailNotificationStrategy implements NotificationStrategy {

    @Override
    public void send(String payload) {
        System.out.println("📧 Enviando EMAIL para os moradores: " + payload);
    }

}
