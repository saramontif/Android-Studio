package com.example.yealm.takego1.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.yealm.takego1.R;

public class MainActivity extends Activity implements View.OnClickListener
{
    private Button addcostumerbutton;
    private Button addcarmodelbutton;
    private Button addcarbutton;
    private Button showallcarsmodelbutton;
    private Button showallcostumersbutton;
    private Button showallbranchbutton;
    private Button showallcarsbutton;

    private void findViews()
    {
        addcostumerbutton = (Button)findViewById( R.id.addcostumerbutton );
        addcarmodelbutton = (Button)findViewById( R.id.addcarmodelbutton );
        addcarbutton = (Button)findViewById( R.id.addcarbutton );
        showallcarsmodelbutton = (Button)findViewById( R.id.showallcarsmodelbutton);
        showallcostumersbutton = (Button)findViewById( R.id.showallcostumersbutton);
        showallbranchbutton = (Button)findViewById( R.id.showallbranchbutton);
        showallcarsbutton = (Button)findViewById( R.id.showallcarsbutton );

        addcostumerbutton.setOnClickListener(  this );
        addcarmodelbutton.setOnClickListener( this );
        addcarbutton.setOnClickListener( this );
        showallcarsmodelbutton.setOnClickListener( this );
        showallcostumersbutton.setOnClickListener( this );
        showallbranchbutton.setOnClickListener( this );
        showallcarsbutton.setOnClickListener( this );
    }

    private void addcostumer()
    {
        Intent intent = new Intent(this,AddCostumer.class);
        startActivity(intent);
    }
    private void addcarmodel()
    {
        Intent intent = new Intent(this,AddCarModel.class);
        startActivity(intent);
    }
    private void addcar()
    {
        Intent intent = new Intent(this,AddCar.class);
        startActivity(intent);
    }
    private void showallcarsmodel()
    {
       Intent intent = new Intent(this,ShowAllCarModel.class);
       startActivity(intent);
    }
    private void showallcostumers()
    {
        Intent intent = new Intent(this,ShowAllCostumers.class);
        startActivity(intent);
    }
    private void showallbranchs()
    {
        Intent intent = new Intent(this,ShowAllBranch.class);
        startActivity(intent);
    }
    private void showallcars()
    {
        Intent intent = new Intent(this,ShowAllCars.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    public void onClick(View v)
    {
        if ( v == addcostumerbutton )
        {
            addcostumer() ;
        }
        else if ( v == addcarmodelbutton )
        {
            addcarmodel();
        }
        else if ( v == addcarbutton )
        {
            addcar();
        }
        else if ( v == showallcarsmodelbutton )
        {
            showallcarsmodel();
        }
        else if ( v == showallcostumersbutton )
        {
            showallcostumers();
        }
        else if ( v == showallbranchbutton )
        {
            showallbranchs();
        }
        else if ( v == showallcarsbutton )
        {
            showallcars();
        }
    }


}
