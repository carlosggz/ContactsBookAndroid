package com.carlosggz.contactsbook.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carlosggz.contactsbook.model.EmailItem;
import com.carlosggz.contactsbook.model.PhoneNumber;
import com.carlosggz.contactsbook.model.PhoneType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditContactViewModel extends ViewModel {

    //Private members
    private Optional<String> _id = Optional.empty();
    private MutableLiveData<String> firstName = new MutableLiveData<String>("");
    private MutableLiveData<String> lastName = new MutableLiveData<String>("");
    private MutableLiveData<List<EmailItem>> emailAddresses = new MutableLiveData<List<EmailItem>>(new ArrayList<EmailItem>());
    private MutableLiveData<List<PhoneNumber>> phoneNumbers = new MutableLiveData<List<PhoneNumber>>(new ArrayList<PhoneNumber>());

    //Getters
    public Optional<String> getId() {
        return _id;
    }
    public MutableLiveData<String> getFirstName() {
        return firstName;
    }
    public MutableLiveData<String> getLastName() {
        return lastName;
    }
    public MutableLiveData<List<EmailItem>> getEmailAddresses() {
        return emailAddresses;
    }
    public MutableLiveData<List<PhoneNumber>> getPhoneNumbers() {
        return phoneNumbers;
    }

    //Methods to access contacts

    public void loadContact(String contactId) {
        _id = Optional.of(contactId);
        firstName.setValue("Peter");
        lastName.setValue("Parker");
        emailAddresses.setValue(
                List.of(
                        new EmailItem("peterparker@gmail.com"),
                        new EmailItem("pp@gmail.com"),
                        new EmailItem("other@gmail.com")
                ));
        phoneNumbers.setValue(
                List.of(
                        new PhoneNumber(PhoneType.HOME, "123-456-7890"),
                        new PhoneNumber(PhoneType.WORK, "111-222-3333"),
                        new PhoneNumber(PhoneType.MOBILE, "111-222-4444"))
        );
    }

    public void newContact() {
        _id = Optional.empty();
        firstName.setValue("");
        lastName.setValue("");
        emailAddresses.setValue(new ArrayList<EmailItem>());
        phoneNumbers.setValue(new ArrayList<PhoneNumber>());
    }

    //Email methods
    public void addEmail() {

        List<EmailItem> emails = new ArrayList<EmailItem>(emailAddresses.getValue());
        emails.add(new EmailItem(""));
        emailAddresses.setValue(emails);
    }

    public void deleteEmail(int index) {

        List<EmailItem> emails = new ArrayList<EmailItem>(emailAddresses.getValue());
        emails.remove(index);
        emailAddresses.setValue(emails);
    }

    //Phone methods
    public void addPhone() {
        List<PhoneNumber> phones = new ArrayList<PhoneNumber>(phoneNumbers.getValue());
        phones.add(new PhoneNumber(PhoneType.HOME, ""));
        phoneNumbers.setValue(phones);
    }

    public void deletePhone(int index) {
        List<PhoneNumber> phones = new ArrayList<PhoneNumber>(phoneNumbers.getValue());
        phones.remove(index);
        phoneNumbers.setValue(phones);
    }
}
