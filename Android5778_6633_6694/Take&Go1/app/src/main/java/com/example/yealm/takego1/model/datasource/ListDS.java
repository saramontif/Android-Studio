package com.example.yealm.takego1.model.datasource;

import android.content.ContentValues;

import com.example.yealm.takego1.model.backend.DB_manager;
import com.example.yealm.takego1.model.entities.Address;
import com.example.yealm.takego1.model.entities.Branch;
import com.example.yealm.takego1.model.entities.Car;
import com.example.yealm.takego1.model.entities.CarModel;
import com.example.yealm.takego1.model.entities.Costumer;

import java.util.ArrayList;
import java.util.List;

import static com.example.yealm.takego1.model.backend.TakeGoConst.*;

/**
 * Created by yealm on 12/11/2017.
 */

public class ListDS implements DB_manager
{
    static List<Car> cars;
    static List<Costumer> costumers;
    static List<CarModel> carModels;
    static List<Branch> branches;

    static
    {
        cars = new ArrayList<>();
        costumers = new ArrayList<>();
        carModels = new ArrayList<>();
        branches = new ArrayList<Branch>();
        Branch branch = new Branch();
        Address address=new Address();
        address.setCity("jerusalm");
        address.setStreet("Brit hadfus");
        address.setNumber(7);
        branch.setAddress(address);
        branch.setNumOfBranch("2");
        branch.setNumParkingSpaces(3);
        branches.add(branch);
    }

    @Override
    public boolean existcostumer(ContentValues values)
    {
        Costumer costumer=ContentValuesToCostumer(values);
        for (Costumer item :costumers)
        {
            if(item.getID()==costumer.getID())
                return true;
        }
        return false;
    }

    @Override
    public String addcostumer(ContentValues values) throws Exception {

       if(existcostumer(values)==true) {
            throw new Exception("this costumer is exist");
        }
        else {
           Costumer costumer = ContentValuesToCostumer(values);
           costumers.add(costumer);
           return costumer.getID();
       }
    }

    public boolean existcarmodel(ContentValues values)
    {
        CarModel carModel=ContentValuesToCarModel(values);
        for (CarModel item :carModels)
        {
            if(item.getCodeModel()==carModel.getCodeModel())
                return true;
        }
        return false;
    }
    @Override
    public String addcarmodel(ContentValues values) throws Exception {
        if(existcarmodel( values)==true) {
            throw new Exception("this car model is exist");
        }
        else
            {
            CarModel carModel = ContentValuesToCarModel(values);
            carModels.add(carModel);
            return carModel.getCodeModel();
        }
    }

    public boolean existcar(ContentValues values)
    {
        Car car=ContentValuesToCar(values);
        for (Car item :cars)
        {
            if(item.getNumOfCar()==car.getNumOfCar())
                return true;
        }
        return false;
    }
    @Override
    public String addcar(ContentValues values) throws Exception {
        if(existcar(values)==true) {
            throw new Exception("this car is exist");
        }
        else {
            Car car = ContentValuesToCar(values);
            cars.add(car);
            return car.getNumOfCar();
        }
    }

    @Override
    public List<CarModel> getallcarmodels() {
        return carModels;
    }

    @Override
    public List<Costumer> getallcostumers() {
        return costumers;
    }

    @Override
    public List<Branch> getallbranchs() {
        return branches;
    }

    @Override
    public List<Car> getallcars() {
        return cars;
    }
}