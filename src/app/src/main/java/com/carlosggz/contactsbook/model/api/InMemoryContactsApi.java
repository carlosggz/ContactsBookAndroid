package com.carlosggz.contactsbook.model.api;

import com.carlosggz.contactsbook.model.ContactDetails;
import com.carlosggz.contactsbook.model.ContactInfo;
import com.carlosggz.contactsbook.model.PhoneNumber;
import com.carlosggz.contactsbook.model.PhoneType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Single;

public class InMemoryContactsApi implements ContactsApi {

    private static InMemoryContactsApi instance;

    private ArrayList<ContactDetails> contacts;

    private InMemoryContactsApi() {

        contacts = new ArrayList<ContactDetails>();
        contacts.add(new ContactDetails("111", "Peter", "Parker", List.of("spiderman@example.com"), List.of(new PhoneNumber(PhoneType.HOME, "123-456-7890"))));
        contacts.add(new ContactDetails("222", "Clark", "Kent", List.of("superman@example.com"), List.of(new PhoneNumber(PhoneType.WORK, "123-456-7890"))));
        contacts.add(new ContactDetails("333", "Barry", "Allen", List.of("flash@example.com"), List.of(new PhoneNumber(PhoneType.WORK, "123-456-7890"))));
        contacts.add(new ContactDetails("444", "Bruce", "Wayne", List.of("batman@example.com"), List.of(new PhoneNumber(PhoneType.WORK, "123-456-7890"))));
        contacts.add(new ContactDetails("555", "Steve", "Rogers", List.of("captainamerica@example.com"), List.of(new PhoneNumber(PhoneType.WORK, "123-456-7890"))));
        contacts.add(new ContactDetails("666", "Tony", "Stark", List.of("ironman@example.com"), List.of(new PhoneNumber(PhoneType.WORK, "123-456-7890"))));
        contacts.add(new ContactDetails("777", "Bruce", "Banner", List.of("hulk@example.com"), List.of(new PhoneNumber(PhoneType.WORK, "123-456-7890"))));
        contacts.add(new ContactDetails("888", "Natalia", "Romanova", List.of("blackwidow@example.com"), List.of(new PhoneNumber(PhoneType.WORK, "123-456-7890"))));
    }

    public static InMemoryContactsApi getInstance() {
        if (instance == null) {
            instance = new InMemoryContactsApi();
        }

        return instance;
    }

    @Override
    public Single<List<ContactInfo>> search(SearchContactsRequest request) {
        List<ContactInfo> toReturn = contacts.stream().map(ContactInfo::new).collect(Collectors.toList());
        return Single.just(toReturn);
    }

    @Override
    public Single<ContactDetails> get(String id) {
        return Single.just(getContact(id));
    }

    @Override
    public Single<ApiResult> add(ContactDetails contact) {
        ContactDetails newContact = new ContactDetails(String.valueOf(new Date().getTime()), contact.getFirstName(), contact.getLastName(), contact.getEmailAddresses(), contact.getPhoneNumbers());
        contacts.add(newContact);
        ApiResult result = new ApiResult(newContact.getContactId(), true, List.of());
        return Single.just(result);
    }

    @Override
    public Single<ApiResult> update(ContactDetails contact) {
        ContactDetails c = getContact(contact.getContactId());
        ApiResult result = new ApiResult(contact.getContactId(), c != null, List.of("Unknown contact"));

        if (c == null) {
            return Single.just(result);
        }

        c.setFirstName(contact.getFirstName());
        c.setLastName(contact.getLastName());

        int index = c.getEmailAddresses().size() - 1;

        while (index >= 0) {
            c.deleteMail(index);
            index--;
        }

        index = c.getPhoneNumbers().size() - 1;

        while (index >= 0) {
            c.deletePhone(index);
            index--;
        }

        for (String email : contact.getEmailAddresses()) {
            c.addEmail(email);
        }

        for (PhoneNumber phoneNumber : contact.getPhoneNumbers()) {
            c.addPhoneNumber(phoneNumber);
        }


        return Single.just(result);
    }

    @Override
    public Single<ApiResult> delete(String id) {
        ContactDetails c = getContact(id);
        ApiResult result = new ApiResult(id, c != null, c == null ? List.of("Unknown contact") : List.of());

        if (c != null) {
            contacts.remove(c);
        }

        return Single.just(result);
    }

    private ContactDetails getContact(String id) {
        return contacts
                .stream()
                .filter(x -> x.getContactId().equalsIgnoreCase(id))
                .findAny()
                .orElse(null);

    }
}
