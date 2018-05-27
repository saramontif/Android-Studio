package com.example.yealm.takego2.controller;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yealm.takego2.R;
//import com.example.yealm.takego2.model.backend.ArrayDataFilter;
import com.example.yealm.takego2.model.backend.DBmanagerFactory;
import com.example.yealm.takego2.model.backend.DB_manager;
//import com.example.yealm.takego2.model.backend.ExpandableDataFilter;
//import com.example.yealm.takego2.model.backend.RentConst;
import com.example.yealm.takego2.model.backend.ExpandableDataFilter;
import com.example.yealm.takego2.model.backend.TakeGoConst;
import com.example.yealm.takego2.model.entities.Branch;
import com.example.yealm.takego2.model.entities.Car;
import com.example.yealm.takego2.model.entities.Invitation;
//import com.example.yealm.takego2.model.entities.Enums;
//import com.example.yealm.takego2.model.entities.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by User on 31/01/2017.
 */

public class MyexpandableListAdepter extends BaseExpandableListAdapter implements Filterable,View.OnClickListener {

    //ArrayDataFilter df;
   //Car car;
    ExpandableDataFilter edf;
    DB_manager manager = DBmanagerFactory.getManager();
    private ExpandableListView expandableListView;
    private ArrayList<Branch> tempBranch;
    private ArrayList<Car> tempCar;
    FragmentActivity activity;
    Fragment fragment;
    ArrayAdapter<String> adapterCars;
   // private String mType;
    Car selectedCar=null;
    String currentCustomer="";
    Button addOrder;

    private String carTag="Cars";
    private String branchTag="Branches";


    private Button b_rentCar;
    private ImageButton b_mapLink;


    public MyexpandableListAdepter(ExpandableListView myExpandableListView, String type, Fragment mfragment, String mcurrentCustomer)
    {
       // mType=type;
        expandableListView = myExpandableListView;
        activity = mfragment.getActivity();
        fragment=mfragment;
        currentCustomer=mcurrentCustomer;
       // if(mType.compareTo(branchTag)==0)
        {
            tempBranch = (ArrayList<Branch>) manager.getallbranchs();
        }
//        if(mType.compareTo(carTag)==0)
//        {
//            tempCar= (ArrayList<Car>) manager.getallvacantCars();
//        }

    }


    @Override
    public int getGroupCount()
    {
        int returndItem;
       // if(mType.compareTo(branchTag)==0)
        {
            returndItem= tempBranch.size();
        }
//        else if(mType.compareTo(carTag)==0)
//        {
//            returndItem= tempCar.size();
//        }
//        else
//            returndItem=0;
        return returndItem;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        View header=null;

      //  if(mType.compareTo(branchTag)==0)
        {

            header = activity.getLayoutInflater().inflate(R.layout.header_branch, parent, false);
            Branch b = tempBranch.get(groupPosition);
            TextView address = (TextView) header.findViewById(R.id.branchAddressTextView);
            address.setText(b.getAddress().toString());
//            TextView parking = (TextView) header.findViewById(R.id.parkingNumTextView);
//            parking.setText(String.valueOf(b.getNumParkingSpaces()));
        }
//        else if (mType.compareTo(carTag) == 0) {
//            header = activity.getLayoutInflater().inflate(R.layout.header_car, parent, false);
//            Car c = tempCar.get(groupPosition);
//            TextView branchAddress = (TextView) header.findViewById(R.id.carAddressTextView);
//            String bAddress = manager.getBranchByBranchNumber(c.getBranchCar()).getAddress().toString();
//            branchAddress.setText(bAddress);
//            TextView companyName = (TextView) header.findViewById(R.id.companyNameTextView);
//            String cName = manager.getCarModelByID(c.getCarModel()).getCompanyName();
//            companyName.setText(cName);
//        }
        return header;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View item = null;


       // if (mType.compareTo(branchTag) == 0) {

            item = activity.getLayoutInflater().inflate(R.layout.fragment_item_branch, parent, false);
            final Branch b = tempBranch.get(groupPosition);
            final Spinner spinerCars =(Spinner)item.findViewById(R.id.spinerCars);
            addOrder=(Button)item.findViewById(R.id.add_order);
            addOrder.setOnClickListener(MyexpandableListAdepter.this);
            final Context context = activity.getApplicationContext();

            TextView branchNumber;
            TextView Address;
            TextView parkingSpaces;

            branchNumber = (TextView) item.findViewById(R.id.branchNumber);
            branchNumber.setText(branchNumber.getText() + ": " + b.getNumOfBranch());
//            Address = (TextView) item.findViewById(R.id.Address);
//            Address.setText(Address.getText() + ": " + b.getAddress().toString());
            parkingSpaces = (TextView) item.findViewById(R.id.parkingSpaces);
            parkingSpaces.setText(parkingSpaces.getText() + ": " + b.getNumParkingSpaces());
            b_mapLink = (ImageButton) item.findViewById(R.id.b_mapLink);

            b_mapLink.setOnClickListener(this);
            b_mapLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a = b.getAddress().toString();
                    String[] separate = a.split(",");// scity,street,number
                    String address = separate[0] + "," + separate[1] + "," + separate[2];// city,street,number
                    String url = "http://maps.google.com/maps?daddr=" + address;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    v.getContext().startActivity(intent);
                }
            });
            new AsyncTask<Void, Void, ArrayAdapter>() {

                @Override
                protected ArrayAdapter doInBackground(Void... params) {
                    List<Car> l=DBmanagerFactory.getManager().getallVacantCarsForBranch(b.getNumOfBranch());
                    final List<Car> spinnerArray= DBmanagerFactory.getManager().getallVacantCarsForBranch(b.getNumOfBranch());
                    return new ArrayAdapter<Car>(context,android.R.layout.simple_spinner_dropdown_item,spinnerArray);
                }
                @Override
                protected void onPostExecute(ArrayAdapter adapter) {
                    if(adapter.isEmpty())
                        addOrder.setEnabled(false);
                    spinerCars.setAdapter(adapter);
                }
            }.execute();
            spinerCars.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //car = (Car)parent.getItemAtPosition(position);
                    selectedCar= (Car)parent.getItemAtPosition(position);
                    addOrder.setEnabled(true);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    addOrder.setEnabled(false);
                }
            });


        return item;//delete = null

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Filter getFilter()
    {
        if (edf == null)
        {
         //   if(mType.compareTo(branchTag)==0)
            {
                edf = new ExpandableDataFilter(branchTag, this);
            }
//            if(mType.compareTo(carTag)==0)
//            {
//                edf = new ExpandableDataFilter(carTag, this);
//            }
        }
        return edf;
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
      //  if(mType.compareTo(branchTag)==0)
        {
            tempBranch = edf.branches;
        }
//        if(mType.compareTo(carTag)==0)
//        {
//            tempCar=edf.cars;
//        }

    }

    @Override
    public void notifyDataSetInvalidated()
    {
        //no  branch founs as matching the query
        super.notifyDataSetInvalidated();
      //  if(mType.compareTo(branchTag)==0)
        {
            tempBranch = edf.branches;
        }
//        if(mType.compareTo(carTag)==0)
//        {
//            tempCar=edf.cars;
//        }
        Toast.makeText(this.activity,"no results", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {

      //  if (v == b_rentCar) {
        if (v == addOrder) {
            // rent the car
            Invitation invitation=new Invitation();
            invitation.isDoInvitation();
            invitation.setSumToPay(0);

            invitation.setNumOfCostumer(String.valueOf(currentCustomer));
            invitation.setNumOfCar(selectedCar.getNumOfCar());
            invitation.setStartKM(selectedCar.getNumOfkilometer());

            Date d=Calendar.getInstance().getTime();
            invitation.setRentStart(d);
            invitation.setRentEnd(d);
            invitation.setEndKM(selectedCar.getNumOfkilometer());
            invitation.setDoFuel(false);
            final ContentValues cv= TakeGoConst.InvitationToContentValues(invitation);

            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected void onPostExecute(Boolean idResult) {
                    super.onPostExecute(idResult);

                }
                @Override
                protected Boolean doInBackground(Void... params) {
                    return Boolean.valueOf(manager.addInvitation(cv));
                }
            }.execute();

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("order opened");
            builder.setMessage("you can see your order in 'My order' section");
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            });
            builder.create().show();

            FragmentTransaction ft=fragment.getActivity().getSupportFragmentManager().beginTransaction();
            ft.remove(fragment);
            ft.commit();

        }
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 10;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 120);
            totalHeight += 120;
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
