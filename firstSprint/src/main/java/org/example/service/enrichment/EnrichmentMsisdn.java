package org.example.service.enrichment;

import org.example.entity.MessageContent;
import org.example.entity.MessageEnrichment;

public class EnrichmentMsisdn implements Enrichment {

    public EnrichmentMsisdn() {
    }

    @Override
    public MessageEnrichment enrich(MessageContent messageContent) {
        MessageEnrichment messageEnrichment = genericDao.getPersonalInfoByMsisdn(messageContent.getMsisdn());
        messageContent.setMessageEnrichment(messageEnrichment);

        return messageEnrichment;
    }
}
