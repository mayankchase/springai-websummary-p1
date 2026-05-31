package com.example.summarizer.controller;

import com.example.summarizer.dto.SummarizationResponse;
import com.example.summarizer.service.SummarizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for web content summarization endpoints.
 */
@RestController
@RequestMapping("/api/summarize")
public class SummarizationController {

    private static final Logger logger = LoggerFactory.getLogger(SummarizationController.class);

    @Autowired
    private SummarizationService summarizationService;

    /**
     * Summarize content from a given URL.
     * 
     * @param url The URL to summarize
     * @return SummarizationResponse containing the URL and summary
     */
    @GetMapping
    public SummarizationResponse summarize(@RequestParam String url) {
        logger.info("Received request to summarize URL: {}", url);
        
        try {
            String summary = summarizationService.summarizeWebContent(url);
            logger.info("Successfully summarized URL: {}", url);
            return new SummarizationResponse(url, summary);
        } catch (Exception e) {
            logger.error("Error summarizing URL: {}", url, e);
            return new SummarizationResponse(url, "Error: " + e.getMessage());
        }
    }

    /**
     * Health check endpoint.
     * 
     * @return Status message
     */
    @GetMapping("/health")
    public String health() {
        return "Spring AI Web Summarizer is running!";
    }
}
