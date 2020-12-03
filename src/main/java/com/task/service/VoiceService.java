package com.task.service;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import com.task.model.TravelBot;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Voice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VoiceService {

    private final TravelBot travelBot;

    public String voiceToMessage(Voice voice) throws IOException {
        InputStream inputStream = getTelegramAudioStream(voice);

        byte[] bytes = new byte[60000];
        int length = inputStream.read(bytes);

        try(SpeechClient speechClient = SpeechClient.create()){
            ByteString audioBytes = ByteString.copyFrom(bytes, 0, length);
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.OGG_OPUS)
                            .setEnableAutomaticPunctuation(true)
                            .setEnableAutomaticPunctuation(true)
                            .setEnableWordTimeOffsets(true)
                            .setSampleRateHertz(48000)
                            .setLanguageCode("ru-RU")
                            .setModel("default")
                            .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                    speechClient.longRunningRecognizeAsync(config, audio);

            StringBuilder recognizedMessage = new StringBuilder();

            while(!response.isDone()){
                Thread.sleep(1000);
            }
            List<SpeechRecognitionResult> resultsList = response.get().getResultsList();


            for (SpeechRecognitionResult result : resultsList) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                recognizedMessage.append(alternative.getTranscript());
            }

            return recognizedMessage.toString();
        } catch (Exception e){
            return "";
        }
    }

    private InputStream getTelegramAudioStream(Voice voice) throws IOException {
        URL url = new URL("https://api.telegram.org/bot" + travelBot.getBotToken() + "/getFile?file_id=" + voice.getFileId());
        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
        String getFileResponse = bf.readLine();

        JSONObject jresponce = new JSONObject(getFileResponse).getJSONObject("result");
        String file_path = jresponce.getString("file_path");

        URL url1 = new URL("https://api.telegram.org/file/bot"
                + travelBot.getBotToken() + "/" + file_path);

        return url1.openStream();
    }
}
