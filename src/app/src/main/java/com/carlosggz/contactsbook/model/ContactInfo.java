package com.carlosggz.contactsbook.model;

public class ContactInfo {

    private String contactId ;
    private String firstName ;
    private String lastName ;
    private int emailsCount ;
    private int phonesCount ;

    public ContactInfo(String contactId, String firstName, String lastName, int emailsCount, int phonesCount) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailsCount = emailsCount;
        this.phonesCount = phonesCount;
    }

    public ContactInfo(ContactDetails details) {
        this.contactId = details.getId();
        this.firstName = details.getFirstName();
        this.lastName = details.getLastName();
        this.emailsCount = details.getEmailAddresses().size();
        this.phonesCount = details.getPhoneNumbers().size();
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
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

    public int getEmailsCount() {
        return emailsCount;
    }

    public void setEmailsCount(int emailsCount) {
        this.emailsCount = emailsCount;
    }

    public int getPhonesCount() {
        return phonesCount;
    }

    public void setPhonesCount(int phonesCount) {
        this.phonesCount = phonesCount;
    }
}
