package com.carlosggz.contactsbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.carlosggz.contactsbook.model.ContactInfo;

import java.util.ArrayList;
import java.util.List;

public class ContactsListViewModel extends AndroidViewModel {

    private MutableLiveData<List<ContactInfo>> contacts = new MutableLiveData<List<ContactInfo>>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> contactsLoadingError = new MutableLiveData<Boolean>();

    public ContactsListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<ContactInfo>> getContacts() {
        return contacts;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> getContactsLoadingError() {
        return contactsLoadingError;
    }

    public void reloadContacts() {
        contactsLoadingError.setValue(false);
        isLoading.setValue(true);

        ArrayList<ContactInfo> fake = new ArrayList<>();

        for(int i = 1; i <= 20; i++) {
            String number = String.valueOf(i);
            fake.add(new ContactInfo(number, "fn " + number, "ln " + number, 0, 0));
        }

        isLoading.setValue(false);
        contacts.setValue(fake);
    }
}
