package com.example.summarizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ai.chat.client.ChatClient;

/**
 * Main Spring Boot application class for Web Content Summarizer.
 * 
 * This application uses Spring AI to summarize external website content.
 */
@SpringBootApplication
public class WebSummarizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSummarizerApplication.class, args);
    }

    /**
     * Create ChatClient bean for AI interactions.
     * 
     * @param builder ChatClient builder
     * @return Configured ChatClient instance
     */
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
