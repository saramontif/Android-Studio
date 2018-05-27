package com.example.yealm.takego2.model.backend;

import android.os.AsyncTask;
import android.widget.Filter;



import com.example.yealm.takego2.controller.MyexpandableListAdepter;
import com.example.yealm.takego2.model.backend.TakeGoConst;
import com.example.yealm.takego2.model.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 31/01/2017.
 */

public class ExpandableDataFilter extends Filter
{
    DB_manager manager= DBmanagerFactory.getManager();
    public ArrayList<Branch> branches;
    public ArrayList<Car> cars ;




    //public ArrayList<Activity> activities=manager.getListOfActivities();
    String type;
    MyexpandableListAdepter adapter;

    private String carTag="Cars";
    private String branchTag="Branches";

    public ExpandableDataFilter(String typeFilter, MyexpandableListAdepter anAdapter)
    {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected void onPostExecute(Integer a)
            {

            }

            @Override
            protected Integer doInBackground(Void... params)
            {
                branches = (ArrayList<Branch>) manager.getallbranchs();
                cars = (ArrayList<Car>) manager.getallvacantCars();
                return 0;
            }
        }.execute();


        type=typeFilter;
        adapter=anAdapter;
    }

    @Override
    protected FilterResults performFiltering(final CharSequence constraint)
    {
        final String query= String.valueOf(constraint);
        final FilterResults results = new FilterResults();
        new AsyncTask<Void, Void, Integer>() {
                     @Override
            protected void onPostExecute(Integer Result)
            {
               // List<Branch> l=Result;
            }

            @Override
            protected Integer doInBackground(Void... params)
            {


        // We implement here the filter logic
        if(type.compareTo(branchTag)==0)
        {
            branches = (ArrayList<Branch>) manager.getallbranchs();
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = branches;
                results.count = branches.size();
            } else
            {
                // We perform filtering operation
                ArrayList<Branch> nBranchesList = new ArrayList<Branch>();

                for (Branch b : branches)
                {
                    if(b.getAddress().toString().toLowerCase().contains(query.toLowerCase())||String.valueOf(b.getNumParkingSpaces()).toLowerCase().contains(query.toLowerCase())||
                            String.valueOf(b.getNumOfBranch()).toLowerCase().contains(query.toLowerCase()))
                        nBranchesList.add(b);
                }
                results.values = nBranchesList;
                results.count = nBranchesList.size();
            }

        }
        else if (type.compareTo(carTag) == 0) {
                cars = (ArrayList<Car>) manager.getallcars();
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = cars;
                    results.count = cars.size();
                } else {
                    // We perform filtering operation
                    ArrayList<Car> ncarList = new ArrayList<Car>();

                    for (Car c : cars) {
                        String BranchADDRESS=manager.getBranchByBranchNumber(c.getBranchCar()).getAddress().toString();
                        String CompanyName=manager.getCarModelByID(c.getCarModel()).getCompanyName();
                        if (BranchADDRESS.toLowerCase().contains(query.toLowerCase()) || CompanyName.toLowerCase().contains(query.toLowerCase()) ||
                                String.valueOf(c.getNumOfCar()).toLowerCase().contains(query.toLowerCase()) )
                            ncarList.add(c);
                    }
                    results.values = ncarList;
                    results.count = ncarList.size();
                }
            }

            return 0;
                    }
        }.execute();

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results)
    {
        if(type.compareTo(branchTag)==0) {
            branches = (ArrayList<Branch>) results.values;
        }else if (type.compareTo(carTag) == 0) {
                cars = (ArrayList<Car>) results.values;//activity to car
        }

        // Now we have to inform the adapter about the new list filtered
        if (results.count == 0)
        {

            adapter.notifyDataSetInvalidated();
        }
        else
        {
            adapter.notifyDataSetChanged();
        }
    }
}