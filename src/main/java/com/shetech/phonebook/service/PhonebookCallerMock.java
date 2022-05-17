package com.shetech.phonebook.service;

import com.shetech.phonebook.domain.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhonebookCallerMock {

    private List<Contact> contacts;

    public PhonebookCallerMock() {
        this.contacts = new ArrayList<>();

        /* - lista mockata contatti - */
        Contact c1 = new Contact();
        c1.setName("Aldo");
        c1.setSurname("Baglio");
        c1.setPhone("3452115689");
        c1.setAge(65);
        c1.setFemale(false);
        contacts.add(c1);

        Contact c2 = new Contact();
        c2.setName("Giovanni");
        c2.setSurname("Storti");
        c2.setPhone("3401656887");
        c2.setAge(67);
        c2.setFemale(false);
        contacts.add(c2);

        Contact c3 = new Contact();
        c3.setName("Giacomino");
        c3.setSurname("Poretti");
        c3.setPhone("3381919122");
        c3.setAge(62);
        c3.setFemale(false);
        contacts.add(c3);

        Contact c4 = new Contact();
        c4.setName("Marina");
        c4.setSurname("Massironi");
        c4.setPhone("3665478988");
        c4.setAge(50);
        c4.setFemale(true);
        contacts.add(c4);
    }

    public List<Contact> retrieveContacts() {
        return this.contacts;
    }

    public Contact retrieveContactByPhone(String phone) {
        for (Contact c : contacts) {
            if (c.getPhone().equalsIgnoreCase(phone)) {
                return c;
            }
        }
        return null;
    }

    public Contact retrieveContactByPhoneWithLambda(String phone) {
        Optional<Contact> contact = this.contacts.stream().filter(c -> c.getPhone().equalsIgnoreCase(phone)).findFirst();
        if (contact.isPresent()) {
            return contact.get();
        }
        return null;
    }

    public boolean addContact(Contact contact) {

        Contact c = retrieveContactByPhoneWithLambda(contact.getPhone());
        if (c == null) {
            this.contacts.add(contact);
            return true;
        }
        return false;
    }

    public boolean removeContact(String phone) {
        Contact c = retrieveContactByPhoneWithLambda(phone);

        if (c != null) {
            this.contacts.remove(c);
            return true;
        }

        return false;
    }

    public boolean updateContact(String phone, Contact contact) {
        Contact c = retrieveContactByPhoneWithLambda(phone);

        if (c != null) {
            removeContact(phone);
            addContact(contact);
        }

        return false;

    }
}
