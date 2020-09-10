package com.carlosggz.contactsbook.model;

import java.util.ArrayList;
import java.util.List;

public class ContactDetails {

    private String id;
    private String firstName ;
    private String lastName ;
    private ArrayList<String> emailAddresses;
    private ArrayList<PhoneNumber> phoneNumbers;

    public ContactDetails(String id, String firstName, String lastName, List<String> emailAddresses, List<PhoneNumber> phoneNumbers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddresses = new ArrayList<>(emailAddresses);
        this.phoneNumbers = new ArrayList<>(phoneNumbers);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public final List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public final List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void addEmail(String email) { emailAddresses.add(email); }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumbers.add(phoneNumber);
    }

    public void deleteMail(int index) {

        if (index < 0 || index >= emailAddresses.size())
            return;

        emailAddresses.remove(index);
    }

    public void deletePhone(int index) {

        if (index < 0 || index >= phoneNumbers.size())
            return;

        phoneNumbers.remove(index);
    }
}
