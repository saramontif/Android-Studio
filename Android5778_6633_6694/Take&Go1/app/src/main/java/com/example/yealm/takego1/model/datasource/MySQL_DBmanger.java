package com.example.yealm.takego1.model.datasource;

import android.content.ContentValues;

import com.example.yealm.takego1.model.backend.DB_manager;
import com.example.yealm.takego1.model.backend.TakeGoConst;
import com.example.yealm.takego1.model.datasource.PHPtools;
import com.example.yealm.takego1.model.entities.Address;
import com.example.yealm.takego1.model.entities.Branch;
import com.example.yealm.takego1.model.entities.Car;
import com.example.yealm.takego1.model.entities.CarModel;
import com.example.yealm.takego1.model.entities.Costumer;
import com.example.yealm.takego1.model.entities.Gearbox;
import com.example.yealm.takego1.model.entities.Seats;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yealm on 25/12/2017.
 */

public class MySQL_DBmanger implements DB_manager {

    private String WEB_URL="http://ymordech.vlab.jct.ac.il/take&go/";

    @Override
    public boolean existcostumer(ContentValues costumer)
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/ExistCostumer.php", costumer);
            String flag = result;
            //SetUpdate();
            //printLog("addcostumer:\n" + result);
            if(flag=="true")
                return true;
            else return false;
        }
        catch (IOException e)
        {
            // printLog("existcostumer Exception:\n" + e);
            // Toast.makeText(getBaseContext(), "existcostumer Exception:\n" + e, Toast.LENGTH_LONG).show();
           // return " ";
        }
        return false;
    }

    @Override
    public String addcostumer(ContentValues costumer) throws Exception
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/AddCostumer.php", costumer);
            String id = result;
            //SetUpdate();
            //printLog("addcostumer:\n" + result);
            return id;
        }
        catch (IOException e)
        {
           // printLog("addcostumer Exception:\n" + e);
           // Toast.makeText(getBaseContext(), "addcostumer Exception:\n" + e, Toast.LENGTH_LONG).show();
            return null;
        }
    }

    @Override
    public String addcarmodel(ContentValues carModel) throws Exception {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/AddCarModel.php", carModel);
            String id = result;
            //SetUpdate();
            //printLog("addcostumer:\n" + result);
            return id;
        }
        catch (IOException e)
        {
            // printLog("addcarModel Exception:\n" + e);
            // Toast.makeText(getBaseContext(), "addcarModel Exception:\n" + e, Toast.LENGTH_LONG).show();
             return null;
        }
    }

    @Override
    public String addcar(ContentValues car) throws Exception
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/AddCar.php", car);
            String id = result;
            //SetUpdate();
            //printLog("addcar:\n" + result);
            return id;
        }
        catch (IOException e)
        {
            // printLog("addcar Exception:\n" + e);
            // Toast.makeText(getBaseContext(), "addcar Exception:\n" + e, Toast.LENGTH_LONG).show();
            return null;
        }
    }

    @Override
    public List<CarModel> getallcarmodels()
    {
        List<CarModel> result = new ArrayList<CarModel>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "/ShowAllCarModel.php");
            if(str.equals("0 results"))
                return null;
            JSONArray array = new JSONObject(str).getJSONArray("carsModel");
            JSONObject jsonObject;
            for (int i = 0; i < array.length(); i++)
            {
                jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                CarModel carModel = TakeGoConst.ContentValuesToCarModel(contentValues);
                result.add(carModel);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Costumer> getallcostumers() {
        List<Costumer> result = new ArrayList<Costumer>();
        try {
            String str = PHPtools.GET(WEB_URL + "/ShowAllCostumer.php");
            if (str.equals("0 results"))
                return null;
            JSONArray array = new JSONObject(str).getJSONArray("costumers");
            JSONObject jsonObject;
            for (int i = 0; i < array.length(); i++)
            {
                jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Costumer costumer = TakeGoConst.ContentValuesToCostumer(contentValues);
                result.add(costumer);
            }
            return result;
        }
        catch (Exception e)
        {

           e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Branch> getallbranchs() {
        List<Branch> result = new ArrayList<Branch>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "/ShowAllBranch.php").trim();
            if(str.equals("0 results"))
                return null;
            JSONArray array = new JSONObject(str).getJSONArray("branchs");
            JSONObject jsonObject;
            for (int i = 0; i < array.length(); i++)
            {
                jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Branch branch = TakeGoConst.ContentValuesToBranch(contentValues);
                result.add(branch);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> getallcars() {
        List<Car> result = new ArrayList<Car>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "/ShowAllCars.php");
            if(str.equals("0 results"))
                return null;
            JSONArray array = new JSONObject(str).getJSONArray("cars");
            JSONObject jsonObject;
            for (int i = 0; i < array.length(); i++)
            {
                jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Car car = TakeGoConst.ContentValuesToCar(contentValues);
                result.add(car);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;    }
    }

