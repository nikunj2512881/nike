package com.example.atos.myapplication.handler;

/**
 * Created by Atos on 18/07/17.
 */

import android.util.Log;

import com.example.atos.myapplication.interfaces.CallBackInterface;
import com.example.atos.myapplication.interfaces.CallBackInterfaceDetail;
import com.example.atos.myapplication.interfaces.ConnectionInterface;
import com.example.atos.myapplication.model.User;
import com.example.atos.myapplication.network.RestAPIManager;

import org.json.JSONArray;
import org.json.JSONObject;


public class PostServiceCallHandler implements ConnectionInterface  {

    CallBackInterfaceDetail callBackObj;

    public PostServiceCallHandler(CallBackInterfaceDetail callBackObj) {
        this.callBackObj = callBackObj;
    }

    public void postServiceCall(String url, JSONObject postDataParams) {
        RestAPIManager con = new RestAPIManager(this);
        con.callPostService(url,postDataParams);
    }

    @Override
    public void successWithResponse(Object response) {

        try {
            JSONObject jsonObj = new JSONObject(response.toString());
            String jsonString = jsonObj.getString("Get_UserStatusResult");
            JSONArray jsonArry = new JSONArray(jsonString);
            JSONObject jsonObjF = (JSONObject)jsonArry.get(0);
            JSONArray jsonObjarry =  jsonObjF.getJSONArray("NAMES");
            callBackObj.callBack(jsonObjarry.toString());
            Log.d(jsonObjarry.toString(),jsonString);



        }
        catch (Exception e){}


    }
    @Override
    public void failedWithErrorMess(String message){
        callBackObj.failedWithErrorMessage(message);
    }
}

