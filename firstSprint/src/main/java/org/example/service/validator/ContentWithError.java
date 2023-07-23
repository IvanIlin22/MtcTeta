package org.example.service.validator;

import java.util.ArrayList;
import java.util.List;
import org.example.entity.MessageContent;

public class ContentWithError {

    private MessageContent content;
    private List<Error> errors = new ArrayList<>();

    public ContentWithError() {

    }

    public ContentWithError(MessageContent content, List<Error> errors) {
        this.content = content;
        this.errors = errors;
    }

    public MessageContent getContent() {
        return content;
    }

    public void setContent(MessageContent content) {
        this.content = content;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
