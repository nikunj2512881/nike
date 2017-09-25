package com.example.atos.myapplication.controller;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.atos.myapplication.customclasses.BaseActivity;
import com.example.atos.myapplication.customclasses.CustomHeader;
import com.example.atos.myapplication.customclasses.ProgressDialogue;
import com.example.atos.myapplication.global.Constants;
import com.example.atos.myapplication.global.PermissionUtils;
import com.example.atos.myapplication.global.Utils;
import com.example.atos.myapplication.handler.GetServiceCallHandler;
import com.example.atos.myapplication.interfaces.CallBackInterface;
import com.example.atos.myapplication.R;

import java.util.Locale;

public class MainActivity extends BaseActivity implements CallBackInterface ,CustomHeader.OnHeaderElementClickListener{

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    Button clickButton;
    ProgressDialogue obj;
    CustomHeader header;
    private String userChoosenTask;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.clickButton = (Button) findViewById(R.id.buttonlogin);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   PermissionUtils.requestPermissionFromActivity(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        Log.d("tag","jenkins test");


        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        //setting header
        header=new CustomHeader(this,this,getSupportActionBar(),"lines","settings","Home");

        header.setHeader();

        obj = new ProgressDialogue(this);

        Button btnLogin = (Button) findViewById(R.id.buttonlogin);
        final EditText etUserId = (EditText) findViewById(R.id.edittextemail);

        etUserId.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {

                for(int i = s.length(); i > 0; i--) {

                    if(s.subSequence(i-1, i).toString().equals("\n"))
                        s.replace(i-1, i, "");
                }

                String myTextString = s.toString();
            }
        });


        final EditText etPassword = (EditText) findViewById(R.id.edittestpassword);
        etPassword.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {

                for(int i = s.length(); i > 0; i--) {

                    if(s.subSequence(i-1, i).toString().equals("\n"))
                        s.replace(i-1, i, "");
                }

                String myTextString = s.toString();
            }
        });




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent myIntent = new Intent(MainActivity.this, ExpandableListActivity.class);
                MainActivity.this.startActivity(myIntent);*/

                obj.showProgressDialog();
                String user = etUserId.getText().toString().trim();
                String pw = etPassword.getText().toString().trim();

                if (!user.isEmpty() && !pw.isEmpty()) {
                    if (Utils.isNetworkConnected(MainActivity.this)) {
                        GetServiceCallHandler handler = new GetServiceCallHandler(MainActivity.this);
                        handler.loginService(Constants.getLoginSampleURL + "/" + user + "/" + pw);
                    } else {

                        showToast("Network not available!!!");
                        obj.stopProgressDialog();
                    }
                } else {
                    showToast("Username/Password should not be empty!");
                    obj.stopProgressDialog();
                }
                etUserId.setText("");
                etPassword.setText("");
            }

        });
    }
    public void callBack (String response) {
        obj.stopProgressDialog();
        Intent myIntent = new Intent(MainActivity.this, ExpandableListActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
    public void failedWithErrorMessage(String errorMessage){
        obj.stopProgressDialog();
        showToast(errorMessage);
    }
    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.clickButton = (Button) findViewById(R.id.buttonlogin);

        setContentView(R.layout.activity_main);


        //setting header
        header=new CustomHeader(this,this,getSupportActionBar(),"lines","settings","Home");
        header.setHeader();

        obj = new ProgressDialogue(this);

        Button btnLogin = (Button) findViewById(R.id.buttonlogin);
        final EditText etUserId = (EditText) findViewById(R.id.edittextemail);
        final EditText etPassword = (EditText) findViewById(R.id.edittestpassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ExpandableListActivity.class);
                MainActivity.this.startActivity(myIntent);

                /*
                obj.showProgressDialog();
                String user = etUserId.getText().toString().trim();
                String pw = etPassword.getText().toString().trim();
                user = "test";
                pw = "test";
                if (!user.isEmpty() && !pw.isEmpty()) {
                    if (Utils.isNetworkConnected(MainActivity.this)) {
                        GetServiceCallHandler handler = new GetServiceCallHandler(MainActivity.this);
                        handler.loginService(Constants.getLoginSampleURL + "/" + user + "/" + pw);
                    } else {

                        showToast("Network not available!!!");
                        obj.stopProgressDialog();
                    }
                } else {
                    showToast("Username/Password should not be empty!");
                    obj.stopProgressDialog();
                }
                */
            }

        });
        etUserId.setHint(getResources().getString(R.string.email));
        etPassword.setHint(getResources().getString(R.string.password));
    }

    public void onLeftButtonClicked() {

    }

    public void onRightButtonClicked() {

        final CharSequence[] items = { "Hindi", "English",
                "Cancel" };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Select Language");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Hindi")) {
                    userChoosenTask ="Hindi";
                    String languageToLoad  = "hi"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getApplicationContext().getResources().updateConfiguration(config,
                            getApplicationContext().getResources().getDisplayMetrics());

                    onConfigurationChanged(config);
                    Toast.makeText(getApplicationContext(), "You have choosen Hindi..",
                            Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else if (items[item].equals("English")) {
                    userChoosenTask ="English";
                    String languageToLoad  = "en"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getApplicationContext().getResources().updateConfiguration(config,
                            getApplicationContext().getResources().getDisplayMetrics());
                    onConfigurationChanged(config);
                    Toast.makeText(getApplicationContext(), "You have choosen English..",
                            Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }


    public static class GoogleMapItemsListActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_google_map_items_list);
        }
    }

}