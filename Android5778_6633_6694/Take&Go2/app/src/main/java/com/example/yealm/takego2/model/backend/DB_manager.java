package com.example.yealm.takego2.model.backend;

/**
 * Created by yealm on 12/11/2017.
 */

import android.content.ContentValues;

import com.example.yealm.takego2.model.entities.*;

import java.util.List;


public interface DB_manager {
    public boolean existcostumer(ContentValues costumer);

    public String addcostumer(ContentValues costumer) throws Exception;

    public String addcarmodel(ContentValues carModel) throws Exception;

    public String addcar(ContentValues car) throws Exception;

    public List<CarModel> getallcarmodels();

    public List<Costumer> getallcostumers();

    public List<Branch> getallbranchs();

    public List<Car> getallcars();

    public boolean checkCloseInvitation();

    public List<Car> getallvacantCars();

    public List<Car> getallVacantCarsForBranch(String branch);
    public List<String> getallNumOfCatchCars();
    public Branch getBranchByBranchNumber(String BranchNumber);
    public CarModel getCarModelByID(String ID);
    public String addInvitation(ContentValues invitation);
    public void TotalPay(Invitation invitation);
    public Car getCarforCostumer(String id);
    public List<Invitation> getallInvitation() ;
    public boolean checkUsernameAndPassword(String lastName, int ID);//compare the user name to last name and password to his id
    public String closeInvitation(ContentValues invitation);
    public Invitation getInvitation(String numInvi);



}



