package com.example.atos.myapplication.thirdparty.google;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.atos.myapplication.R;

public class GoogleMapListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        String[] strArry = new String[]{"Current Location","Markers","Route"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, strArry);

        //Log.d("tag","count :"+mobileArray.length);
        ListView listView = (ListView) findViewById(R.id.list_example);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Intent myIntent = new Intent(GoogleMapListActivity.this, MyLocationDemoActivity.class);
                    GoogleMapListActivity.this.startActivity(myIntent);

                }else if (i == 1){
                    Intent myIntent = new Intent(GoogleMapListActivity.this, GoogleMapMarkerFragment.class);
                    GoogleMapListActivity.this.startActivity(myIntent);

                }else if (i == 2){
                    Intent myIntent = new Intent(GoogleMapListActivity.this, MapsActivity.class);
                    GoogleMapListActivity.this.startActivity(myIntent);

                }

            }


        });



        listView.setAdapter(adapter);
    }
}
