package com.example.atos.myapplication.controller.media;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.customclasses.CameraHelper;

import java.io.File;
import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


/**
 * Created by Atos on 05/09/17.
 */

public class MediaListActivity extends AppCompatActivity {


    ArrayList<String> mediaList;
    //String[] mOutputFile;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);
        listView = (ListView) findViewById(R.id.mediaListview);

        //String[] strArry = new String[]{"Android","iPhone","WindowsMobile","WebOS"};

        mediaList = CameraHelper.getOutputMediaFileNames(CameraHelper.MEDIA_TYPE_VIDEO);
        if (mediaList != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, mediaList);
            listView.setAdapter(adapter);
        }
        else {

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MediaListActivity.this, MediaPlayerVideoActivity.class);
                String fileName = mediaList.get(position);
                intent.putExtra("FILE_NAME", fileName);
                startActivity(intent);
            }
        });


    }
}
