package com.carlosggz.contactsbook.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.carlosggz.contactsbook.model.ContactInfo;
import com.carlosggz.contactsbook.model.api.SearchContactsRequest;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContactsListViewModel extends BaseViewModel {

    private MutableLiveData<List<ContactInfo>> contacts = new MutableLiveData<List<ContactInfo>>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> contactsLoadingError = new MutableLiveData<Boolean>();

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

        disposable.add(
                contactsService
                        .searchContacts(new SearchContactsRequest(1, 1000, ""))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            contacts.setValue(response.results);
                            isLoading.setValue(false);
                        }, e -> {
                            contactsLoadingError.setValue(true);
                            isLoading.setValue(false);
                        }));
    }
}
