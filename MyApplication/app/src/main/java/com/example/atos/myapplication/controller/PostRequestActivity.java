package com.example.atos.myapplication.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.atos.myapplication.R;
import com.example.atos.myapplication.customclasses.BaseActivity;
import com.example.atos.myapplication.global.Constants;
import com.example.atos.myapplication.customclasses.ProgressDialogue;
import com.example.atos.myapplication.handler.PostServiceCallHandler;
import com.example.atos.myapplication.interfaces.CallBackInterfaceDetail;

import org.json.JSONException;
import org.json.JSONObject;

public class PostRequestActivity extends BaseActivity implements CallBackInterfaceDetail {
    EditText responseEditTxt = null;
    ProgressDialogue obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);
        obj = new ProgressDialogue(this);

        Button btnAccess = (Button) findViewById(R.id.requestPostbtn);
        final EditText requestEditTxt = (EditText) findViewById(R.id.requestEditText);
        this.responseEditTxt  = (EditText) findViewById(R.id.responseEditText);
        btnAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.showProgressDialog();
                responseEditTxt.setVisibility(View.GONE);
                PostServiceCallHandler handler = new PostServiceCallHandler(PostRequestActivity.this);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("MobileNo", "9898989898");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                handler.postServiceCall(Constants.postSampleURL, jsonObject);

            }
        });

    }
    @Override
    public void callBack(String response) {
        responseEditTxt.setText(response);
        responseEditTxt.setVisibility(View.VISIBLE);
        obj.stopProgressDialog();

    }
    public void failedWithErrorMessage(String errorMessage){
        obj.stopProgressDialog();
        showToast(errorMessage);
    }

}
