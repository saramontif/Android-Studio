package com.example.yealm.takego2.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yealm.takego2.R;
import com.example.yealm.takego2.model.backend.DBmanagerFactory;
import com.example.yealm.takego2.model.backend.DB_manager;
import com.example.yealm.takego2.model.entities.Branch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10/01/2018.
 */
// this class show all the list of the branches
public class BranchesFragment extends Fragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener
{
    SearchView searchView;

    MyexpandableListAdepter adapterExList;
    private ExpandableListView expandableListView;
    DB_manager manager= DBmanagerFactory.getManager();
   // int currentCustomer=-1;
   String currentCustomer="";

    public BranchesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        try
        {
            if (!isNetworkConnected())
                throw new Exception("The application must have internet connection");
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkConnected() {//check if its connect
        ConnectivityManager cm = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)//show the branch
    {
        final View rootView = inflater.inflate(R.layout.fragment_branches, container, false);
        final Context context = getActivity().getApplicationContext();
        // Inflate the layout for this fragment
        currentCustomer=getArguments().getString("current customer");
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.BranchesList);


        new AsyncTask<Void, Void,Integer>() {
            @Override
            protected void onPostExecute(Integer a)
            {
                searchView = (SearchView) rootView.findViewById(R.id.search_branch);

                searchView.setOnQueryTextListener(BranchesFragment.this);
                searchView.setOnCloseListener(BranchesFragment.this);
                expandableListView.setAdapter(adapterExList);

            }

            @Override
            protected Integer doInBackground(Void... params)
            {
        adapterExList = new MyexpandableListAdepter(expandableListView, "Branches",BranchesFragment.this, currentCustomer);
                return 0;
            }
        }.execute();
        return rootView;


    }


    @Override
    public boolean onQueryTextChange(String newText)
    {

        adapterExList.getFilter().filter(newText);
        expandableListView.setAdapter(adapterExList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }

    @Override
    public boolean onClose()
    {
        adapterExList = new MyexpandableListAdepter(expandableListView, "Branches", this, currentCustomer);
        expandableListView.setAdapter(adapterExList);
        return false;
    }

}
