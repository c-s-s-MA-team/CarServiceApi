package org.example.jvcarsharingservice.servece.notification;

import org.example.jvcarsharingservice.telegram_bots.NotificationBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationBot notificationBot;
    private final String adminChatId;

    public NotificationService(NotificationBot notificationBot,
                               @Value("${telegram.admin.chat.id}") String adminChatId) {
        this.notificationBot = notificationBot;
        this.adminChatId = adminChatId;
    }

    public void notifyNewRentalsCreated(String message) {
        String info = """
                new rentals created: 
                """ + message;
        notificationBot.sendMessage(adminChatId, info);
    }

    public void notifyOverdueRentals(String message) {
        String info = """
                overdue rentals: 
                """ + message;
        notificationBot.sendMessage(adminChatId, info);
    }

    public void notifySuccessfulPayments(String message) {
        String info = """
                successful payments: 
                """ + message;
        notificationBot.sendMessage(adminChatId, info);
    }
}
