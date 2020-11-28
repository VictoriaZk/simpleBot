package com.task.service;

import com.task.model.Message;
import com.task.model.TravelBot;
import com.task.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Voice;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TelegramService {
    private final CityRepository cityRepository;
    private final VoiceService voiceService;
    private final TravelBot travelBot;

    public void onWebhookUpdateReceived(Update update) throws IOException {
        Long chatId = update.getMessage().getChatId();

        try {
            travelBot.execute(new SendMessage(chatId, getAnswer(update.getMessage())));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getAnswer(org.telegram.telegrambots.meta.api.objects.Message message) throws IOException {
        if(!Objects.nonNull(message)) {
            return "";
        }

        if(message.hasVoice()) {
            Voice voice = message.getVoice();
            String town = voiceService.voiceToMessage(voice);
            return cityRepository.findDescriptionByName(town)
                    .orElse(Message.APOLOGIZE_MESSAGE.getApologizeMessage());

        }else if(message.hasText()){
            if (message.getText().equals("/start")) {
                return message.getFrom().getFirstName() + ", " + Message.HELLO_MESSAGE.getHelloMessage();
            }else {
                return cityRepository.findDescriptionByName(message.getText())
                        .orElse(Message.APOLOGIZE_MESSAGE.getApologizeMessage());
            }
        }else {
            return "";
        }
    }
}
