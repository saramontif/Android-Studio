package com.example.yealm.takego2.model.entities;

/**
 * Created by yealm on 06/11/2017.
 */

public class CarModel
{
    private String codeModel;
    private String companyName;
    private String modelName;
    private float engineCapacity;
    private Gearbox gearbox;
    private Seats numOfseats;

    public String getCodeModel() {
        return codeModel;
    }

    public void setCodeModel(String codeModel) {
        this.codeModel = codeModel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public float getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }


    public Seats getNumOfseats() {
        return numOfseats;
    }

    public void setNumOfseats(Seats numOfseats) {
        this.numOfseats = numOfseats;
    }

    @Override
    public String toString() {
        return
                "codeModel='" + codeModel + '\'' +
                ", companyName='" + companyName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", gearbox=" + gearbox +
                ", numOfseats=" + numOfseats;
    }
}
