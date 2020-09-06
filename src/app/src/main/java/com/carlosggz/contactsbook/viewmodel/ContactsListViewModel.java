package com.carlosggz.contactsbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.carlosggz.contactsbook.di.DaggerContactsApiComponent;
import com.carlosggz.contactsbook.model.ContactInfo;
import com.carlosggz.contactsbook.model.api.SearchContactsRequest;
import com.carlosggz.contactsbook.model.services.ContactsService;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class ContactsListViewModel extends BaseViewModel {

    private MutableLiveData<List<ContactInfo>> contacts = new MutableLiveData<List<ContactInfo>>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> contactsLoadingError = new MutableLiveData<Boolean>();

    public LiveData<List<ContactInfo>> getContacts() {
        return contacts;
    }

    public LiveData<Boolean> getIsLoading() { return isLoading; }

    public LiveData<Boolean> getContactsLoadingError() {
        return contactsLoadingError;
    }

    public void reloadContacts() {
        contactsLoadingError.setValue(false);
        isLoading.setValue(true);

        Disposable disposable = contactsService
                .searchContacts(new SearchContactsRequest(1,1, ""))
                .subscribeWith(new DisposableSingleObserver<List<ContactInfo>>() {

                    @Override
                    public void onSuccess(List<ContactInfo> contactInfos) {
                        contacts.setValue(contactInfos);
                        isLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        contactsLoadingError.setValue(true);
                        isLoading.setValue(false);
                    }
                });
    }
}
