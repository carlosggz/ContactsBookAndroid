package com.carlosggz.contactsbook.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PhoneNumber {

    private int phoneType;
    private String phoneNumber;

    public PhoneNumber(int phoneType, String phoneNumber) {
        setPhoneType(phoneType);
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneType() { return phoneType; }

    public void setPhoneType(int phoneType) {
        this.phoneType = getFromOrdinal(phoneType).map(Enum::ordinal).orElse(PhoneType.HOME.ordinal());
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneTypeName() {
        return getFromOrdinal(phoneType).map(Enum::name).orElse("");
    }

    private Optional<PhoneType> getFromOrdinal(int ordinal) {
        return Arrays.stream(PhoneType.values()).filter(x -> x.ordinal() == ordinal).findFirst();
    }
}
