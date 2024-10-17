package com.audio.audio_transcribe.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/transcribe")
public class TranscriptionController {

    OpenAiAudioTranscriptionModel transcriptionModel;
    OpenAiAudioTranscriptionOptions options;
    
    public TranscriptionController(OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel, OpenAiAudioTranscriptionOptions options) {
        this.transcriptionModel = openAiAudioTranscriptionModel;
        this.options = options;
    }

    @PostMapping
    public ResponseEntity<String> transcribeAudio(@RequestParam("file") MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("audio", ".wav");
        file.transferTo(tempFile);

        FileSystemResource audioFile = new FileSystemResource(tempFile);
        AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(audioFile, this.options);

        AudioTranscriptionResponse response = this.transcriptionModel.call(prompt);
        
        tempFile.delete();
        return new ResponseEntity<>(response.getResult().getOutput(), HttpStatus.OK);
    }
}
