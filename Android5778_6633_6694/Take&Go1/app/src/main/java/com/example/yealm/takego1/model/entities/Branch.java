package com.example.yealm.takego1.model.entities;

import com.example.yealm.takego1.model.entities.Address;

/**
 * Created by yealm on 06/11/2017.
 */

public class Branch
{
    private Address address;
    private int numParkingSpaces;
    private String numOfBranch;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getNumParkingSpaces() {
        return numParkingSpaces;
    }

    public void setNumParkingSpaces(int numParkingSpaces) {
        this.numParkingSpaces = numParkingSpaces;
    }

    public String getNumOfBranch() {
        return numOfBranch;
    }

    public void setNumOfBranch(String numOfBranch) {
        this.numOfBranch = numOfBranch;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "address=" + address +
                ", numParkingSpaces=" + numParkingSpaces +
                ", numOfBranch='" + numOfBranch + '\'' +
                '}';
    }
}
