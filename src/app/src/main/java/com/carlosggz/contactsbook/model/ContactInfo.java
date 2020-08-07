package com.carlosggz.contactsbook.model;

public class ContactInfo {

    public String contactId ;
    public String firstName ;
    public String lastName ;
    public int emailsCount ;
    public int phonesCount ;

    public ContactInfo(String contactId, String firstName, String lastName, int emailsCount, int phonesCount) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailsCount = emailsCount;
        this.phonesCount = phonesCount;
    }
}
