package org.example.springcontacts.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("init")
public class ContactInitializer {
    @Value("${app.contacts.init-file}")
    private String initFile;

    private final ContactManager contactManager;

    ContactInitializer(ContactManager contactManager) {
        this.contactManager = contactManager;
    }

    @PostConstruct
    private void initialize(){
        try {
            contactManager.loadFromFile(initFile);
        } catch (Exception e) {
            System.out.println("Error by loading init data from file " + initFile);
            System.out.println(e.getMessage());
        }
    }

}
