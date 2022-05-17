package com.shetech.phonebook.controller;

import com.shetech.phonebook.domain.Contact;
import com.shetech.phonebook.service.PhonebookCaller;
import com.shetech.phonebook.service.PhonebookCallerMock;

import java.util.List;

public class PhonebookPage {

    /* - service - */
    private PhonebookCallerMock phonebookCallerMock;    //MOCK
    private PhonebookCaller phonebookCaller;

    public void add(Contact contact) {
        phonebookCaller.addContact(contact);
    }

    public void remove(String phone) {
        phonebookCaller.removeContact(phone);
    }

    public void update(String phone, Contact contact) {
        phonebookCaller.updateContact(phone, contact);
    }

    public List<Contact> getContacts() {
        return phonebookCaller.retrieveContacts();
    }


    public void setPhonebookCaller(PhonebookCaller phonebookCaller) {
        this.phonebookCaller = phonebookCaller;
    }

    public void setPhonebookCallerMock(PhonebookCallerMock phonebookCallerMock) {
        this.phonebookCallerMock = phonebookCallerMock;
    }
}
