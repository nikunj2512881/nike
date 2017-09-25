package com.example.atos.myapplication.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.customclasses.CustomHeader;
import com.example.atos.myapplication.global.DBHelper;
import com.example.atos.myapplication.model.User;

public class SaveInfoToDBActivity extends AppCompatActivity implements CustomHeader.OnHeaderElementClickListener{

    DBHelper db;
    String id;
    CustomHeader header;
    boolean mode = true;
    EditText etUsername;
    EditText etUserId;
    EditText etPassword;
    User useObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //setting header
        ActionBar var = getSupportActionBar();
        header=new CustomHeader(this,this,var,"arrowleft","checkwhite","");
        header.setHeader();

        db = SqliteActivity.getDb();

        etUsername = (EditText) findViewById(R.id.userNameEditText);
        etUserId = (EditText) findViewById(R.id.userIdEditText);
        etPassword = (EditText) findViewById(R.id.passwordEditText);

        mode = getIntent().getBooleanExtra("operation",false);

        if(mode) {
            useObject  = (User) getIntent().getSerializableExtra("userObject");
            etUsername.setText(useObject.getUserName());
            etUserId.setText(useObject.getUserId());
            etPassword.setText(useObject.getUserPassword());
        }
    }

    @Override
    public void onLeftButtonClicked() {
       super.onBackPressed();
    }

    @Override
    public void onRightButtonClicked() {

        if(mode){
                    id = String.valueOf(useObject.getId());
                    String userName = etUsername.getText().toString();
                    String userId = etUserId.getText().toString();
                    String password = etPassword.getText().toString();

                    if (!userName.isEmpty() && !userId.isEmpty() && !password.isEmpty()) {
                        User user = new User(Integer.parseInt(id),userName,userId,password);

                        if(db.updateRecord(user,"contacts"))
                        {
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("result","edit");
                            setResult(Activity.RESULT_OK,returnIntent);
                            finish();
                        }
                        else {
                            Toast.makeText(SaveInfoToDBActivity.this, "Not updated..", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(SaveInfoToDBActivity.this, "Please enter all fields..", Toast.LENGTH_LONG).show();
                    }

        } else {

                    String userName = etUsername.getText().toString();
                    String userId = etUserId.getText().toString();
                    String password = etPassword.getText().toString();

                    if (!userName.isEmpty() && !userId.isEmpty() && !password.isEmpty()) {
                        User user = new User(0,userName,userId,password);

                        if(db.insertRecord(user,"contacts"))
                        {
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("result","add");
                            setResult(Activity.RESULT_OK,returnIntent);
                            finish();
                        }
                        else {
                            Toast.makeText(SaveInfoToDBActivity.this, "Not added..", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(SaveInfoToDBActivity.this, "Please enter all fields..", Toast.LENGTH_LONG).show();
                    }
                }
    }

}
