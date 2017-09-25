package com.example.atos.myapplication.controller;

import android.os.Bundle;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.customclasses.BaseActivity;
import com.example.atos.myapplication.global.Constants;
import com.example.atos.myapplication.customclasses.ProgressDialogue;
import com.example.atos.myapplication.handler.PostServiceCallHandler;
import com.example.atos.myapplication.interfaces.CallBackInterfaceDetail;

import org.json.JSONException;
import org.json.JSONObject;


public class DetailActivity extends BaseActivity implements CallBackInterfaceDetail {

    ProgressDialogue obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        obj = new ProgressDialogue(this);

        PostServiceCallHandler handler = new PostServiceCallHandler(DetailActivity.this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("MobileNo", "9898989898");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        handler.postServiceCall(Constants.postSampleURL,jsonObject);


    }


    @Override

    public void callBack (String response) {
        obj.stopProgressDialog();

    }
    public void failedWithErrorMessage(String errorMessage){
        obj.stopProgressDialog();
        showToast(errorMessage);
    }
}
