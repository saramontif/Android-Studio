package com.example.yealm.takego1.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yealm.takego1.R;
import com.example.yealm.takego1.model.backend.DBmanagerFactory;
import com.example.yealm.takego1.model.backend.TakeGoConst;
import com.example.yealm.takego1.model.entities.Gearbox;
import com.example.yealm.takego1.model.entities.Seats;

public class AddCarModel extends Activity implements View.OnClickListener 
{
        private EditText codeModelEditText;
        private EditText companyNameEditText;
        private EditText modelNameEditText;
        private EditText engineCapacityEditText;
        private Spinner GearboxSpinner;
        private Spinner numOfseatsSpinner;
        private Button addCarModelButton;
    

    private void findViews()
    {
        codeModelEditText = (EditText)findViewById( R.id.codeModelEditText );
        companyNameEditText = (EditText)findViewById( R.id.companyNameEditText );
        modelNameEditText = (EditText)findViewById( R.id.modelNameEditText );
        engineCapacityEditText = (EditText)findViewById( R.id.engineCapacityEditText );
        GearboxSpinner = (Spinner) findViewById( R.id.GearboxSpinner );
        numOfseatsSpinner = (Spinner)findViewById( R.id.numOfseatsSpinner );
        addCarModelButton = (Button)findViewById( R.id.addCarModelButton );

        GearboxSpinner.setAdapter(new ArrayAdapter<Gearbox>(this,android.R.layout.simple_expandable_list_item_1,Gearbox.values()));
        numOfseatsSpinner.setAdapter(new ArrayAdapter<Seats>(this,android.R.layout.simple_expandable_list_item_1,Seats.values()));

        addCarModelButton.setOnClickListener( this );


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_model);
        findViews();
    }
    @Override
    public void onClick(View v)
    {
        if ( v == addCarModelButton )
        {
            addCarModel();
        }
        finish();
    }
    private void addCarModel()
    {

        final ContentValues contentValues = new ContentValues();
        try
        {
            contentValues.put(TakeGoConst.CarModelConst.CODEMODEL, this.codeModelEditText.getText().toString());
            contentValues.put(TakeGoConst.CarModelConst.COMPANYNAME, this.companyNameEditText.getText().toString());
            contentValues.put(TakeGoConst.CarModelConst.ENGINECAPACITY, this.engineCapacityEditText.getText().toString());

            String gear=((Gearbox)GearboxSpinner.getSelectedItem()).name();
            contentValues.put(TakeGoConst.CarModelConst.GEARBOX, gear);
            String seats=((Seats)numOfseatsSpinner.getSelectedItem()).name();
            contentValues.put(TakeGoConst.CarModelConst.NUMOFSEATS, seats);
            contentValues.put(TakeGoConst.CarModelConst.MODELNAME, this.modelNameEditText.getText().toString());
            new AsyncTask<Void, Void, String>() {
                @Override
                protected void onPostExecute(String idResult) {
                    super.onPostExecute(idResult);
                    if (idResult != " ")
                        Toast.makeText(getBaseContext(), "insert id: " + idResult, Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getBaseContext(), "this car model is exist", Toast.LENGTH_LONG).show();
                }

                @Override
                protected String doInBackground(Void... params)
                {
                    try {
                        return DBmanagerFactory.getManager().addcarmodel(contentValues);
                    } catch (Exception e) {
                        return " ";
                    }
                }
            }.execute();

        }
        catch (Exception e)
        {
        }
    }

}