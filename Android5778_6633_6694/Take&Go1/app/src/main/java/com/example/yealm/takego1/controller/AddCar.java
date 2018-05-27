package com.example.yealm.takego1.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yealm.takego1.R;
import com.example.yealm.takego1.model.backend.DBmanagerFactory;
import com.example.yealm.takego1.model.backend.TakeGoConst;
import com.example.yealm.takego1.model.entities.CarModel;
import com.example.yealm.takego1.model.entities.Gearbox;
import com.example.yealm.takego1.model.entities.Seats;

import java.util.ArrayList;
import java.util.List;

public class AddCar extends Activity  implements View.OnClickListener
{

    private EditText branchCarEditText;
    private Spinner carModelSpinner;
    private EditText numOfkilometerEditText;
    private EditText numOfCarEditText;
    private Button addCarButton;



    private void findViews() {
        new AsyncTask<Void, Void, List<CarModel>>() {
            @Override
            protected void onPostExecute(final List<CarModel> Result) {
                branchCarEditText = (EditText) findViewById(R.id.branchCarEditText);

                carModelSpinner = (Spinner)findViewById(R.id.carModelSpinner);

                numOfkilometerEditText = (EditText)findViewById(R.id.numOfkilometerEditText);

                numOfCarEditText = (EditText) findViewById(R.id.numOfCarEditText);

                addCarButton = (Button)findViewById(R.id.addCarButton);
                // carModelSpinner.setAdapter(new ArrayAdapter<CarModel>(this,android.R.layout.simple_expandable_list_item_1,DBmanagerFactory.getManager().getallcarmodels()));
                carModelSpinner.setAdapter(new ArrayAdapter<CarModel>(AddCar.this, R.layout.showcarmodel, Result)
                {

                    @Override
                    public View getDropDownView(int position, View convertView, ViewGroup parent)
                    {
                        return getCustomView(position, convertView, parent);
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent)
                    {
                        return getCustomView(position, convertView, parent);
                    }

                    View getCustomView(final int position, View convertView, ViewGroup parent)
                    {
                        if (convertView == null) {
                            convertView = View.inflate(AddCar.this, R.layout.showcarmodel, null);
                        }
                        TextView companyNameTextView = (TextView) convertView.findViewById(R.id.companyNameTextView);
                        TextView modelNameTextView = (TextView) convertView.findViewById(R.id.modelNameTextView);
                        companyNameTextView.setText(Result.get(position).getCompanyName());
                        modelNameTextView.setText(Result.get(position).getModelName());
                        return convertView;
                    }
                });
                addCarButton.setOnClickListener(AddCar.this);
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        findViews();
    }

    @Override
    public void onClick(View v)
    {
        if ( v == addCarButton )
        {
            addCar();
        }
        finish();
    }

    private void addCar()
    {

        final ContentValues contentValues = new ContentValues();
        try
        {
            contentValues.put(TakeGoConst.CarConst.BRANCHCAR, this.branchCarEditText.getText().toString());
            String carModel=((CarModel)carModelSpinner.getSelectedItem()).getCodeModel();
            contentValues.put(TakeGoConst.CarConst.CARMODEL,carModel);
            contentValues.put(TakeGoConst.CarConst.NUMOFCAR, this.numOfCarEditText.getText().toString());
            contentValues.put(TakeGoConst.CarConst.NUMOFKILOMETER, this.numOfCarEditText.getText().toString());

            new AsyncTask<Void, Void, String>() {
                @Override
                protected void onPostExecute(String idResult)
                {
                    super.onPostExecute(idResult);
                    if (idResult != " ")
                        Toast.makeText(getBaseContext(), "insert id: " + idResult, Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getBaseContext(), "this car is exist", Toast.LENGTH_LONG).show();
                }

                @Override
                protected String doInBackground(Void... params)
                {
                    try {
                        return DBmanagerFactory.getManager().addcar(contentValues);

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







