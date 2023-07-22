package org.example.service.enrichment;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Message;
import org.example.entity.MessageContent;
import org.example.service.validator.*;
import static org.example.service.enrichment.Enrichment.genericDao;

public class EnrichmentService {

    private final ObjectMapper objectMapper;

    public EnrichmentService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String enrich(Message message) {
        MessageValidator messageValidator = choiceMessageValidator(message);
        ContentWithError contentWithError = messageValidator.validate(message.getContent());
        MessageContent messageContent = contentWithError.getContent();
        if (!contentWithError.getErrors().isEmpty()) {
            return message.getContent();
        }

        if (messageContent.getEnrichment().enrich(messageContent) != null) {
            genericDao.saveEnrichMessage(messageContent);
        } else {
            genericDao.saveNotEnrichMessage(messageContent);
        }

        return messageContentToString(messageContent);
    }

    // решил сделать через декоратор, чтобы была возможность валидировать INN_WITH_MSISDN, если бы они добавляли разные данные
    private MessageValidator choiceMessageValidator(Message message) {
        MessageValidator messageValidatorService = new MessageValidatorService(objectMapper);
        return switch (message.getEnrichmentType()) {
            case INN -> new MessageInnValidatorService(messageValidatorService);
            case MSISDN -> new MessageMsisdnValidatorService(messageValidatorService);
            default -> messageValidatorService;
        };
    }

    private String messageContentToString(MessageContent messageContent) {
        String messageContentString = null;
        try {
            messageContentString = objectMapper.writeValueAsString(messageContent);
        } catch (JacksonException ex) {
            System.out.println("Bad JSON " + ex);
        }

        return messageContentString;
    }
}
