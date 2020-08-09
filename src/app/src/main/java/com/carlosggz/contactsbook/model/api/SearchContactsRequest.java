package com.carlosggz.contactsbook.model.api;

public class SearchContactsRequest {

    private int pageNumber;
    private int pageSize;
    private String text;

    public SearchContactsRequest(int pageNumber, int pageSize, String text) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.text = text;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
