package com.communityhub.notification_service.infrastructure.messaging;

import io.awspring.cloud.sqs.annotation.SqsListener;

import java.util.List;

import org.springframework.stereotype.Component;

import com.communityhub.notification_service.domain.strategy.NotificationStrategy;

@Component
public class IncidentEventListener {

    private final List<NotificationStrategy> strategies;

    public IncidentEventListener(List<NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    @SqsListener("notification-queue")
    public void listener(String payload) {
        System.out.println("recebi um evento do SQS!");

        for (NotificationStrategy strategy : strategies) {
            strategy.send(payload);
        }
    }
}
