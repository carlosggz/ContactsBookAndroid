package com.carlosggz.contactsbook.di;

import com.carlosggz.contactsbook.model.api.ContactsApi;
import com.carlosggz.contactsbook.model.services.ContactsService;
import com.carlosggz.contactsbook.viewmodel.BaseViewModel;
import com.carlosggz.contactsbook.viewmodel.ContactsListViewModel;

import dagger.Component;

@Component(modules = {ContactsApiModule.class})
public interface ContactsApiComponent {

    void inject(ContactsService service);
    void inject(BaseViewModel viewModel);
}
