package com.task.config;

import com.task.model.TravelBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "spring.telegrambot")
public class TelegramBotConfiguration {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    private DefaultBotOptions.ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;

    @Bean
    public TravelBot travelBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        TelegramBotsApi botsApi = new TelegramBotsApi();

        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(proxyType);

        TravelBot travelBot = new TravelBot(options);
        travelBot.setBotUserName(botUserName);
        travelBot.setBotToken(botToken);
        travelBot.setWebHookPath(webHookPath);

        try {
            botsApi.registerBot(travelBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return travelBot;
    }
}
