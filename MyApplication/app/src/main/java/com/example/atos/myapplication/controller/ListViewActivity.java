package com.example.atos.myapplication.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.atos.myapplication.R;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        String[] strArry = new String[]{"Android","iPhone","WindowsMobile","WebOS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, strArry);

        //Log.d("tag","count :"+mobileArray.length);
        ListView listView = (ListView) findViewById(R.id.list_example);
        listView.setAdapter(adapter);
    }
}
