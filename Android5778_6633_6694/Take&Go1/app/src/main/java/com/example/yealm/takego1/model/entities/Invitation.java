package com.example.yealm.takego1.model.entities;

import java.util.Date;

/**
 * Created by yealm on 06/11/2017.
 */

public class Invitation
{
    private String numOfInvitation;
    private String numOfCostumer;
    private boolean doInvitation;
    private String  numOfCar;
    private Date rentStart;
    private Date rentEnd;
    private float startKM;
    private float endKM;
    private boolean doFuel;
    private float litersFuel;
    private float sumToPay;


    public String getNumOfInvitation() {
        return numOfInvitation;
    }

    public void setNumOfInvitation(String numOfInvitation) {
        this.numOfInvitation = numOfInvitation;
    }

    public String getNumOfCostumer() {
        return numOfCostumer;
    }

    public void setNumOfCostumer(String numOfCostumer) {
        this.numOfCostumer = numOfCostumer;
    }

    public String getNumOfCar() {
        return numOfCar;
    }

    public void setNumOfCar(String numOfCar) {
        this.numOfCar = numOfCar;
    }

    public Date getRentStart() {
        return rentStart;
    }

    public void setRentStart(Date rentStart) {
        this.rentStart = rentStart;
    }

    public Date getRentEnd() {
        return rentEnd;
    }

    public void setRentEnd(Date rentEnd) {
        this.rentEnd = rentEnd;
    }

    public float getStartKM() {
        return startKM;
    }

    public void setStartKM(float startKM) {
        this.startKM = startKM;
    }

    public float getEndKM() {
        return endKM;
    }

    public void setEndKM(float endKM) {
        this.endKM = endKM;
    }

    public float getLitersFuel() {
        return litersFuel;
    }

    public void setLitersFuel(float litersFuel) {
        this.litersFuel = litersFuel;
    }

    public float getSumToPay() {
        return sumToPay;
    }

    public void setSumToPay(float sumToPay) {
        this.sumToPay = sumToPay;
    }

    public boolean isDoInvitation() {
        return doInvitation;
    }

    public void setDoInvitation(boolean doInvitation) {
        this.doInvitation = doInvitation;
    }

    public boolean isDoFuel() {
        return doFuel;
    }

    public void setDoFuel(boolean doFuel) {
        this.doFuel = doFuel;
    }
}
