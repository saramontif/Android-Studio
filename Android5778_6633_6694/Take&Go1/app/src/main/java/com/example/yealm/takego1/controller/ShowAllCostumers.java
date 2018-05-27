package com.example.yealm.takego1.controller;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yealm.takego1.R;
import com.example.yealm.takego1.model.backend.DBmanagerFactory;
import com.example.yealm.takego1.model.entities.Car;
import com.example.yealm.takego1.model.entities.Costumer;

import java.util.ArrayList;
import java.util.List;

public class ShowAllCostumers extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_costumers);
        initItemByListView();
    }


    void initItemByListView()
    {
        new AsyncTask<Void, Void, List<Costumer>>() {
            @Override
            protected void onPostExecute(List<Costumer> Result)
            {
                if (Result == null) {
                    Toast.makeText(getBaseContext(), "The costumer list is empty", Toast.LENGTH_LONG).show();
                    //return;
                    Result=new ArrayList<Costumer>();
                }
                super.onPostExecute(Result);
                final List<Costumer> myList=Result;
                ListView listView = new ListView(ShowAllCostumers.this);

                ArrayAdapter<Costumer> adapter = new ArrayAdapter<Costumer>(ShowAllCostumers.this, R.layout.activity_show_all_costumers, myList) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        if (convertView == null) {
                            convertView = View.inflate(ShowAllCostumers.this, R.layout.activity_show_all_costumers, null);
                        }
                        TextView productFirstNameTextView = (TextView) convertView.findViewById(R.id.FirstNameTextView);
                        TextView productLastNameTextView = (TextView) convertView.findViewById(R.id.LastNameTextView);
                        TextView productIdTextView = (TextView) convertView.findViewById(R.id.IdTextView);
                        productFirstNameTextView.setText(( myList.get(position).getFiratName()).toString());
                        productLastNameTextView.setText(( myList.get(position).getLastName()).toString());
                        productIdTextView.setText(( myList.get(position).getID()).toString());
                        return convertView;
                    }
                };
                listView.setAdapter(adapter);
                ShowAllCostumers.this.setContentView(listView);
            }

            @Override
            protected List<Costumer> doInBackground(Void... params)
            {
                try {
                    List<Costumer> lcostumer=DBmanagerFactory.getManager().getallcostumers();
                    return lcostumer;

                } catch (Exception e) {
                    return null;
                }
            }
        }.execute();


    }
}

