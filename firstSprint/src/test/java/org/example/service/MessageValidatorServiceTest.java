package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.validator.ContentWithError;
import org.example.service.validator.MessageMsisdnValidatorService;
import org.example.service.validator.MessageValidator;
import org.example.service.validator.MessageValidatorService;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MessageValidatorServiceTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public static void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testMessageNotInJson() {
        MessageValidatorService messageValidatorService = new MessageValidatorService(objectMapper);
        String expected = """
                <?xml version="1.0" encoding="utf-8"?>
                <action>button_click</action>
                <page>book_card</page>
                <msisdn>88005553535</msisdn>
                """;
        ContentWithError contentWithError = messageValidatorService.validate(expected);
        assertFalse(contentWithError.getErrors().isEmpty());
    }

    @Test
    public void testMessageBadJson() {
        MessageValidatorService messageValidatorService = new MessageValidatorService(objectMapper);
        String expected = """
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "88005553535",
                
                """;
        ContentWithError contentWithError = messageValidatorService.validate(expected);
        assertFalse(contentWithError.getErrors().isEmpty());
    }

    @Test
    public void testMessageMsisdnValidatorServiceWithoutMsisdn() {
        MessageValidator messageValidator = new MessageMsisdnValidatorService(new MessageValidatorService(objectMapper));
        String expected = """
                {
                    "action": "button_click",
                    "page": "book_card"
                }
                """;
        ContentWithError contentWithError = messageValidator.validate(expected);
        assertFalse(contentWithError.getErrors().isEmpty());
    }

    @Test
    public void testMessageMsisdnValidatorServiceWithMsisdn() {
        MessageValidator messageValidator = new MessageMsisdnValidatorService(new MessageValidatorService(objectMapper));
        String expected = """
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "88005553535"
                }
                """;
        ContentWithError contentWithError = messageValidator.validate(expected);
        assertTrue(contentWithError.getErrors().isEmpty());
    }
}
