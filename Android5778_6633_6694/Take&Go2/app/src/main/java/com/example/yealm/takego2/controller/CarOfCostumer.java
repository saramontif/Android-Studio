package com.example.yealm.takego2.controller;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yealm.takego2.R;
import com.example.yealm.takego2.model.backend.DB_manager;
import com.example.yealm.takego2.model.backend.DBmanagerFactory;
import com.example.yealm.takego2.model.backend.TakeGoConst;
import com.example.yealm.takego2.model.entities.Car;
import com.example.yealm.takego2.model.entities.Invitation;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarOfCostumer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarOfCostumer#newInstance} factory method to
 * create an instance of this fragment.
 */
//this class show the order and give option to close her.
public class CarOfCostumer extends Fragment implements View.OnClickListener{
    String currentCustomer="";
    final DB_manager manager=DBmanagerFactory.getManager();
    private Button CloseOrderButton;
    private TextView carModelTextView;
    private TextView numOfkilometerTextView;
    private TextView numOfCarTextView;
    private TextView branchCarTextView;
    private CheckBox checkBoxFuel;
    private EditText numkilomEdit;
    private Car car;
    private View view;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CarOfCostumer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarOfCostumer.
     */
    public static CarOfCostumer newInstance(String param1, String param2) {
        CarOfCostumer fragment = new CarOfCostumer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        currentCustomer=getArguments().getString("current customer");
        view = inflater.inflate(R.layout.fragment_car_of_costumer, container, false);
        updateViewByCustomer();
        return view;
    }
//show the detals of the ordew
    private void updateViewByCustomer() {
        Bundle bundle = getArguments();
        String customer_id = bundle.getString("current customer");

        new AsyncTask<String, Void, Car>() {
            @Override
            protected void onPostExecute(Car car1) {
                car = car1;
                if(car==null ||car.getNumOfCar() ==null)
                    Toast.makeText(getContext(), "Not found order", Toast.LENGTH_LONG).show();
                if(car != null) {
                    CloseOrderButton = (Button) view.findViewById(R.id.CloseOrderButton);
                    CloseOrderButton.setOnClickListener(CarOfCostumer.this);

                    carModelTextView = (TextView) view.findViewById(R.id.carModelTextView);
                    numOfkilometerTextView = (TextView) view.findViewById(R.id.numOfkilometerTextView);
                    numOfCarTextView = (TextView) view.findViewById(R.id.numOfCarTextView);
                    branchCarTextView = (TextView) view.findViewById(R.id.branchCarTextView);
                    checkBoxFuel=(CheckBox)view.findViewById(R.id.checkBoxFuel);
                    numkilomEdit=(EditText)view.findViewById(R.id.numkilomEdit);

                    carModelTextView.setText(car.getCarModel());
                    numOfkilometerTextView.setText(String.valueOf(car.getNumOfkilometer()));
                    numOfCarTextView.setText(car.getNumOfCar());
                    branchCarTextView.setText(car.getBranchCar());
                }
            }

            @Override
            protected Car doInBackground(String... params) {
                return DBmanagerFactory.getManager().getCarforCostumer(params[0]);
            }
        }.execute(customer_id);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


//show the car of the order
    public void foundCar(){
        final Car[] car1 = {new Car()};

        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected void onPostExecute(Integer integer) {
//                super.onPostExecute(integer);
//                int i=integer;
                car=car1[1];
//                if(car.getNumOfCar() ==null)
//                    Toast.makeText(getContext(), "Not found order", Toast.LENGTH_LONG).show();
            }

            @Override
            protected Integer doInBackground(Void... params)
            {
                car1[0] =DBmanagerFactory.getManager().getCarforCostumer(currentCustomer);
                return 1;

            }
        }.execute();
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v)//to close the order
    {
        if (v == CloseOrderButton) {
            final Invitation[] invitation = new Invitation[1];
            final Boolean doFuel = checkBoxFuel.isChecked();
            final float endKm=Float.parseFloat(this.numkilomEdit.getText().toString());
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected void onPostExecute(Void aVoid) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    //Displays a notification to the customer that the order has been closed with the final payment
                    builder.setTitle("order closed");
                    builder.setMessage("Final payment: " + invitation[0].getSumToPay() + " Thank You for choosing Take&Go");
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();

                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.remove(CarOfCostumer.this);
                    ft.commit();
                }

                @Override
                protected Void doInBackground(Void... params)
                {
                    invitation[0] = DBmanagerFactory.getManager().getInvitation(currentCustomer);
                    invitation[0].setDoFuel(doFuel);
                    invitation[0].setEndKM(endKm);
                    invitation[0].setRentEnd(new Date());

                    ContentValues contentValues = new ContentValues();
                    contentValues = TakeGoConst.InvitationToContentValues(invitation[0]);
                    DBmanagerFactory.getManager().closeInvitation(contentValues);
                    DBmanagerFactory.getManager().TotalPay(invitation[0]);

                    return null;
                }


            }.execute();
        }
}

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
