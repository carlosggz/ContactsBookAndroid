package com.carlosggz.contactsbook.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carlosggz.contactsbook.model.ContactDetails;
import com.carlosggz.contactsbook.model.PhoneNumber;
import com.carlosggz.contactsbook.model.PhoneType;

import java.util.ArrayList;
import java.util.List;

public class ContactDetailsViewModel extends BaseViewModel {

    private MutableLiveData<ContactDetails> contact = new MutableLiveData<ContactDetails>();
    private MutableLiveData<Boolean> errorLoading = new MutableLiveData<Boolean>(false);
    private MutableLiveData<String> errorDeleting = new MutableLiveData<String>("");
    private MutableLiveData<Boolean> contactDeleted = new MutableLiveData<Boolean>(false);

    public LiveData<ContactDetails> getContact() { return contact; }
    public LiveData<Boolean> getErrorLoading() { return errorLoading; }
    public LiveData<String> getErrorDeleting() { return errorDeleting; }
    public LiveData<Boolean> getContactDeleted() { return contactDeleted; }

    public void loadContact(String contactId) {

        this.contactsService
                .getContact(contactId)
                .doOnSuccess(c -> contact.setValue(c))
                .doOnError(x -> errorLoading.setValue(true))
                .subscribe();
    }

    public void deleteContact() {
        this.contactsService
                .deleteContact(contact.getValue().getContactId())
                .doOnSuccess(c -> contactDeleted.setValue(true))
                .doOnError(x -> errorDeleting.setValue(x.getMessage()))
                .subscribe();
    }
}
