package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Message;
import org.example.service.enrichment.EnrichmentService;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.concurrent.Phaser;
import static org.junit.Assert.assertEquals;

public class EnrichmentServiceTest {

    private static ObjectMapper objectMapper;
    private static EnrichmentService enrichmentService;

    @BeforeClass
    public static void init() {
        objectMapper = new ObjectMapper();
        enrichmentService = new EnrichmentService(objectMapper);
    }

    @Test
    public void testMessageNotInJson() {
        String expected = """
                <?xml version="1.0" encoding="utf-8"?>
                <action>button_click</action>
                <page>book_card</page>
                <msisdn>88005553535</msisdn>
                """;
        Message message = new Message("""
                <?xml version="1.0" encoding="utf-8"?>
                <action>button_click</action>
                <page>book_card</page>
                <msisdn>88005553535</msisdn>
                """, Message.EnrichmentType.MSISDN);
        String actual = enrichmentService.enrich(message);
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageBadJson() {
        String expected = """
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "88005553535",
                }
                """;
        Message message = new Message("""
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "88005553535",
                }
                """, Message.EnrichmentType.MSISDN);
        String actual = enrichmentService.enrich(message);
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageWithoutMsisdn() {
        String expected = """
                {
                    "action": "button_click",
                    "page": "book_card"
                }
                """;
        Message message = new Message("""
                {
                    "action": "button_click",
                    "page": "book_card"
                }
                """, Message.EnrichmentType.MSISDN);
        String actual = enrichmentService.enrich(message);
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageWithoutInn() {
        String expected = """
                {
                    "action": "button_click",
                    "page": "book_card"
                }
                """;
        Message message = new Message("""
                {
                    "action": "button_click",
                    "page": "book_card"
                }
                """, Message.EnrichmentType.INN);
        String actual = enrichmentService.enrich(message);
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageMsisdnNotFound() {
        String expected = """
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "99005553535"
                }
                """;
        Message message = new Message("""
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "99005553535"
                }
                """, Message.EnrichmentType.INN);
        String actual = enrichmentService.enrich(message);
        assertEquals(expected, actual);
    }

    @Test
    public void testEnrichByMsisdn() {
        Message message = new Message("""
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "88005553535"
                }
                """, Message.EnrichmentType.MSISDN);
        String actual = enrichmentService.enrich(message);
        String expected = "{\"action\":\"button_click\",\"page\":\"book_card\",\"msisdn\":\"88005553535\",\"messageEnrichment\":{\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\"}}";
        assertEquals(expected, actual);
    }

    @Test
    public void testEnrichByMsisdnOverwriteMessageEnrichment() {
        Message message = new Message("""
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "88005553535",
                    "messageEnrichment": {
                        "firstName": "George",
                        "lastName": "Bush"
                    }
                }
                """, Message.EnrichmentType.MSISDN);
        String actual = enrichmentService.enrich(message);
        String expected = "{\"action\":\"button_click\",\"page\":\"book_card\",\"msisdn\":\"88005553535\",\"messageEnrichment\":{\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\"}}";
        assertEquals(expected, actual);
    }

    @Test
    public void testEnrichByMsisdnTwoMessage() {
        Message message1 = new Message("""
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "88005553535",
                    "messageEnrichment": {
                        "firstName": "George",
                        "lastName": "Bush"
                    }
                }
                """, Message.EnrichmentType.MSISDN);
        Message message2 = new Message("""
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "88005553536",
                    "messageEnrichment": {
                        "firstName": "George",
                        "lastName": "Bush"
                    }
                }
                """, Message.EnrichmentType.MSISDN);
        String actual1 = enrichmentService.enrich(message1);
        String expected1 = "{\"action\":\"button_click\",\"page\":\"book_card\",\"msisdn\":\"88005553535\",\"messageEnrichment\":{\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\"}}";
        assertEquals(expected1, actual1);
        String actual2 = enrichmentService.enrich(message2);
        String expected2 = "{\"action\":\"button_click\",\"page\":\"book_card\",\"msisdn\":\"88005553536\",\"messageEnrichment\":{\"firstName\":\"Petr\",\"lastName\":\"Petrov\"}}";
        assertEquals(expected2, actual2);
    }

    @Test
    public void multithreadingEnrichByMsisdn() {
        Phaser phaser = new Phaser(1);
        Message message1 = new Message("""
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "88005553535",
                    "messageEnrichment": {
                        "firstName": "George",
                        "lastName": "Bush"
                    }
                }
                """, Message.EnrichmentType.MSISDN);
        Message message2 = new Message("""
                {
                    "action": "button_click",
                    "page": "book_card",
                    "msisdn": "88005553536",
                    "messageEnrichment": {
                        "firstName": "George",
                        "lastName": "Bush"
                    }
                }
                """, Message.EnrichmentType.MSISDN);
        String expected1 = "{\"action\":\"button_click\",\"page\":\"book_card\",\"msisdn\":\"88005553535\",\"messageEnrichment\":{\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\"}}";
        String expected2 = "{\"action\":\"button_click\",\"page\":\"book_card\",\"msisdn\":\"88005553536\",\"messageEnrichment\":{\"firstName\":\"Petr\",\"lastName\":\"Petrov\"}}";

        new MultithreadingEnrich(phaser, enrichmentService, message1, expected1);
        new MultithreadingEnrich(phaser, enrichmentService, message2, expected2);

        // ожидать заверешния всеми потоками исполнения нулевой фазы - подготовка к старту обогощения
        phaser.arriveAndAwaitAdvance();

        // ожидать заверешния всеми потоками исполнения первой фазы - окончания обогощения сообщений
        phaser.arriveAndAwaitAdvance();

        // снять основной поток исполнения с регистрации
        phaser.arriveAndDeregister();
    }
}

class MultithreadingEnrich implements Runnable {
    Phaser phaser;
    EnrichmentService enrichmentService;
    Message message;
    String expected;

    MultithreadingEnrich(Phaser phaser, EnrichmentService enrichmentService, Message message, String expected) {
        this.phaser = phaser;
        this.enrichmentService = enrichmentService;
        this.message = message;
        this.expected = expected;
        phaser.register();
        new Thread(this).start();
    }

    public void run() {
        phaser.arriveAndAwaitAdvance();

        String actual = enrichmentService.enrich(message);
        assertEquals(expected, actual);

        phaser.arriveAndDeregister();
    }
}
