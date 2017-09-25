package com.example.atos.myapplication.global;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkConnectivityBroadcasting extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.



        if (this.isOnline(context)) {
            // Do something
            Toast.makeText(context, "Connected to Internet", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Disconnected from Internet", Toast.LENGTH_LONG).show();

        }



    }


    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }
}

