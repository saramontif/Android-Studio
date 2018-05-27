package com.example.yealm.takego2.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yealm.takego2.R;
import com.example.yealm.takego2.model.backend.DBmanagerFactory;
import com.example.yealm.takego2.model.backend.TakeGoConst;
//this class add new costumer to the company
public class AddCostumer extends Activity implements View.OnClickListener
{

    private EditText lastNameEditText;
    private EditText firstNameEditText;
    private EditText IDEditText;
    private EditText phoneNumberEditText;
    private EditText emailEditText;
    private EditText creditCardNumberEditText;
    private Button addCostumerButton;

    private void findViews()
    {
        lastNameEditText = (EditText)findViewById( R.id.lastNameEditText );
        firstNameEditText = (EditText)findViewById( R.id.firstNameEditText );
        IDEditText = (EditText)findViewById( R.id.IDEditText );
        phoneNumberEditText = (EditText)findViewById( R.id.phoneNumberEditText );
        emailEditText = (EditText)findViewById( R.id.emailEditText );
        creditCardNumberEditText = (EditText)findViewById( R.id.creditCardNumberEditText );
        addCostumerButton = (Button)findViewById( R.id.addCostumerButton );
        addCostumerButton.setOnClickListener( this );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_costumer);
        findViews();
    }
    @Override
    public void onClick(View v)
    {
        if ( v == addCostumerButton )
        {
            addCostumer();
        }
        finish();
    }
    private void addCostumer() {

        final ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(TakeGoConst.CostumerConst.ID, this.IDEditText.getText().toString());
            contentValues.put(TakeGoConst.CostumerConst.LASTNAME, this.lastNameEditText.getText().toString());
            contentValues.put(TakeGoConst.CostumerConst.FIRSTNAME, this.firstNameEditText.getText().toString());
            contentValues.put(TakeGoConst.CostumerConst.PHONENUMBER, this.phoneNumberEditText.getText().toString());
            contentValues.put(TakeGoConst.CostumerConst.EMAIL, this.emailEditText.getText().toString());
            contentValues.put(TakeGoConst.CostumerConst.CREDITCARNUMBER, this.creditCardNumberEditText.getText().toString());
            new AsyncTask<Void, Void, String>() {
                @Override
                protected void onPostExecute(String idResult) {
                    super.onPostExecute(idResult);
                    if (idResult != " ")
                        Toast.makeText(getBaseContext(), "insert id: " + idResult, Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getBaseContext(), "error this costumer is exist", Toast.LENGTH_LONG).show();
                }

                @Override
                protected String doInBackground(Void... params)
                {
                    try {
                        return DBmanagerFactory.getManager().addcostumer(contentValues);
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