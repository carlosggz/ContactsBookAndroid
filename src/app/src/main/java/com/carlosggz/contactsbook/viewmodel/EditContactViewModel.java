package com.carlosggz.contactsbook.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carlosggz.contactsbook.model.ContactDetails;
import com.carlosggz.contactsbook.model.PhoneNumber;
import com.carlosggz.contactsbook.model.PhoneType;

import java.util.ArrayList;
import java.util.List;

public class EditContactViewModel extends ViewModel {

    private MutableLiveData<ContactDetails> contact = new MutableLiveData<ContactDetails>();

    public EditContactViewModel() {
        contact.setValue(new ContactDetails("", "", "", new ArrayList<String>(), new ArrayList<PhoneNumber>()));
    }

    public LiveData<ContactDetails> getContact() {
        return contact;
    }

    public void loadContact(String contactId) {
        ContactDetails details = new ContactDetails(
                contactId,
                "Peter",
                "Parker",
                List.of(
                        "peterparker@gmail.com",
                        "pp@gmail.com",
                        "another@gmail.com"
                ),
                List.of(
                        new PhoneNumber(PhoneType.HOME, "123-456-7890"),
                        new PhoneNumber(PhoneType.WORK, "111-222-3333"),
                        new PhoneNumber(PhoneType.MOBILE, "111-222-4444")
                )
        );
        contact.setValue(details);
    }

    public void newContact() {
        ContactDetails details = new ContactDetails("", "","", new ArrayList<String>(), new ArrayList<PhoneNumber>());
        contact.setValue(details);
    }

    public void addEmail() {
        ContactDetails c = contact.getValue();

        if (c == null)
            return;

        c.addEmail("");
        contact.setValue(c);
    }

    public void addPhone() {
        ContactDetails c = contact.getValue();

        if (c == null)
            return;

        c.addPhoneNumber(new PhoneNumber(PhoneType.WORK, ""));
        contact.setValue(c);
    }

    public void deleteMail(int index) {
        ContactDetails c = contact.getValue();

        if (c == null)
            return;

        c.deleteMail(index);
        contact.setValue(c);
    }

    public void deletePhone(int index) {
        ContactDetails c = contact.getValue();

        if (c == null)
            return;

        c.deletePhone(index);
        contact.setValue(c);
    }

}
