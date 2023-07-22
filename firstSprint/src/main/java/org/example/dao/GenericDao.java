package org.example.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.example.entity.MessageContent;
import org.example.entity.MessageEnrichment;

public class GenericDao {

    private final AtomicInteger id = new AtomicInteger();

    private final ConcurrentHashMap<String, MessageEnrichment> msisdnToPersonalInfo = new ConcurrentHashMap<>(Map.of(
            "88005553535",  new MessageEnrichment("Ivan", "Ivanov"),
            "88005553536",  new MessageEnrichment("Petr", "Petrov"),
            "88005553537",  new MessageEnrichment("Vasya", "Smirnov"),
            "88005553538",  new MessageEnrichment("Vasya", "Vasiliev")
    ));

    private final ConcurrentHashMap<String, MessageEnrichment> innToPersonalInfo = new ConcurrentHashMap<>(Map.of(
            "7743013902",  new MessageEnrichment("Ivan", "Ivanov"),
            "7743013903",  new MessageEnrichment("Petr", "Petrov"),
            "7743013904",  new MessageEnrichment("Vasya", "Smirnov"),
            "7743013905",  new MessageEnrichment("Vasya", "Vasiliev")
    ));

    private static final ConcurrentHashMap<Integer, MessageContent> enrichMessage = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, MessageContent> notEnrichMessage = new ConcurrentHashMap<>();


    public MessageEnrichment getPersonalInfoByMsisdn(String msisdn) {
        return msisdnToPersonalInfo.get(msisdn);
    }

    public MessageEnrichment getPersonalInfoByInn(String msisdn) {
        return innToPersonalInfo.get(msisdn);
    }

    public void saveEnrichMessage(MessageContent messageContent) {
        int messageId = id.addAndGet(1);
        messageContent.setId(messageId);
        enrichMessage.put(messageId, messageContent);
    }

    public void saveNotEnrichMessage(MessageContent messageContent) {
        int messageId = id.addAndGet(1);
        messageContent.setId(messageId);
        notEnrichMessage.put(messageId, messageContent);
    }

    public MessageContent getEnrichMessage(int id) {
        return enrichMessage.get(id);
    }

    public MessageContent getNotEnrichMessage(int id) {
        return notEnrichMessage.get(id);
    }

    public void clearEnrichMessage() {
        enrichMessage.clear();
    }

    public void clearNotEnrichMessage() {
        notEnrichMessage.clear();
    }

}
