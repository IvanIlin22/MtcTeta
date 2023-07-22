package org.example.service.validator;

import java.util.Optional;
import org.example.entity.MessageContent;

public class MessageMsisdnValidatorService implements MessageValidator {

    private final MessageValidator messageValidator;

    public MessageMsisdnValidatorService(MessageValidator messageValidator) {
        this.messageValidator = messageValidator;
    }

    @Override
    public ContentWithError validate(String message) {
        ContentWithError contentWithError = messageValidator.validate(message);
        if (Optional.ofNullable(contentWithError.getContent()).map(MessageContent::getMsisdn).isEmpty()) {
            contentWithError.getErrors().add(new Error("MSISDN_ABSENT", "BAD_JSON_MSISDN_ABSENT"));
        }

        return contentWithError;
    }
}
