package com.carlosggz.contactsbook.model.api;

import com.carlosggz.contactsbook.model.ContactDetails;
import com.carlosggz.contactsbook.model.ContactInfo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactsApi {

    @POST("/api/Contacts/Search")
    Single<List<ContactInfo>> search(SearchContactsRequest request);

    @GET("/api/Contacts/Get/{id}")
    Single<ContactDetails> get(@Path("id") String id);

    @POST("/api/Contacts/Add")
    Single<ApiResult> add(@Body ContactDetails contact);

    @PUT("/api/Contacts/Update")
    Single<ApiResult> update(@Body ContactDetails contact);

    @DELETE("/api/Contacts/Delete/{id}")
    Single<ApiResult> delete(@Path("id") String id);
}
