package com.example.yealm.takego2.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yealm.takego2.model.backend.DB_manager;
import com.example.yealm.takego2.model.backend.DBmanagerFactory;
import com.example.yealm.takego2.R;


/** This class use to log-in and enter to this app  */
public class LoginActivity extends Activity implements View.OnClickListener {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);
                findViews();
                DB_manager manager= DBmanagerFactory.getManager();
//                manager.getallcostumers();
//                manager.getallbranchs();
//                manager.getallcars();
//                manager.getallcarmodels();
//                manager.getallInvitation();
                loadSharedPreferences();
        }

        private EditText passwordEditText;
        private EditText userNameEditText;
        private Button loginButton;
        private TextView memberLoginTextView;
        private ImageView userImageView;
        private ImageView passwordImageView;
        private Button forgotPasswordButton;
        private Button registButton;
        private Button guestButton;
        private Switch savePasswordSwitch;

        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2018-01-04 19:35:38 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         */
        private void findViews() {
                passwordEditText = (EditText)findViewById( R.id.passwordEditText );
                userNameEditText = (EditText)findViewById( R.id.userNameEditText );
                loginButton = (Button)findViewById( R.id.loginButton );
                memberLoginTextView = (TextView)findViewById( R.id.memberLoginTextView );
                userImageView = (ImageView)findViewById( R.id.userImageView );
                passwordImageView = (ImageView)findViewById( R.id.passwordImageView );
                forgotPasswordButton = (Button)findViewById( R.id.forgotPasswordButton );
                registButton = (Button)findViewById( R.id.registButton );
                guestButton = (Button)findViewById( R.id.guestButton );
                savePasswordSwitch=(Switch)findViewById( R.id.savePasswordSwitch);

                loginButton.setOnClickListener( this );
                forgotPasswordButton.setOnClickListener( this );
                registButton.setOnClickListener( this );
                guestButton.setOnClickListener( this );
        /*userNameEditText.addTextChangedListener(new TextWatcher();*/



        }

        /**
         * Handle button click events<br />
         * <br />
         * Auto-created on 2018-01-04 19:35:38 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         */
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
                if ( v == loginButton ) {
                        loadSharedPreferences();
                        checkSharedPreferences();
                } else if ( v == forgotPasswordButton ) {
                        // Handle clicks for forgotPasswordButton
                        Toast.makeText(this, "Your password is your ID :-) ", Toast.LENGTH_LONG).show();
                } else if ( v == registButton ) {
                        Intent intent = new Intent(LoginActivity.this,AddCostumer.class);
                        startActivity(intent);

                } else if ( v == guestButton ) {
                        enter(-1);
                }
         }

        /*This method is used to move to main navigation*/
        private void enter(int id){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("EXTRA_USER_ID", id);
                startActivity(intent);
        }

        /**This method is used to Checks if the username and password are in the system,
         If so - saves the user's choice to save the password
         And send to enter function.
         * @return void
         */
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void checkSharedPreferences(){
                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                final DB_manager manager = DBmanagerFactory.getManager();
                final String name=userNameEditText.getText().toString();
                final int id=Integer.valueOf(this.passwordEditText.getText().toString());

              /*  if(manager.checkUsernameAndPassword(name,id)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        boolean savePass = savePasswordSwitch.getShowText();
                        editor.putBoolean("savePass", savePass);
                        editor.commit();
                        enter(id);
                        return;
                }
                else //to case the password or the user name is incorrect
                        Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_LONG).show();*/

                new AsyncTask<Void, Void, Boolean>() {
                        @Override
                        protected void onPostExecute(Boolean aBoolean) {
                             if (aBoolean) {
                                     SharedPreferences.Editor editor = sharedPreferences.edit();
                                     boolean savePass = savePasswordSwitch.getShowText();
                                     editor.putBoolean("SavePass", savePass);
                                     editor.putString("NAME",name);
                                     editor.putInt("ID",id);
                                     editor.commit();
                                     enter(id);
                                     return;
                             }

                else //to case the password or the user name is incorrect
                        Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        protected Boolean doInBackground(Void... params) {
                                return manager.checkUsernameAndPassword(name,id);
                        }
                }.execute();


        }
        /**This method is used to Load the last login name and password entered and saved
         * in the system.
         * @return void
         */
        private void loadSharedPreferences() {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                Boolean SavePass = sharedPreferences.getBoolean("SavePass",false);
                if (! SavePass )
                        return;
                else {
                        if (sharedPreferences.contains("NAME")) {
                                userNameEditText.setText(sharedPreferences.getString("NAME", null));
                                Toast.makeText(this, "load name", Toast.LENGTH_SHORT).show();
                        }
                        if (sharedPreferences.contains("ID")) {
                                int id = sharedPreferences.getInt("ID", 0);
                                passwordEditText.setText(String.valueOf(id));
                                Toast.makeText(this, "load id", Toast.LENGTH_SHORT).show();
                        }
                }
        }

}

