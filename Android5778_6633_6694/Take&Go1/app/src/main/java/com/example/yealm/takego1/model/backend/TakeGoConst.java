package com.example.yealm.takego1.model.backend;

import android.content.ContentValues;

import com.example.yealm.takego1.model.entities.Address;
import com.example.yealm.takego1.model.entities.Branch;
import com.example.yealm.takego1.model.entities.Car;
import com.example.yealm.takego1.model.entities.CarModel;
import com.example.yealm.takego1.model.entities.Costumer;
import com.example.yealm.takego1.model.entities.Gearbox;
import com.example.yealm.takego1.model.entities.Invitation;
import com.example.yealm.takego1.model.entities.Seats;

import java.text.*;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yealm on 16/11/2017.
 */

public class TakeGoConst
{
    public static class AddressConst
    {
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String NUMBER = "number";
    }
    public static ContentValues AddressToContentValues(Address address) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TakeGoConst.AddressConst.CITY, address.getCity());
        contentValues.put(TakeGoConst.AddressConst.STREET, address.getStreet());
        contentValues.put(TakeGoConst.AddressConst.NUMBER, address.getNumber());
        return contentValues;
    }
    public static Address ContentValuesToAddress(ContentValues contentValues)
    {
        Address address = new Address();
        address.setCity(contentValues.getAsString(AddressConst.CITY));
        address.setStreet(contentValues.getAsString(AddressConst.STREET));
        address.setNumber(contentValues.getAsInteger(AddressConst.NUMBER));
        return address;
    }
    public static class BranchConst
    {
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String NUMBER = "number";
        public static final String NUMPARKINGSPACES = "numParkingSpaces";
        public static final String NUMOFBRANCH = "numOfBranch";
    }

    public static ContentValues BranchToContentValues(Branch branch) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TakeGoConst.AddressConst.CITY, branch.getAddress().getCity());
        contentValues.put(TakeGoConst.AddressConst.STREET, branch.getAddress().getStreet());
        contentValues.put(TakeGoConst.AddressConst.NUMBER, branch.getAddress().getNumber());
        contentValues.put(TakeGoConst.BranchConst.NUMPARKINGSPACES, branch.getNumParkingSpaces());
        contentValues.put(TakeGoConst.BranchConst.NUMOFBRANCH, branch.getNumOfBranch());
        return contentValues;
    }

    public static Branch ContentValuesToBranch(ContentValues contentValues)
    {
        Branch branch = new Branch();
        Address address=new Address();
        address.setCity(contentValues.getAsString(AddressConst.CITY));
        address.setStreet(contentValues.getAsString(AddressConst.STREET));
        address.setNumber(contentValues.getAsInteger(AddressConst.NUMBER));
        branch.setAddress(address);
        branch.setNumParkingSpaces(contentValues.getAsInteger(BranchConst.NUMPARKINGSPACES));
        branch.setNumOfBranch(contentValues.getAsString(BranchConst.NUMOFBRANCH));
        return branch;
    }

    public static class CarConst
    {
        public static final String BRANCHCAR = "branchCar";
        public static final String CARMODEL = "carModel";
        public static final String NUMOFKILOMETER = "numOfkilometer";
        public static final String NUMOFCAR = "numOfCar";
    }

    public static ContentValues CarToContentValues(Car car)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TakeGoConst.CarConst.BRANCHCAR, car.getBranchCar());
        contentValues.put(TakeGoConst.CarConst.CARMODEL, car.getCarModel());
        contentValues.put(TakeGoConst.CarConst.NUMOFKILOMETER, car.getNumOfkilometer());
        contentValues.put(TakeGoConst.CarConst.NUMOFCAR, car.getNumOfCar());
        return contentValues;
    }

    public static Car ContentValuesToCar(ContentValues contentValues)
    {
        Car car = new Car();
        car.setBranchCar(contentValues.getAsString(CarConst.BRANCHCAR));
        car.setCarModel(contentValues.getAsString(CarConst.CARMODEL));
        car.setNumOfkilometer(contentValues.getAsInteger(CarConst.NUMOFKILOMETER));
        car.setNumOfCar(contentValues.getAsString(CarConst.NUMOFCAR));
        return car;
    }

    public static class CarModelConst
    {
        public static final String CODEMODEL = "codeModel";
        public static final String COMPANYNAME = "companyName";
        public static final String MODELNAME = "modelName";
        public static final String ENGINECAPACITY = "engineCapacity";
        public static final String GEARBOX = "gearbox";
        public static final String NUMOFSEATS = "numOfseats";
    }

    public static ContentValues CarModelToContentValues(CarModel carModel)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TakeGoConst.CarModelConst.CODEMODEL, carModel.getCodeModel());
        contentValues.put(TakeGoConst.CarModelConst.COMPANYNAME, carModel.getCompanyName());
        contentValues.put(TakeGoConst.CarModelConst.MODELNAME, carModel.getModelName());
        contentValues.put(TakeGoConst.CarModelConst.ENGINECAPACITY, carModel.getEngineCapacity());
        contentValues.put(TakeGoConst.CarModelConst.GEARBOX, String.valueOf(carModel.getGearbox()));
        contentValues.put(TakeGoConst.CarModelConst.NUMOFSEATS,String.valueOf( carModel.getNumOfseats()));
        return contentValues;
    }

    public static CarModel ContentValuesToCarModel(ContentValues contentValues)
    {
        CarModel car = new CarModel();
        car.setCodeModel(contentValues.getAsString(CarModelConst.CODEMODEL));
        car.setCompanyName(contentValues.getAsString(CarModelConst.COMPANYNAME));
        car.setModelName(contentValues.getAsString(CarModelConst.MODELNAME));
        car.setEngineCapacity(contentValues.getAsFloat(CarModelConst.ENGINECAPACITY));
        Gearbox gearbox =Gearbox.valueOf(contentValues.getAsString(CarModelConst.GEARBOX));
        car.setGearbox(gearbox);
        Seats seats=Seats.valueOf(contentValues.getAsString(CarModelConst.NUMOFSEATS));
        car.setNumOfseats(seats);
        return car;
    }

    public static class CostumerConst
    {
        public static final String ID = "_id";
        public static final String LASTNAME = "lastName";
        public static final String FIRSTNAME = "firatName";
        public static final String PHONENUMBER = "phoneNumber";
        public static final String EMAIL = "email";
        public static final String CREDITCARNUMBER = "creditCardNumber";
    }

    public static ContentValues CostumerToContentValues(Costumer costumer)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TakeGoConst.CostumerConst.ID, costumer.getID());
        contentValues.put(TakeGoConst.CostumerConst.LASTNAME, costumer.getLastName());
        contentValues.put(TakeGoConst.CostumerConst.FIRSTNAME, costumer.getFiratName());
        contentValues.put(TakeGoConst.CostumerConst.PHONENUMBER, costumer.getPhoneNumber());
        contentValues.put(TakeGoConst.CostumerConst.EMAIL, costumer.getEmail());
        contentValues.put(TakeGoConst.CostumerConst.CREDITCARNUMBER, costumer.getCreditCardNumber());
        return contentValues;
    }

    public static Costumer ContentValuesToCostumer(ContentValues contentValues)
    {
        Costumer costumer = new Costumer();
        costumer.setID(contentValues.getAsString(CostumerConst.ID));
        costumer.setLastName(contentValues.getAsString(CostumerConst.LASTNAME));
        costumer.setFiratName(contentValues.getAsString(CostumerConst.FIRSTNAME));
        costumer.setPhoneNumber(contentValues.getAsString(CostumerConst.PHONENUMBER));
        costumer.setEmail(contentValues.getAsString(CostumerConst.EMAIL));
        costumer.setCreditCardNumber(contentValues.getAsString(CostumerConst.CREDITCARNUMBER));
        return costumer;
    }

    public static class InvitationConst
    {
        public static final String NUMOFINVITATION = "_numOfInvitation";
        public static final String NUMOFCOSTUMER = "numOfCostumer";
        public static final String DOINVITATION = "doInvitation";
        public static final String NUMOFCAR = "numOfCar";
        public static final String RENTSTART = "rentStart";
        public static final String RENTEND = "rentEnd";
        public static final String STARTKM = "srartKM";
        public static final String ENDKM = "endKM";
        public static final String DOFUEL = "doFuel";
        public static final String LITERSFUEL = "litersFuel";
        public static final String SUMTOPAY = "sumToPay";

    }

    public static ContentValues InvitationToContentValues(Invitation invitation)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TakeGoConst.InvitationConst.NUMOFINVITATION, invitation.getNumOfInvitation());
        contentValues.put(TakeGoConst.InvitationConst.NUMOFCOSTUMER, invitation.getNumOfCostumer());
        contentValues.put(TakeGoConst.InvitationConst.DOINVITATION, invitation.isDoInvitation());
        contentValues.put(TakeGoConst.InvitationConst.NUMOFCAR, invitation.getNumOfCar());
        contentValues.put(TakeGoConst.InvitationConst.RENTSTART, invitation.getRentStart().toString());
        contentValues.put(TakeGoConst.InvitationConst.RENTEND, invitation.getRentEnd().toString());
        contentValues.put(TakeGoConst.InvitationConst.STARTKM, invitation.getStartKM());
        contentValues.put(TakeGoConst.InvitationConst.ENDKM, invitation.getEndKM());
        contentValues.put(TakeGoConst.InvitationConst.DOFUEL, invitation.isDoFuel());
        contentValues.put(TakeGoConst.InvitationConst.LITERSFUEL, invitation.getLitersFuel());
        contentValues.put(TakeGoConst.InvitationConst.SUMTOPAY, invitation.getSumToPay());
        return contentValues;
    }

    public static Invitation ContentValuesToCInvitation(ContentValues contentValues)
    {
        Invitation invitation = new Invitation();
        invitation.setNumOfInvitation(contentValues.getAsString(InvitationConst.NUMOFINVITATION));
        invitation.setNumOfCostumer(contentValues.getAsString(InvitationConst.NUMOFCOSTUMER));
        invitation.setDoInvitation(contentValues.getAsBoolean(InvitationConst.DOINVITATION));
        invitation.setNumOfCar(contentValues.getAsString(InvitationConst.NUMOFCAR));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = df.parse(contentValues.getAsString(InvitationConst.RENTEND));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        invitation.setRentStart(date);
        try {
            date = df.parse(contentValues.getAsString(InvitationConst.RENTEND));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        invitation.setRentEnd(date);
        invitation.setStartKM(contentValues.getAsFloat(InvitationConst.STARTKM));
        invitation.setEndKM(contentValues.getAsFloat(InvitationConst.ENDKM));
        invitation.setDoFuel(contentValues.getAsBoolean(InvitationConst.DOFUEL));
        invitation.setLitersFuel(contentValues.getAsFloat(InvitationConst.LITERSFUEL));
        invitation.setSumToPay(contentValues.getAsFloat(InvitationConst.SUMTOPAY));
        return invitation;
    }


}
