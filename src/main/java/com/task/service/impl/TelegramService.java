package com.task.service.impl;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public interface TelegramService {
    void onWebhookUpdateReceived(Update update) throws TelegramApiException, IOException;
}
