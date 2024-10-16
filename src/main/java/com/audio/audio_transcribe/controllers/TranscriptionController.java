package com.audio.audio_transcribe.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
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

        FileSystem
    }
}
