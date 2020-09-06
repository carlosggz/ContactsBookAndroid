package com.carlosggz.contactsbook.viewmodel;

import androidx.lifecycle.ViewModel;

import com.carlosggz.contactsbook.di.DaggerContactsApiComponent;
import com.carlosggz.contactsbook.model.services.ContactsService;

import javax.inject.Inject;

public abstract class BaseViewModel extends ViewModel {

    @Inject
    public ContactsService contactsService;

    public BaseViewModel() {
        DaggerContactsApiComponent.create().inject(this);
    }
}
