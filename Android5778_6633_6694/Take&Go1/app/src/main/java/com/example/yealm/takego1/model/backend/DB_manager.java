package com.example.yealm.takego1.model.backend;

/**
 * Created by yealm on 12/11/2017.
 */

import android.content.ContentValues;

import com.example.yealm.takego1.model.entities.Branch;
import com.example.yealm.takego1.model.entities.Car;
import com.example.yealm.takego1.model.entities.CarModel;
import com.example.yealm.takego1.model.entities.Costumer;
import java.util.List;


public interface DB_manager
{
    public boolean existcostumer(ContentValues costumer);
    public String addcostumer (ContentValues costumer) throws Exception;
    public String addcarmodel(ContentValues carModel) throws Exception;
    public String addcar (ContentValues car) throws Exception;
    public List<CarModel> getallcarmodels();
    public List<Costumer> getallcostumers();
    public List<Branch> getallbranchs();
    public List<Car> getallcars();
}
