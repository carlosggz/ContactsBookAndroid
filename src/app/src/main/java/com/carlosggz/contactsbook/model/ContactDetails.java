package com.carlosggz.contactsbook.model;

public class ContactDetails {

    public String contactId ;
    public String firstName ;
    public String lastName ;

    public ContactDetails(String contactId, String firstName, String lastName) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
