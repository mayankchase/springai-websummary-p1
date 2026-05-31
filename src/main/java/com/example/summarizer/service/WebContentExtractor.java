package com.example.summarizer.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service for extracting text content from web pages.
 */
@Service
public class WebContentExtractor {

    private static final Logger logger = LoggerFactory.getLogger(WebContentExtractor.class);
    
    @Value("${web.extractor.timeout:10000}")
    private int timeout;
    
    @Value("${web.extractor.max-content-length:5000}")
    private int maxContentLength;

    /**
     * Extract text content from a given URL.
     * 
     * @param url The URL to extract content from
     * @return Extracted text content
     * @throws IOException if URL connection fails
     */
    public String extractContent(String url) throws IOException {
        try {
            logger.info("Extracting content from URL: {}", url);
            
            // Connect to URL with timeout and user agent
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .timeout(timeout)
                    .get();
            
            logger.debug("Successfully connected to URL: {}", url);
            
            // Remove script and style elements
            doc.select("script, style, nav, footer, [role=navigation]").remove();
            
            // Get main content
            String text = extractMainContent(doc);
            
            // Clean up whitespace
            text = text.replaceAll("\\s+", " ").trim();
            
            // Limit content length
            if (text.length() > maxContentLength) {
                logger.debug("Content length {} exceeds max {}, truncating", text.length(), maxContentLength);
                text = text.substring(0, maxContentLength) + "...";
            }
            
            logger.info("Extracted {} characters from URL: {}", text.length(), url);
            return text;
            
        } catch (IOException e) {
            logger.error("Failed to extract content from URL: {}", url, e);
            throw e;
        }
    }

    /**
     * Extract main content from the document.
     * Prioritizes article, main, or content sections.
     * 
     * @param doc JSoup Document
     * @return Main content text
     */
    private String extractMainContent(Document doc) {
        // Try to find main content area
        Element main = doc.selectFirst("main, article, [role=main], .main-content, .article-content");
        
        if (main != null) {
            return main.text();
        }
        
        // Fallback to body
        return doc.body().text();
    }
}
