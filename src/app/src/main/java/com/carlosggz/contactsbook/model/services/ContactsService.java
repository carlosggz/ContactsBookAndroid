package com.carlosggz.contactsbook.model.services;

import com.carlosggz.contactsbook.di.DaggerContactsApiComponent;
import com.carlosggz.contactsbook.model.ContactDetails;
import com.carlosggz.contactsbook.model.ContactInfo;
import com.carlosggz.contactsbook.model.api.ApiResult;
import com.carlosggz.contactsbook.model.api.ContactsApi;
import com.carlosggz.contactsbook.model.api.SearchContactsRequest;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class ContactsService {

    private static ContactsService instance;

    @Inject
    public ContactsApi api;

    private ContactsService() {
        DaggerContactsApiComponent.create().inject(this);
    }

    public static ContactsService getInstance() {
        if (instance == null) {
            instance = new ContactsService();
        }

        return instance;
    }

    public Single<List<ContactInfo>> searchContacts(SearchContactsRequest request) {
        return api.search(request);
    }

    public Single<ContactDetails> getContact(String id) {
        return api.get(id);
    }

    public Single<ApiResult> addContact(ContactDetails contact) {
        return api.add(contact);
    }

    public Single<ApiResult> updateContact(ContactDetails contact) {
        return api.update(contact);
    }

    public Single<ApiResult> deleteContact(String id) {
        return api.delete(id);
    }
}
