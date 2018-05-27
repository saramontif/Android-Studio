package com.example.yealm.takego2.model.backend;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class FreeCarService extends Service {

    final String TAG = "myservice";
    boolean isRun=true;
//    static boolean ServiceRun;// = false;
//
//    static {
//        ServiceRun = false;
//    }
    public FreeCarService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
       // Log.d(TAG, "onCreate");
        final boolean[] isClosed = {true};

       // Toast.makeText(this, "start service", Toast.LENGTH_SHORT).show();
        Thread t = new Thread() {
            @Override
            public void run() {

                while (isRun) {
                    try {
                        Thread.sleep(10000);
                       // Log.d(TAG, "thread run ..");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    new AsyncTask<String, Void, Boolean>() {
            @Override
            protected void onPostExecute(Boolean Result)
            {
                isClosed[0] =Result;
            }

            @Override
            protected Boolean doInBackground(String... params)
            {
                 return DBmanagerFactory.getManager().checkCloseInvitation();
            }
        }.execute();

                    if (isClosed[0]) {
                       // Log.d(TAG, "isUpdatet run ..");
                        Intent intent1 = new Intent("com.yealm.takego2.UPDATE");
                        FreeCarService.this.sendBroadcast(intent1);
                        //sendBroadcast(intent);
                    }

                }
            }
        };

        t.start();


    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public void onDestroy() {
        isRun = false;
        super.onDestroy();
    }
}
