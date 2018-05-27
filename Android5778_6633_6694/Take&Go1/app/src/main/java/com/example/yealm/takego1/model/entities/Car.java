package com.example.yealm.takego1.model.entities;

/**
 * Created by yealm on 06/11/2017.
 */

public class Car
{
    private String branchCar;
    private String carModel;
    private float numOfkilometer;
    private String numOfCar;

    public String getBranchCar() {
        return branchCar;
    }

    public void setBranchCar(String branchCar) {
        this.branchCar = branchCar;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public float getNumOfkilometer() {
        return numOfkilometer;
    }

    public void setNumOfkilometer(float numOfkilometer) {
        this.numOfkilometer = numOfkilometer;
    }

    public String getNumOfCar() {
        return numOfCar;
    }

    public void setNumOfCar(String numOfCar) {
        this.numOfCar = numOfCar;
    }

    @Override
    public String toString() {
        return "Car{" +
                "branchCar='" + branchCar + '\'' +
                ", carModel='" + carModel + '\'' +
                ", numOfkilometer=" + numOfkilometer +
                ", numOfCar='" + numOfCar + '\'' +
                '}';
    }
}
