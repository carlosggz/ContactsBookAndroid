package com.carlosggz.contactsbook.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carlosggz.contactsbook.model.ContactDetails;
import com.carlosggz.contactsbook.model.ContactInfo;
import com.carlosggz.contactsbook.model.EmailItem;
import com.carlosggz.contactsbook.model.PhoneNumber;
import com.carlosggz.contactsbook.model.PhoneType;
import com.carlosggz.contactsbook.model.api.ApiResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;

public class EditContactViewModel extends BaseViewModel {

    //Private members
    private Optional<String> _id = Optional.empty();
    private MutableLiveData<String> firstName = new MutableLiveData<String>("");
    private MutableLiveData<String> lastName = new MutableLiveData<String>("");
    private MutableLiveData<List<EmailItem>> emailAddresses = new MutableLiveData<List<EmailItem>>(new ArrayList<EmailItem>());
    private MutableLiveData<List<PhoneNumber>> phoneNumbers = new MutableLiveData<List<PhoneNumber>>(new ArrayList<PhoneNumber>());
    private MutableLiveData<Boolean> errorLoading = new MutableLiveData<Boolean>(false);
    private MutableLiveData<String> errorSaving = new MutableLiveData<String>("");
    private MutableLiveData<Boolean> savedSuccessfully = new MutableLiveData<Boolean>(false);

    //Getters
    public Optional<String> getId() {
        return _id;
    }

    public MutableLiveData<String> getFirstName() { return firstName; }

    public MutableLiveData<String> getLastName() {
        return lastName;
    }

    public LiveData<List<EmailItem>> getEmailAddresses() {
        return emailAddresses;
    }

    public LiveData<List<PhoneNumber>> getPhoneNumbers() {
        return phoneNumbers;
    }

    public LiveData<Boolean> getErrorLoading() { return errorLoading; }

    public LiveData<String> getErrorSaving() { return errorSaving; }

    public LiveData<Boolean> getSavedSuccessfully() { return savedSuccessfully; }

    //Methods to access contacts

    public void loadContact(String contactId) {

        this.contactsService
                .getContact(contactId)
                .doOnSuccess(this::fromContact)
                .doOnError(x -> errorLoading.setValue(true))
                .subscribe();
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

    //Save
    public void save() {

        errorSaving.setValue("");

        ContactDetails contact = toContact();

        (!_id.isPresent() ? this.contactsService.addContact(contact) : this.contactsService.updateContact(contact))
                .doOnSuccess(x -> savedSuccessfully.setValue(true))
                .doOnError(x -> errorSaving.setValue(x.getMessage()))
                .subscribe();
    }

    //private

    private void fromContact(ContactDetails contact) {
        _id = Optional.of(contact.getContactId());
        firstName.setValue(contact.getFirstName());
        lastName.setValue(contact.getLastName());
        emailAddresses.setValue(contact.getEmailAddresses().stream().map(EmailItem::new).collect(Collectors.toList()));
        phoneNumbers.setValue(contact.getPhoneNumbers());
    }

    @NotNull
    private ContactDetails toContact() {
        return new ContactDetails(
                _id.orElse(null),
                firstName.getValue(),
                lastName.getValue(),
                emailAddresses.getValue().stream().map(x -> x.getEmail()).collect(Collectors.toList()),
                phoneNumbers.getValue()
        );
    }
}
