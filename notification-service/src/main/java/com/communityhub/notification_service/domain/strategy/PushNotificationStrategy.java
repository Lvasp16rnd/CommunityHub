package com.communityhub.notification_service.domain.strategy;

import org.springframework.stereotype.Component;

@Component
public class PushNotificationStrategy implements NotificationStrategy {
    @Override
    public void send(String payload) {
        System.out.println("📱 Enviando PUSH NOTIFICATION para o celular: " + payload);
    }
}
