package com.carlosggz.contactsbook.model;

public class PhoneNumber {
    private PhoneType phoneType;
    private String phoneNumber;

    public PhoneNumber(PhoneType phoneType, String phoneNumber) {
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
