package com.task.service.impl.impl;

import com.task.exception.Message;
import com.task.model.TravelBot;
import com.task.repository.CityRepository;
import com.task.service.impl.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TelegramServiceImpl implements TelegramService {
    private final CityRepository cityRepository;
    private final TravelBot travelBot;

    @Override
    public void onWebhookUpdateReceived(Update update) throws TelegramApiException {
        if (update.getMessage().getText().equals("/start")) {
            travelBot.execute(new SendMessage(update.getMessage().getChatId(), update.getMessage().getFrom().getFirstName() + ", " + Message.HELLO_MESSAGE.getHelloMessage()));
        } else if (Objects.nonNull(update.getMessage()) && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String name = update.getMessage().getText();
            String cityDescription = cityRepository.findDescriptionByName(name).orElse(Message.APOLOGIZE_MESSAGE.getApologizeMessage());

            try {
                travelBot.execute(new SendMessage(chatId, cityDescription));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
