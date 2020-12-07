package com.task.service;

import com.task.model.Message;
import com.task.model.TravelBot;
import com.task.model.dto.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Voice;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TelegramService {
    private final CityService cityService;
    private final VoiceService voiceService;
    private final TravelBot travelBot;
    private final ImageService imageService;

    public void onWebhookUpdateReceived(Update update) throws IOException {
        sendAnswer(update.getMessage());
    }

    private void sendAnswer(org.telegram.telegrambots.meta.api.objects.Message message) throws IOException {
        if(!Objects.nonNull(message)) {
            return;
        }

        String textMessage = "";

        if(message.hasVoice()){
            Voice voice = message.getVoice();
            textMessage = voiceService.voiceToMessage(voice);
        }else if(message.hasText()) {
            textMessage = message.getText();
        }else {
            return;
        }

        if (textMessage.equals("/start")) {
            String answer = message.getFrom().getFirstName() + ", " + Message.HELLO_MESSAGE.getHelloMessage();
            try {
                travelBot.execute(new SendMessage(message.getChatId(), answer));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else {
            String answer = cityService.getInformation(textMessage);

            try {
                travelBot.execute(new SendMessage(message.getChatId(), answer));

                SendMediaGroup sendMediaGroup = new SendMediaGroup().setChatId(message.getChatId());

                List<InputMedia> collect = imageService.getImagesByCity(textMessage).stream()
                        .filter(imageResponse -> !imageResponse.getIsVideo()).map(imageResponse -> {
                        return new InputMediaPhoto()
                                .setMedia(imageResponse.getUrl());
                }).collect(Collectors.toList());

                if(collect.size() > 0){
                    sendMediaGroup.setMedia(collect);
                    travelBot.execute(sendMediaGroup);
                }


                imageService.getImagesByCity(textMessage).stream()
                        .filter(ImageResponse::getIsVideo).forEach(imageResponse -> {
                    try {
                        travelBot.execute(new SendMessage(message.getChatId(), imageResponse.getUrl()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                });

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
