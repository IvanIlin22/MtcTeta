package org.example.service.validator;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.MessageContent;

public class MessageValidatorService implements MessageValidator {

    private final ObjectMapper objectMapper;

    public MessageValidatorService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ContentWithError validate(String message) {
        ContentWithError contentWithError = new ContentWithError();
        try {
            MessageContent content = objectMapper.readValue(message, MessageContent.class);
            contentWithError.setContent(content);
        } catch (JacksonException ex) {
            contentWithError.getErrors().add(new Error("BAD_JSON", "BAD_JSON_DESCRIPTION"));
        }

        return contentWithError;
    }
}
