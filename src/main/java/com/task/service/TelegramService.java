package com.task.service;

import com.task.model.Message;
import com.task.model.TravelBot;
import com.task.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Voice;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TelegramService {
    private static final int CONNECTION_TIMEOUT = 10000;
    private final CityService cityService;
    private final TravelBot travelBot;

    public void onWebhookUpdateReceived(Update update) throws TelegramApiException, IOException {
        Voice voice = update.getMessage().getVoice();
        if(voice != null){
            uploadFile(voice, update);
        }else if (update.getMessage().getText().equals("/start")) {
            travelBot.execute(new SendMessage(update.getMessage().getChatId(), update.getMessage().getFrom().getFirstName() + ", " + Message.HELLO_MESSAGE.getHelloMessage()));
        } else if (Objects.nonNull(update.getMessage()) && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String name = update.getMessage().getText();
            String cityDescription = cityService.getInformation(name);

            try {
                travelBot.execute(new SendMessage(chatId, cityDescription));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadFile(Voice voice, Update update) throws IOException, TelegramApiException {
        URL url = new URL("https://api.telegram.org/bot" + travelBot.getBotToken() + "/getFile?file_id=" + voice.getFileId());
        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
        String getFileResponse = bf.readLine();

        JSONObject jresponce = new JSONObject(getFileResponse).getJSONObject("result");
        String file_path = jresponce.getString("file_path");

        InputStream inputStream = new URL("https://api.telegram.org/file/bot"
                + travelBot.getBotToken() + "/" + file_path).openStream();


        byte[] bytes = new byte[60000];
        int length = inputStream.read(bytes);

        String witApiUrl = "https://api.wit.ai/speech?v=20200513";

        URL obj = new URL(witApiUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer CJTNETJV5DDADEJJP3GTJJ6O2XMLW2ZU");
        con.setRequestProperty("Content-Type", "audio/ogg");
        con.setRequestProperty("Content-Length", String.valueOf(length));
        con.setConnectTimeout(CONNECTION_TIMEOUT);
        con.setReadTimeout(CONNECTION_TIMEOUT);
        con.setDoOutput(true);

        OutputStream outputStream = con.getOutputStream();

        outputStream.write(bytes, 0, length);
        outputStream.close();
        inputStream.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());

        travelBot.execute(new SendMessage(update.getMessage().getChatId(), "ResponceCode: " + con.getResponseCode()
                + "\nResponseMessage: " + con.getResponseMessage()
                + "\n" + file_path
                + "\nResponseBody: \n"+ response.toString()));

    }
}
