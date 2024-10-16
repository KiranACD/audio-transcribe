package com.audio.audio_transcribe.config;

import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.api.OpenAiAudioApi.TranscriptResponseFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TranscriptionModelConfig {
    
    @Value("${spring.ai.openai.api-key}")
    String apiKey;

    @Bean
    public OpenAiAudioTranscriptionModel getTranscriptionModel() {
        OpenAiAudioApi openAiAudioApi = new OpenAiAudioApi(apiKey);
        return new OpenAiAudioTranscriptionModel(openAiAudioApi);
    }

    @Bean
    public OpenAiAudioTranscriptionOptions getTranscriptionOptions() {
        OpenAiAudioTranscriptionOptions options = OpenAiAudioTranscriptionOptions.builder()
            .withResponseFormat(TranscriptResponseFormat.TEXT)
            .withLanguage("en")
            .withTemperature(0f)
            .build();
        return options;
    }

}
