package org.example.springcontacts.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContactManager {
    private final List<Contact> contacts;
    private final static String FILE_DELIMITER = ";";

    @Value("${app.contacts.save-folder}")
    private String saveFolder;

    public ContactManager() {
        this.contacts = new ArrayList<>();
    }

    public List<String> getAllContactsInStrings(String delimiter) {
        return contacts.stream()
                .map(contact -> contact.toDelimiterString(delimiter))
                .toList();
    }

    public int deleteByEmail(String email) {
        int count = contacts.size();
        contacts.removeIf(contact -> contact.getEmail().equals(email));
        return count - contacts.size();
    }

    public void addContactFromString(String data, String delimiter) {
        Contact contact = Contact.fromDelimiterString(data, delimiter);
        if (contact == null) {
            throw new IllegalArgumentException();
        }
        contacts.add(contact);
    }

    public void loadFromFile(String filename) throws IOException {
        File file = new File(filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contact contact = Contact.fromDelimiterString(line, FILE_DELIMITER);
                if (contact != null) {
                    contacts.add(contact);
                }
            }
        }
    }

    public void saveToFile(String filename) throws IOException {
        File file = new File(saveFolder, filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Contact contact : contacts) {
                writer.write(contact.toDelimiterString(FILE_DELIMITER));
                writer.write(System.lineSeparator());
            }
            writer.flush();
        }
    }

}
