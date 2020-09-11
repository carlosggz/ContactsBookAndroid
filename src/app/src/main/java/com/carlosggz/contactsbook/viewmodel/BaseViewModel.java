package com.carlosggz.contactsbook.viewmodel;

import androidx.lifecycle.ViewModel;

import com.carlosggz.contactsbook.di.DaggerContactsApiComponent;
import com.carlosggz.contactsbook.model.services.ContactsService;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    @Inject
    public ContactsService contactsService;

    protected CompositeDisposable disposable = new CompositeDisposable();

    public BaseViewModel() {
        DaggerContactsApiComponent.create().inject(this);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
