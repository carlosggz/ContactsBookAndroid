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

    @POST("/api/Contacts/search")
    Single<SearchContactsResponse> search(@Body SearchContactsRequest request);

    @GET("/api/Contacts/{id}")
    Single<ContactDetails> get(@Path("id") String id);

    @POST("/api/Contacts")
    Single<ApiResult> add(@Body ContactDetails contact);

    @PUT("/api/Contacts")
    Single<ApiResult> update(@Body ContactDetails contact);

    @DELETE("/api/Contacts/{id}")
    Single<ApiResult> delete(@Path("id") String id);
}
