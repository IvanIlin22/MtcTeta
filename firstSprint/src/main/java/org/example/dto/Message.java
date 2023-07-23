package org.example.dto;

public class Message {
    private String content;
    private EnrichmentType enrichmentType;

    public Message(String content, EnrichmentType enrichmentType) {
        this.content = content;
        this.enrichmentType = enrichmentType;
    }

    public EnrichmentType getEnrichmentType() {
        return enrichmentType;
    }

    public String getContent() {
        return content;
    }

    public void setEnrichmentType(EnrichmentType enrichmentType) {
        this.enrichmentType = enrichmentType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public enum EnrichmentType {
        MSISDN,
        INN;
    }
}
