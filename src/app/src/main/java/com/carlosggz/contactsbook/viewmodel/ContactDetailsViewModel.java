package com.carlosggz.contactsbook.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carlosggz.contactsbook.model.ContactDetails;

public class ContactDetailsViewModel extends ViewModel {

    private MutableLiveData<ContactDetails> contact = new MutableLiveData<ContactDetails>();

    public LiveData<ContactDetails> getContact() {
        return contact;
    }

    public void LoadContact(String contactId) {
        ContactDetails details = new ContactDetails(contactId, "fn " + contactId, "ln " + contactId);
        contact.setValue(details);
    }
}
