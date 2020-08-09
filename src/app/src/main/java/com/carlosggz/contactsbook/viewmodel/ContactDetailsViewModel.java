package com.carlosggz.contactsbook.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carlosggz.contactsbook.model.ContactDetails;
import com.carlosggz.contactsbook.model.PhoneNumber;
import com.carlosggz.contactsbook.model.PhoneType;

import java.util.ArrayList;
import java.util.List;

public class ContactDetailsViewModel extends ViewModel {

    private MutableLiveData<ContactDetails> contact = new MutableLiveData<ContactDetails>();

    public LiveData<ContactDetails> getContact() {
        return contact;
    }

    public void loadContact(String contactId) {
        ContactDetails details = new ContactDetails(
                contactId,
                "Peter",
                "Parker",
                List.of("peterparker@gmail.com", "pp@gmail.com"),
                List.of(new PhoneNumber(PhoneType.HOME, "123-456-7890"), new PhoneNumber(PhoneType.WORK, "111-222-3333"))
        );
        contact.setValue(details);
    }

    public void deleteContact() {
    }
}
