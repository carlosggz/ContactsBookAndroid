package com.carlosggz.contactsbook.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.carlosggz.contactsbook.helpers.Utils;
import com.carlosggz.contactsbook.model.ContactDetails;
import com.carlosggz.contactsbook.model.EmailItem;
import com.carlosggz.contactsbook.model.PhoneNumber;
import com.carlosggz.contactsbook.model.PhoneType;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EditContactViewModel extends BaseViewModel {

    //Private members
    private MutableLiveData<String> id = new MutableLiveData<String>("");
    private MutableLiveData<String> firstName = new MutableLiveData<String>("");
    private MutableLiveData<String> lastName = new MutableLiveData<String>("");
    private MutableLiveData<List<EmailItem>> emailAddresses = new MutableLiveData<List<EmailItem>>(List.of());
    private MutableLiveData<List<PhoneNumber>> phoneNumbers = new MutableLiveData<List<PhoneNumber>>(List.of());
    private MutableLiveData<Boolean> isSaving = new MutableLiveData<Boolean>(false);
    private MutableLiveData<Boolean> errorLoading = new MutableLiveData<Boolean>(false);
    private MutableLiveData<String> errorSaving = new MutableLiveData<String>("");
    private MutableLiveData<Boolean> savedSuccessfully = new MutableLiveData<Boolean>(false);
    private MutableLiveData<List<String>> validationErrors = new MutableLiveData<List<String>>(List.of());

    //Getters
    public LiveData<String> getId() {
        return id;
    }

    public MutableLiveData<String> getFirstName() {
        return firstName;
    }

    public MutableLiveData<String> getLastName() {
        return lastName;
    }

    public LiveData<List<EmailItem>> getEmailAddresses() {
        return emailAddresses;
    }

    public LiveData<List<PhoneNumber>> getPhoneNumbers() {
        return phoneNumbers;
    }

    public LiveData<Boolean> getIsSaving() {
        return isSaving;
    }

    public LiveData<Boolean> getErrorLoading() {
        return errorLoading;
    }

    public LiveData<String> getErrorSaving() {
        return errorSaving;
    }

    public LiveData<Boolean> getSavedSuccessfully() {
        return savedSuccessfully;
    }

    public LiveData<List<String>> getValidationErrors() {
        return validationErrors;
    }

    //Methods to access contacts

    public void loadContact(String contactId) {

        disposable.add(
                this.contactsService
                        .getContact(contactId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::fromContact, x -> errorLoading.setValue(true)));
    }

    public void newContact() {
        firstName.setValue("");
        lastName.setValue("");
        emailAddresses.setValue(new ArrayList<EmailItem>());
        phoneNumbers.setValue(new ArrayList<PhoneNumber>());
        id.setValue("");
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
        phones.add(new PhoneNumber(PhoneType.HOME.ordinal(), ""));
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
        validationErrors.setValue(List.of());
        ContactDetails contact = toContact();
        List<String> validations = validate(contact);

        if (validations.size() > 0) {
            validationErrors.setValue(validations);
            return;
        }

        isSaving.setValue(true);

        disposable.add(
                (Utils.isNullOrWhiteSpace(id.getValue()) ? this.contactsService.addContact(contact) : this.contactsService.updateContact(contact))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                s -> {
                                    isSaving.setValue(false);
                                    savedSuccessfully.setValue(true);
                                },
                                x -> {
                                    isSaving.setValue(false);
                                    errorSaving.setValue(Utils.getErrorFromResponse(x));
                                }
                        ));
    }

    //private

    private void fromContact(ContactDetails contact) {
        firstName.setValue(contact.getFirstName());
        lastName.setValue(contact.getLastName());
        emailAddresses.setValue(contact.getEmailAddresses().stream().map(EmailItem::new).collect(Collectors.toList()));
        phoneNumbers.setValue(contact.getPhoneNumbers());
        id.setValue(contact.getId());
    }

    @NotNull
    private ContactDetails toContact() {
        return new ContactDetails(
                id.getValue(),
                firstName.getValue(),
                lastName.getValue(),
                emailAddresses.getValue().stream().map(EmailItem::getEmail).collect(Collectors.toList()),
                phoneNumbers.getValue()
        );
    }

    private List<String> validate(ContactDetails contact) {

        ArrayList<String> errors = new ArrayList<String>();

        if (Utils.isNullOrWhiteSpace(contact.getFirstName())) {
            errors.add("Type a valid first name");
        }

        if (Utils.isNullOrWhiteSpace(contact.getLastName())) {
            errors.add("Type a valid last name");
        }

        List<String> emails = contact.getEmailAddresses();

        if (emails.stream().anyMatch(x -> !Utils.isValidEmail(x))) {
            errors.add("There are invalid emails");
        } else if (emails.stream().distinct().count() != emails.size()) {
            errors.add("There are repeated emails");
        }

        List<String> phoneNumbers = contact.getPhoneNumbers().stream().map(PhoneNumber::getPhoneNumber).collect(Collectors.toList());

        if (phoneNumbers.stream().anyMatch(x -> !Utils.isValidPhone(x))) {
            errors.add("There are invalid phone numbers");
        } else if (phoneNumbers.stream().distinct().count() != phoneNumbers.size()) {
            errors.add("There are repeated phone numbers");
        }

        return errors;
    }
}
