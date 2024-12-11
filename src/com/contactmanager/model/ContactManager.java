package com.contactmanager.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private static final String CONTACTS_FILE = "contacts.dat";
    private List<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
        loadContacts();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public boolean addContact(Contact contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
            saveContacts();
            return true;
        }
        return false;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int index = contacts.indexOf(oldContact);
        if (index >= 0) {
            contacts.set(index, newContact);
            saveContacts();
            return true;
        }
        return false;
    }

    public boolean removeContact(Contact contact) {
        if (contacts.remove(contact)) {
            saveContacts();
            return true;
        }
        return false;
    }

    private void loadContacts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CONTACTS_FILE))) {
            contacts = (List<Contact>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous contacts found.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONTACTS_FILE))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
