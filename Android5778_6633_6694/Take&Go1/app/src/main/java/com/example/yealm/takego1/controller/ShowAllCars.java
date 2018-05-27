package com.example.yealm.takego1.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yealm.takego1.R;
import com.example.yealm.takego1.model.backend.DB_manager;
import com.example.yealm.takego1.model.backend.DBmanagerFactory;
import com.example.yealm.takego1.model.entities.Car;
import com.example.yealm.takego1.model.entities.CarModel;

import java.util.ArrayList;
import java.util.List;

public class ShowAllCars extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_cars);
        initItemByListView();
    }


    void initItemByListView()
    {
        new AsyncTask<Void, Void, List<Car>>() {
            @Override
            protected void onPostExecute(List<Car> Result)
            {
                if (Result == null) {
                    Toast.makeText(getBaseContext(), "The cars list is empty", Toast.LENGTH_LONG).show();
                    //return;
                    Result=new ArrayList<Car>();
                }
                super.onPostExecute(Result);
                final List<Car> myList=Result;
                ListView listView = new ListView(ShowAllCars.this);

                ArrayAdapter<Car> adapter = new ArrayAdapter<Car>(ShowAllCars.this, R.layout.activity_show_all_cars, myList) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        if (convertView == null) {
                            convertView = View.inflate(ShowAllCars.this, R.layout.activity_show_all_cars, null);
                        }
                        TextView productnumOfCarTextView = (TextView) convertView.findViewById(R.id.numOfCarTextView);
                        TextView productcarModelTextView = (TextView) convertView.findViewById(R.id.carModelTextView);
                        TextView productionnumOfkilometerTextView = (TextView) convertView.findViewById(R.id.numOfkilometerTextView);
                        TextView productionbranchCarTextView = (TextView) convertView.findViewById(R.id.branchCarTextView);
                        productnumOfCarTextView.setText(( myList.get(position).getNumOfCar()));
                        productcarModelTextView.setText(( myList.get(position).getCarModel()));
                        productionnumOfkilometerTextView.setText(((Float)myList.get(position).getNumOfkilometer()).toString());
                        productionbranchCarTextView.setText((myList.get(position).getBranchCar()));


                        return convertView;
                    }
                };
                listView.setAdapter(adapter);
                ShowAllCars.this.setContentView(listView);
            }

            @Override
            protected List<Car> doInBackground(Void... params)
            {
                try {
                    return DBmanagerFactory.getManager().getallcars();

                } catch (Exception e) {
                    return null;
                }
            }
        }.execute();


    }
}