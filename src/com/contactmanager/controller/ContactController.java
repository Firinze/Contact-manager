package com.contactmanager.controller;

import com.contactmanager.model.Contact;
import com.contactmanager.model.ContactManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactController {
    private ContactManager contactManager;
    private ObservableList<Contact> contactList;

    public ContactController() {
        contactManager = new ContactManager();
        contactList = FXCollections.observableArrayList(contactManager.getContacts());
    }

    public ObservableList<Contact> getContactList() {
        return contactList;
    }

    public boolean addContact(Contact contact) {
        if (contactManager.addContact(contact)) {
            contactList.add(contact);
            return true;
        }
        return false;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        if (contactManager.updateContact(oldContact, newContact)) {
            int index = contactList.indexOf(oldContact);
            contactList.set(index, newContact);
            return true;
        }
        return false;
    }

    public boolean removeContact(Contact contact) {
        if (contactManager.removeContact(contact)) {
            contactList.remove(contact);
            return true;
        }
        return false;
    }
}
