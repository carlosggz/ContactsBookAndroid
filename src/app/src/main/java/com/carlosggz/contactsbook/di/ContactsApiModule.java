package com.carlosggz.contactsbook.di;

import com.carlosggz.contactsbook.model.Constants;
import com.carlosggz.contactsbook.model.api.ContactsApi;
import com.carlosggz.contactsbook.model.services.ContactsService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ContactsApiModule {

    @Provides
    public ContactsApi provideContactsApi() {
        return new Retrofit.Builder()
                .baseUrl(Constants.CONTACTS_API_URL) //Api url
                .addConverterFactory(GsonConverterFactory.create()) //Mapping from json to classes
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Async result on calls
                .build()
                .create(ContactsApi.class);
    }

    @Provides
    public ContactsService providesContactsService() {
        return ContactsService.getInstance();
    }
}
