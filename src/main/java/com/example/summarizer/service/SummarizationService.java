package com.example.summarizer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service for AI-powered content summarization.
 */
@Service
public class SummarizationService {

    private static final Logger logger = LoggerFactory.getLogger(SummarizationService.class);
    private static final String SUMMARY_PROMPT = """
            Please provide a concise summary of the following web content in 3-5 bullet points.
            Focus on the key ideas and main information:
            
            Content:
            {content}
            
            Summary (as bullet points):
            """;

    @Autowired
    private ChatClient chatClient;

    @Autowired
    private WebContentExtractor contentExtractor;

    /**
     * Summarize content from a given URL.
     * 
     * @param url The URL to summarize
     * @return Summary of the web content
     */
    public String summarizeWebContent(String url) {
        try {
            logger.info("Starting summarization for URL: {}", url);
            
            // Extract content from URL
            String content = contentExtractor.extractContent(url);
            
            if (content.trim().isEmpty()) {
                logger.warn("No content extracted from URL: {}", url);
                return "No readable content found at the provided URL.";
            }
            
            logger.debug("Extracted content length: {} characters", content.length());
            
            // Create prompt template
            PromptTemplate promptTemplate = new PromptTemplate(SUMMARY_PROMPT);
            Prompt prompt = promptTemplate.create(Map.of("content", content));
            
            logger.info("Sending summarization request to AI model");
            
            // Call AI model
            String summary = chatClient.call(prompt).getResult().getOutput().getContent();
            
            logger.info("Successfully generated summary for URL: {}", url);
            return summary;
            
        } catch (Exception e) {
            logger.error("Error summarizing content from URL: {}", url, e);
            return "Error summarizing content: " + e.getMessage();
        }
    }
}
