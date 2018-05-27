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
import com.example.yealm.takego1.model.entities.Branch;
import com.example.yealm.takego1.model.entities.Car;

import java.util.ArrayList;
import java.util.List;

public class ShowAllBranch extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_branch);
        initItemByListView();

    }

    void initItemByListView()
    {
        new AsyncTask<Void, Void, List<Branch>>() {
            @Override
            protected void onPostExecute(List<Branch> Result)
            {
                if (Result == null) {
                    Toast.makeText(getBaseContext(), "The branches list is empty", Toast.LENGTH_LONG).show();
                    //return;
                    Result=new ArrayList<Branch>();
                }
                super.onPostExecute(Result);
                final List<Branch> myList=Result;
                ListView listView = new ListView(ShowAllBranch.this);

                ArrayAdapter<Branch> adapter = new ArrayAdapter<Branch>(ShowAllBranch.this, R.layout.activity_show_all_branch, myList) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        if (convertView == null) {
                            convertView = View.inflate(ShowAllBranch.this, R.layout.activity_show_all_branch, null);
                        }
                        TextView productaddressTextView = (TextView) convertView.findViewById(R.id.addressTextView);
                        TextView productnumOfBranchTextView = (TextView) convertView.findViewById(R.id.numOfBranchTextView);
                        TextView productionnumParkingSpacesTextView = (TextView) convertView.findViewById(R.id.numParkingSpacesTextView);
                        productaddressTextView.setText(( myList.get(position).getAddress()).toString());
                        productnumOfBranchTextView.setText(( myList.get(position).getNumOfBranch()).toString());
                        productionnumParkingSpacesTextView.setText(((Integer)myList.get(position).getNumParkingSpaces()).toString());


                        return convertView;
                    }
                };
                listView.setAdapter(adapter);
                ShowAllBranch.this.setContentView(listView);
            }

            @Override
            protected List<Branch> doInBackground(Void... params)
            {
                try {
                    return DBmanagerFactory.getManager().getallbranchs();

                } catch (Exception e) {
                    return null;
                }
            }
        }.execute();


    }
}

