package org.example.service.enrichment;

import org.example.dao.GenericDao;
import org.example.entity.MessageContent;
import org.example.entity.MessageEnrichment;

public interface Enrichment {
    GenericDao genericDao = new GenericDao();
    MessageEnrichment enrich(MessageContent messageContent);
}
