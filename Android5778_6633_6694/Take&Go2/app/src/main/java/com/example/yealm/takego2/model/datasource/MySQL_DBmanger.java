package com.example.yealm.takego2.model.datasource;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.SystemClock;

import com.example.yealm.takego2.model.backend.DB_manager;
import com.example.yealm.takego2.model.backend.DBmanagerFactory;
import com.example.yealm.takego2.model.backend.TakeGoConst;
import com.example.yealm.takego2.model.entities.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 * Created by yealm on 25/12/2017.
 */

public class MySQL_DBmanger implements DB_manager {

    private String WEB_URL="http://ymordech.vlab.jct.ac.il/take&go/";
    final List<Costumer> result = new ArrayList<Costumer>();


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
    public List<Costumer> getallcostumers() //get customer list from the server
    {

    if(!result.isEmpty())
    {
        return result;
    }

                try {
                    String str = PHPtools.GET(WEB_URL + "/ShowAllCostumer.php");
                    JSONArray array = new JSONObject(str).getJSONArray("costumers");
                    for (int i = 0; i < array.length(); i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);
                        ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                        Costumer customer = TakeGoConst.ContentValuesToCostumer(contentValues);
                        result.add(customer );
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

        return result;
    }
//    @Override
//    public List<Costumer> getallcostumers() {
//        List<Costumer> result = new ArrayList<Costumer>();
//        try {
//            String str = PHPtools.GET(WEB_URL + "/ShowAllCostumer.php");
//            if (str.equals("0 results"))
//                return null;
//            JSONArray array = new JSONObject(str).getJSONArray("costumers");
//            JSONObject jsonObject;
//            for (int i = 0; i < array.length(); i++)
//            {
//                jsonObject = array.getJSONObject(i);
//                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
//                Costumer costumer = TakeGoConst.ContentValuesToCostumer(contentValues);
//                result.add(costumer);
//            }
//            return result;
//        }
//        catch (Exception e)
//        {
//
//           e.printStackTrace();
//        }
//        return null;
//    }


    @Override
    public List<Branch> getallbranchs() {
        final List<Branch> result = new ArrayList<Branch>();

        //       new AsyncTask<Void, Void, List<Branch>>() {
        //         @Override
//            protected void onPostExecute(List<Branch> Result)
//            {
//                List<Branch> l=Result;
//            }
//
//            @Override
//            protected List<Branch> doInBackground(Void... params)
//            {
        try {
            String str = PHPtools.GET(WEB_URL + "/ShowAllBranch.php").trim();
            if (str.equals("0 results"))
                return null;
            JSONArray array = new JSONObject(str).getJSONArray("branchs");
            JSONObject jsonObject;
            for (int i = 0; i < array.length(); i++) {
                jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Branch branch = TakeGoConst.ContentValuesToBranch(contentValues);
                result.add(branch);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
//            }
//        }.execute();
        //SystemClock.sleep(2000);
        // return result;
        //   }
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
        return null;
    }

    public boolean updateCar(ContentValues car)
    {
        //the function set the kilometr of the car
        try
        {
            String result = PHPtools.POST(WEB_URL + "/UpdateCar.php",car);
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

    public List<Invitation> getallOpenInvitation() {
        List<Invitation> result = new ArrayList<Invitation>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "/ShowAllOpenInvitation.php").trim();
            if(str.equals("0 results"))
                return null;
            JSONArray array = new JSONObject(str).getJSONArray("Invitations");
            JSONObject jsonObject;
            for (int i = 0; i < array.length(); i++)
            {
                jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Invitation invitation = TakeGoConst.ContentValuesToCInvitation(contentValues);
                result.add(invitation);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Invitation> getallInvitation() {
        List<Invitation> result = new ArrayList<Invitation>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "/ShowAllInvitation.php");
            if(str.equals("0 results"))
                return null;
            JSONArray array = new JSONObject(str).getJSONArray("Invitations");
            JSONObject jsonObject;
            for (int i = 0; i < array.length(); i++)
            {
                jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                Invitation invitation = TakeGoConst.ContentValuesToCInvitation(contentValues);
                result.add(invitation);
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public List<String> getallNumOfCatchCars() {
        List<String>CatchCars=new ArrayList<String>();
        try
        {
            String str = PHPtools.GET(WEB_URL + "/ShowAllCatchCar.php").trim();
            if(str.equals("0 results"))
                return null;
            JSONArray array = new JSONObject(str).getJSONArray("numOfCar");
            JSONObject jsonObject;
            for (int i = 0; i < array.length(); i++)
            {
                jsonObject = array.getJSONObject(i);
                ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
                String numOfCar =contentValues.getAsString("numOfCar");
                CatchCars.add(numOfCar);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return CatchCars;
    }
    public Car getCar(String NumOfCar)
    {
        Car car = null;
        try {
            ContentValues contentValueNumOfCar = new ContentValues();
            contentValueNumOfCar.put("numOfCar", NumOfCar);
            String str = PHPtools.POST(WEB_URL + "/GetCar.php", contentValueNumOfCar).trim();
            if (str.equals("0 results"))
                return null;
            JSONArray array = new JSONObject(str).getJSONArray("cars");
            JSONObject jsonObject;
            jsonObject = array.getJSONObject(0);
            ContentValues contentValues = PHPtools.JsonToContentValues(jsonObject);
            car = TakeGoConst.ContentValuesToCar(contentValues);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return car;
    }
    public List<Car> getallvacantCars()
    {
        List<Car> cars=getallcars();
        List<String> CatchCars=getallNumOfCatchCars();
        List<Car> result = new ArrayList<>();
        if(CatchCars!=null)
        {
//            for (String catchCar : CatchCars) {
//                catchCars2.add(getCar(catchCar));
//            }
//        if (catchCars2!=null && CatchCars!=null)
//            for (Car car : catchCars2) {
//                vacantCars.remove(car);
//            }
            for (Car car : cars){
                boolean catched = false;
                for(String carNum :CatchCars )
                    if(car.getNumOfCar().equals(carNum))
                        catched = true;
                if (!catched)
                result.add(car);

            }
        }
        return result;
    }

    public List<Car> getallVacantCarsForBranch(String branch) {

//        try
//        {
//            List<Car> result = getallvacantCars();
//            for(Car car: result)
//            {
//                if(!car.getBranchCar().equals(branch))
//               // if(car.getBranchCar()!=branch)
//                    result.remove(car);
//            }
//            return result;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return null;
        List<Car> result = new ArrayList<Car>();
        List<Car> vacantCars = getallvacantCars();
        for(Car car:vacantCars){
            if(car.getBranchCar().equals(branch))
                result.add(car);
        }
        return result;
    }

    public Invitation getInvitation(String numInvi)
    {
        List<Invitation> invs=getallInvitation();
        Invitation invitation=new Invitation();
        for (Invitation inv:invs)
        {
            if(inv.getNumOfCostumer().equals(numInvi) && inv.isDoInvitation()==false)
            {
                invitation=inv;
                return invitation;
            }
        }
        return invitation;
    }

    public String addInvitation(ContentValues invitation)
    {
        try
        {
            String result = PHPtools.POST(WEB_URL + "/AddInvitation.php", invitation);
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

    public String closeInvitation(ContentValues invitation)
    {
        try
        {
            String resultinv = PHPtools.POST(WEB_URL + "/CloseInvitation.php", invitation);
            String id = resultinv;
            //SetUpdate();
            //printLog("addcar:\n" + result);
            String resultcar = PHPtools.POST(WEB_URL + "/UpdateCar.php", invitation);

            return id;

        }
        catch (IOException e)
        {
            // printLog("addcar Exception:\n" + e);
            // Toast.makeText(getBaseContext(), "addcar Exception:\n" + e, Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public boolean checkCloseInvitation()
    {
        long nowDate=new Date().getTime();
        List<Invitation> result=getallInvitation();
        for (Invitation invi:result)
        {
            if(invi.isDoInvitation() && nowDate- invi.getRentEnd().getTime()<10000)
                return true;
        }
        return false;
    }
    public Branch getBranchByBranchNumber(String BranchNumber){
        Branch b=null;
        for (Branch br: getallbranchs())
        {
            if (br.getNumOfBranch()==BranchNumber)
                b=br;
        }
        return b;
    }
    public CarModel getCarModelByID(String ID){
        for(CarModel cm : getallcarmodels())
        {
            if(cm.getCodeModel()==ID)
                return cm;
        }
        return null;
    }

    public void TotalPay(Invitation invitation)
    {
        double pay;
        if(invitation.isDoFuel())
        {
            pay=(invitation.getEndKM()-invitation.getStartKM())*6.19+19*(invitation.getRentEnd().getTime()-invitation.getRentStart().getTime())*0.0036;
        }
        else {
            //pay = 19 * (invitation.getRentEnd().getTime() - invitation.getRentStart().getTime()) * 360000;
            pay = 0.00019 * (invitation.getRentEnd().getTime() - invitation.getRentStart().getTime())*36;
        }
        invitation.setSumToPay((float) pay);

    }
    public Car getCarforCostumer(String id)
    {
//        final Car[] car = new Car[1];
//        new AsyncTask<String, Void, Car>() {
//            @Override
//            protected void onPostExecute(Car Result)
//            {
//                car[0] =Result;
//            }
//
//            @Override
//            protected Car doInBackground(String... params)
//            {
//                for (Invitation invitation :getallOpenInvitation())
//                {
//                    if (invitation.getNumOfCostumer() == params.toString())
//                        return getCar(invitation.getNumOfCar());
//                }
//                return null;
//            }
//        }.execute();
//
//        return car[0];

                 Car cari = null;
                for (Invitation invitation :getallOpenInvitation())
                {
                    if (invitation.getNumOfCostumer().equals(id))
                    {
                        cari=getCar(invitation.getNumOfCar());
                        ///return cari;

                    }
                }
                return cari;
                //return null;
    }
//    @Override
//    public boolean checkUsernameAndPassword(String lastName, int ID)//compare the user name to last name and password to his id
//    {
//        final List<Costumer> l = new ArrayList<Costumer>();
//        new AsyncTask<String, Void, Void>() {
//            protected void onPostExecute(Car Result)
//            {
//            }
//            @Override
//          protected Void doInBackground(String... params)
//          {
//              for (Costumer c:getallcostumers())
//              {
//                  l.add(c);
//              }
//              return null;
//          }
//        }.execute();
//
//        for( Costumer item : l)
//            if((item.getLastName().compareTo(lastName))==0 && (item.getID()==Integer.toString(ID)))
//                return true;
//        return false;
//    }



    @Override
    public boolean checkUsernameAndPassword(String lastName, int ID)//compare the user name to last name and password to his id
    {
        for (Costumer item: getallcostumers()) {
            if ((item.getLastName().compareTo(lastName)) == 0 && (item.getID().equals(Integer.toString(ID))))
                   return true;
        }
        return false;
    }
}

