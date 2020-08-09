package com.carlosggz.contactsbook.model.api;

import java.util.List;

public class ApiResult {
    private String contactId;
    private boolean success;
    private List<String> errors;

    public ApiResult(String contactId, boolean success, List<String> errors) {
        this.contactId = contactId;
        this.success = success;
        this.errors = errors;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
