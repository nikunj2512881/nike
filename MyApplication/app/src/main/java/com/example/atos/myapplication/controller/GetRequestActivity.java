package com.example.atos.myapplication.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.customclasses.BaseActivity;
import com.example.atos.myapplication.global.Constants;
import com.example.atos.myapplication.customclasses.ProgressDialogue;
import com.example.atos.myapplication.global.Utils;
import com.example.atos.myapplication.handler.GetServiceCallHandler;
import com.example.atos.myapplication.interfaces.CallBackInterface;

public class GetRequestActivity extends BaseActivity implements CallBackInterface {
    EditText responseEditTxt = null;
    ProgressDialogue obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_request);
        obj = new ProgressDialogue(this);

        Button btnAccess = (Button) findViewById(R.id.requestPostbtn);
        final EditText requestEditTxt = (EditText) findViewById(R.id.requestEditText);
        this.responseEditTxt  = (EditText) findViewById(R.id.responseEditText);
        btnAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.showProgressDialog();
                responseEditTxt.setVisibility(View.GONE);
                if (Utils.isNetworkConnected(GetRequestActivity.this)) {
                    GetServiceCallHandler handler = new GetServiceCallHandler(GetRequestActivity.this);
                        handler.loginService(Constants.getLoginSampleURL + "/" + "test" + "/" + "test");
                    } else {
                        showToast("Network not available!!!");
                    }
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
