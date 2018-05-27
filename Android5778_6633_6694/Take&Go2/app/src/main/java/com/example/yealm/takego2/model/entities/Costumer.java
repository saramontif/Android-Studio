package com.example.yealm.takego2.model.entities;

/**
 * Created by yealm on 06/11/2017.
 */

public class Costumer {

    private String lastName;
    private  String firatName;
    private String ID;
    private String phoneNumber;
    private String email;
    private String creditCardNumber;

    @Override
    public String toString() {
        return "Costumer{" +
                "lastName='" + lastName + '\'' +
                ", firatName='" + firatName + '\'' +
                ", ID='" + ID + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFiratName() {
        return firatName;
    }

    public void setFiratName(String firatName) {
        this.firatName = firatName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
