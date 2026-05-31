package com.example.summarizer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object for summarization response.
 */
public class SummarizationResponse {

    @JsonProperty("url")
    private String url;

    @JsonProperty("summary")
    private String summary;

    /**
     * Default constructor.
     */
    public SummarizationResponse() {
    }

    /**
     * Constructor with parameters.
     * 
     * @param url The URL that was summarized
     * @param summary The summary text
     */
    public SummarizationResponse(String url, String summary) {
        this.url = url;
        this.summary = summary;
    }

    // Getters and Setters

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "SummarizationResponse{" +
                "url='" + url + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
