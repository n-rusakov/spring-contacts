package org.example.springcontacts.manager;


public class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String toDelimiterString(String delimiter) {
        return name + delimiter + phone + delimiter + email;
    }

    public static Contact fromDelimiterString(String data, String delimiter) {

        String[] fields = data.trim().split(delimiter);
        if (fields.length != 3) {
            return null;
        }
        return new Contact(fields[0].trim(), fields[1].trim(), fields[2].trim());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
