package com.example.atos.myapplication.handler;

/**
 * Created by Atos on 18/07/17.
 */

import android.os.Environment;
import android.util.Log;

import com.example.atos.myapplication.interfaces.CallBackInterfaceDetail;
import com.example.atos.myapplication.interfaces.ConnectionInterface;
import com.example.atos.myapplication.network.RestAPIManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;


public class DownloadServiceCallHandler implements ConnectionInterface  {

    CallBackInterfaceDetail callBackObj;

    public DownloadServiceCallHandler(CallBackInterfaceDetail callBackObj) {
        this.callBackObj = callBackObj;
    }

    public void downloadServiceCall(String url,final File directory) {
        RestAPIManager con = new RestAPIManager(this);
        con.performDownloadingFromUrl(url, directory);
    }

    @Override
    public void successWithResponse(Object response) {


        callBackObj.callBack(response.toString());

    }
    @Override
    public void failedWithErrorMess(String message){
        callBackObj.failedWithErrorMessage(message);
    }
}

