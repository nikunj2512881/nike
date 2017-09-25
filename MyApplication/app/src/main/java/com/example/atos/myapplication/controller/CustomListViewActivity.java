package com.example.atos.myapplication.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.adapter.CustomListAdapter;
import com.example.atos.myapplication.model.User;

import java.util.ArrayList;

/**
 * Created by Atos on 18/08/17.
 */

public class CustomListViewActivity extends AppCompatActivity {

    CustomListAdapter adapter;
    ListView listView;
    ArrayList<User> list = new ArrayList<User> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list_view);

        User user = new User(1,"pooja","A435353","abc");
        User user1 = new User(2,"Soniya","A364654","pqr");

        list.add(user);
        list.add(user1);

        adapter = new CustomListAdapter(this, list);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
