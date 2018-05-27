package com.example.yealm.takego2.model.backend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class FreeCarReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        Log.d("my service reload cars" , "onReceive");

        Toast.makeText(context,intent.getAction(),Toast.LENGTH_LONG).show();

    }
}
