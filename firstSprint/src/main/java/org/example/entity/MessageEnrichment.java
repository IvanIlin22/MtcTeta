package org.example.entity;

public class MessageEnrichment {
    private String firstName;
    private String lastName;

    public MessageEnrichment(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public MessageEnrichment() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
