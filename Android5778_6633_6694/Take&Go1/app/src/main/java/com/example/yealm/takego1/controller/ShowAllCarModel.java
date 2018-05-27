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
import com.example.yealm.takego1.model.backend.DBmanagerFactory;
import com.example.yealm.takego1.model.entities.Car;
import com.example.yealm.takego1.model.entities.CarModel;

import java.util.ArrayList;
import java.util.List;

public class ShowAllCarModel extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_car_model);
        initItemByListView();
    }

    void initItemByListView()
    {
        new AsyncTask<Void, Void, List<CarModel>>() {
            @Override
            protected void onPostExecute(List<CarModel> Result)
            {
                if (Result == null) {
                    Toast.makeText(getBaseContext(), "The cars model list is empty", Toast.LENGTH_LONG).show();
                    //return;
                    Result=new ArrayList<CarModel>();
                }
                super.onPostExecute(Result);
                final List<CarModel> myList=Result;
                ListView listView = new ListView(ShowAllCarModel.this);

                ArrayAdapter<CarModel> adapter = new ArrayAdapter<CarModel>(ShowAllCarModel.this, R.layout.activity_show_all_car_model, myList) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        if (convertView == null)
                        {
                            convertView = View.inflate(ShowAllCarModel.this, R.layout.activity_show_all_car_model, null);
                        }
                        TextView productcodeModelTextView = (TextView) convertView.findViewById(R.id.codeModelTextView);
                        TextView productcompanyNameTextView = (TextView) convertView.findViewById(R.id.companyNameTextView);
                        TextView productmodelNameTextView = (TextView) convertView.findViewById(R.id.modelNameTextView);
                        TextView productgearboxTextView = (TextView) convertView.findViewById(R.id.gearboxTextView);
                        TextView productnumOfseatsTextViewTextView = (TextView) convertView.findViewById(R.id.numOfseatsTextView);

                        productcodeModelTextView.setText(( myList.get(position).getCodeModel()));
                        productcompanyNameTextView.setText(( myList.get(position).getCompanyName()));
                        productmodelNameTextView.setText((myList.get(position).getModelName()));
                        productgearboxTextView.setText(String.valueOf(myList.get(position).getGearbox()));
                        productnumOfseatsTextViewTextView.setText(String.valueOf(myList.get(position).getNumOfseats()));

                        return convertView;
                    }
                };
                listView.setAdapter(adapter);
                ShowAllCarModel.this.setContentView(listView);
            }

            @Override
            protected List<CarModel> doInBackground(Void... params)
            {
                try {
                    return DBmanagerFactory.getManager().getallcarmodels();

                } catch (Exception e) {
                    return null;
                }
            }
        }.execute();


    }
}

