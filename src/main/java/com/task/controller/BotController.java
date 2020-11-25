package com.task.controller;

import com.task.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class BotController {
    private final TelegramService telegramService;

    @PostMapping
    public ResponseEntity<?> onUpdateReceived(@RequestBody Update update) throws TelegramApiException, IOException {
        telegramService.onWebhookUpdateReceived(update);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
