package com.example.atos.myapplication.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;


import com.example.atos.myapplication.R;
import com.example.atos.myapplication.adapter.CustomExpandableListAdapter;
import com.example.atos.myapplication.controller.media.MediaImageSelectorActivity;
import com.example.atos.myapplication.controller.media.MediaListActivity;
import com.example.atos.myapplication.controller.media.MediaRecorderActivity;
import com.example.atos.myapplication.customclasses.CustomHeader;
import com.example.atos.myapplication.customclasses.ExpandableListDataPump;
import com.example.atos.myapplication.thirdparty.google.GoogleMapListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
        }
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }
        if (checkSelfPermission(Manifest.permission.CAPTURE_VIDEO_OUTPUT) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAPTURE_VIDEO_OUTPUT}, 1);
        }

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData(this);
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {




                if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.POST)){
                    Intent myIntent = new Intent(ExpandableListActivity.this, PostRequestActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);


                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.GET)){
                    Intent myIntent = new Intent(ExpandableListActivity.this, GetRequestActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);


                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.Download)){
                    Intent myIntent = new Intent(ExpandableListActivity.this, DownloadRequestActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);


                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.SQLiteDatabases)){
                    Intent myIntent = new Intent(ExpandableListActivity.this, SqliteActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);
                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) ==  getString(R.string.ListView_Text)){
                    Intent myIntent = new Intent(ExpandableListActivity.this, ListViewActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);
                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.CustomListView_Text)){
                    Intent myIntent = new Intent(ExpandableListActivity.this, CustomListViewActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);
                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.GoogleMap)){
                    Intent myIntent = new Intent(ExpandableListActivity.this, GoogleMapListActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);

                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.AlertDialog_Text)){
                    Intent myIntent = new Intent(ExpandableListActivity.this, AlertDialogActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);

                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.Dropdown_Text)){
                    Intent myIntent = new Intent(ExpandableListActivity.this, DropDownActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);

                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.drawPDF)){
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "Please grant the permission for WRITE EXTERNAL STORAGE from setting.." ,
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent myIntent = new Intent(ExpandableListActivity.this, PdfActivity.class);
                        ExpandableListActivity.this.startActivity(myIntent);
                    }

                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.recordVideo)){

                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                    }
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.CAPTURE_VIDEO_OUTPUT) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "Please grant the permission for camera,audio and video from settings.." ,
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent myIntent = new Intent(ExpandableListActivity.this, MediaRecorderActivity.class);
                        ExpandableListActivity.this.startActivity(myIntent);
                    }
                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.myVideos)){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "Please grant the permission for READ EXTERNAL STORAGE from setting.." ,
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent myIntent = new Intent(ExpandableListActivity.this, MediaListActivity.class);
                        ExpandableListActivity.this.startActivity(myIntent);
                    }

                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.camera)){
                    // Intent myIntent = new Intent(ExpandableListActivity.this, PdfActivity.class);

                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        //requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                        Toast.makeText(getApplicationContext(), "Please grant the permission for camera from setting.." ,
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent myIntent = new Intent(ExpandableListActivity.this, MediaImageSelectorActivity.class);
                        ExpandableListActivity.this.startActivity(myIntent);
                    }
                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.roundedImageView)){
                    // Intent myIntent = new Intent(ExpandableListActivity.this, PdfActivity.class);
                    Intent myIntent = new Intent(ExpandableListActivity.this, RoundedImageViewActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);
                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.borderImageView)){
                    // Intent myIntent = new Intent(ExpandableListActivity.this, PdfActivity.class);
                    Intent myIntent = new Intent(ExpandableListActivity.this, BorderedImageViewActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);
                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.nod)){
                    // Intent myIntent = new Intent(ExpandableListActivity.this, PdfActivity.class);
                    Intent myIntent = new Intent(ExpandableListActivity.this, DateModelActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);
                }else if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == getString(R.string.localnotification)){
                    // Intent myIntent = new Intent(ExpandableListActivity.this, PdfActivity.class);
                    Intent myIntent = new Intent(ExpandableListActivity.this, LocalNotificationActivity.class);
                    ExpandableListActivity.this.startActivity(myIntent);
                }

                /*
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                */
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        expandableListDetail = ExpandableListDataPump.getData(this);
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }

}
