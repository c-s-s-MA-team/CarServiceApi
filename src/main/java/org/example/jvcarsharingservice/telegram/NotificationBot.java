package org.example.jvcarsharingservice.telegram;

import org.example.jvcarsharingservice.exception.BotNotificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class NotificationBot extends TelegramLongPollingBot {
    private final String botToken;
    private final String botUsername;

    public NotificationBot(@Value("${telegram.bot.token}") String botToken,
                           @Value("${telegram.bot.username}") String botUsername) {
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new BotNotificationException("Cannot send message", e);
        }
    }
}
