package com.carlosggz.contactsbook.model.api;

import com.carlosggz.contactsbook.model.ContactInfo;

import java.util.List;

public class SearchContactsResponse {

    public int total;
    public List<ContactInfo> results;

    public SearchContactsResponse(int total, List<ContactInfo> results) {
        this.total = total;
        this.results = results;
    }
}
