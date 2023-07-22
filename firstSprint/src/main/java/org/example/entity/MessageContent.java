package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.service.enrichment.Enrichment;
import org.example.service.enrichment.EnrichmentInn;
import org.example.service.enrichment.EnrichmentMsisdn;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageContent {
    @JsonIgnore
    private int id;
    private String action;
    private String page;
    private String msisdn;
    private String inn;
    @JsonIgnore
    private Enrichment enrichment;

    private MessageEnrichment messageEnrichment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MessageContent() {
        id++;
    }

    public MessageContent(String action, String page, String msisdn) {
        this.action = action;
        this.page = page;
        this.msisdn = msisdn;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
        this.enrichment = new EnrichmentMsisdn();
    }

    public MessageEnrichment getMessageEnrichment() {
        return messageEnrichment;
    }

    public void setMessageEnrichment(MessageEnrichment messageEnrichment) {
        this.messageEnrichment = messageEnrichment;
    }

    public Enrichment getEnrichment() {
        return enrichment;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
        this.enrichment = new EnrichmentInn();
    }
}
