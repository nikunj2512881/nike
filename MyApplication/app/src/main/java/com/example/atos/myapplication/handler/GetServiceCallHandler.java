package com.example.atos.myapplication.handler;

/**
 * Created by Atos on 18/07/17.
 */
import org.json.*;
import android.util.*;

import com.example.atos.myapplication.controller.DetailActivity;
import com.example.atos.myapplication.interfaces.CallBackInterface;
import com.example.atos.myapplication.interfaces.ConnectionInterface;
import com.example.atos.myapplication.model.User;
import com.example.atos.myapplication.network.RestAPIManager;


public class GetServiceCallHandler implements ConnectionInterface {

    CallBackInterface callBackObj;

    public GetServiceCallHandler(CallBackInterface callBackObj) {
        this.callBackObj = callBackObj;
    }

    public void loginService(String url) {
        RestAPIManager con = new RestAPIManager(this);
        con.callGetService(url);
    }

    @Override
    public void successWithResponse(Object response) {

        System.out.println(response instanceof String);
        if (response instanceof String) {
            callBackObj.failedWithErrorMessage((String) response);
        }else {
        try {
            JSONObject jsonObj = new JSONObject(response.toString());


            if (! jsonObj.has("data")){
                callBackObj.failedWithErrorMessage(jsonObj.getString("message"));

            }else{
                JSONObject c = jsonObj.getJSONObject("data");
                int id = Integer.parseInt(c.getString("id"));
                String userId = c.getString("userId");
                String userName = c.getString("userName");
                String userPassword = c.getString("userPassword");
                User userObj = new User(id,userId,userName,userPassword);
                Log.d("mytag","got response as\n id:"+userObj.getId()+"\n userId"+userObj.getUserId()+"\n userName"+userObj.getUserName()+"\n password"+userObj.getUserPassword());
                callBackObj.callBack(response.toString());


            }




        }
        catch (Exception e){

        }}

    }
    @Override
    public void failedWithErrorMess(String message){
        callBackObj.failedWithErrorMessage(message);
    }
}

