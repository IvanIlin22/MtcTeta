package org.example.service.validator;

import java.util.Optional;
import org.example.entity.MessageContent;

public class MessageInnValidatorService implements MessageValidator {

    private final MessageValidator messageValidator;

    public MessageInnValidatorService(MessageValidator messageValidator) {
        this.messageValidator = messageValidator;
    }

    @Override
    public ContentWithError validate(String message) {
        ContentWithError contentWithError = messageValidator.validate(message);
        if (Optional.ofNullable(contentWithError.getContent()).map(MessageContent::getInn).isEmpty()) {
            contentWithError.getErrors().add(new Error("INN_ABSENT", "BAD_JSON_INN_ABSENT"));
        }

        return contentWithError;
    }
}
