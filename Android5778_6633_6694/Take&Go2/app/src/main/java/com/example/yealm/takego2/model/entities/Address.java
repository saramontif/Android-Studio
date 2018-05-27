package com.example.yealm.takego2.model.entities;

/**
 * Created by yealm on 06/11/2017.
 */

public class Address
{
    private String city;
    private String street;
    private int number;

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return city + "  , " + street + " ,  " + number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
