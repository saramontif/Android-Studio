package com.example.yealm.takego2.model.backend;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.yealm.takego2.R;
import com.example.yealm.takego2.model.entities.Car;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yealm on 08/03/2018.
 */

public class AdapterCar extends ArrayAdapter<Car> {

    public AdapterCar(@NonNull Context context, @LayoutRes int resource, @NonNull List<Car> objects) {
        super(context, resource, objects);
    }

    public AdapterCar(Context context, ArrayList<Car> cars) {
            super(context, 0, cars);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Car car = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_car, parent, false);
            }

            // Lookup view for data population
            TextView branchcar = (TextView) convertView.findViewById(R.id.branchcar);
            TextView carModel = (TextView) convertView.findViewById(R.id.carModel);
            TextView numOfkilometer = (TextView) convertView.findViewById(R.id.numOfkilometer);
            TextView carNumber = (TextView) convertView.findViewById(R.id.carNumber);
            Button buttonRent=(Button) convertView.findViewById(R.id.buttonRent);



            // Populate the data into the template view using the data object
            branchcar.setText(car.getBranchCar());
            carModel.setText(car.getCarModel());
            numOfkilometer.setText(Float.toString(car.getNumOfkilometer()));
            carNumber.setText(car.getNumOfCar());


            // Return the completed view to render on screen
            return convertView;
        }

}
