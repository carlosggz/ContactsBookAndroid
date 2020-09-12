package com.carlosggz.contactsbook.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.carlosggz.contactsbook.model.ContactDetails;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContactDetailsViewModel extends BaseViewModel {

    private MutableLiveData<ContactDetails> contact = new MutableLiveData<ContactDetails>();
    private MutableLiveData<Boolean> errorLoading = new MutableLiveData<Boolean>(false);
    private MutableLiveData<String> errorDeleting = new MutableLiveData<String>("");
    private MutableLiveData<Boolean> contactDeleted = new MutableLiveData<Boolean>(false);

    public LiveData<ContactDetails> getContact() {
        return contact;
    }

    public LiveData<Boolean> getErrorLoading() {
        return errorLoading;
    }

    public LiveData<String> getErrorDeleting() {
        return errorDeleting;
    }

    public LiveData<Boolean> getContactDeleted() {
        return contactDeleted;
    }

    public void loadContact(String contactId) {

        disposable.add(this.contactsService
                .getContact(contactId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(c -> contact.setValue(c), x -> errorLoading.setValue(true)));
    }

    public void deleteContact() {
        disposable.add(this.contactsService
                .deleteContact(contact.getValue().getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(c -> contactDeleted.setValue(true))
                .doOnError(x -> errorDeleting.setValue(x.getMessage()))
                .subscribe());
    }
}
