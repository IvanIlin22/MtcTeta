package org.example.service.enrichment;

import org.example.entity.MessageContent;
import org.example.entity.MessageEnrichment;

public class EnrichmentInn implements Enrichment {

    public EnrichmentInn() {
    }

    @Override
    public MessageEnrichment enrich(MessageContent messageContent) {
        MessageEnrichment messageEnrichment = genericDao.getPersonalInfoByInn(messageContent.getInn());
        messageContent.setMessageEnrichment(messageEnrichment);

        return messageEnrichment;
    }
}
