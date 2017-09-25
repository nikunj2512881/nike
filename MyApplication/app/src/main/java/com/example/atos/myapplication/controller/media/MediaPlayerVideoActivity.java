package com.example.atos.myapplication.controller.media;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.customclasses.CameraHelper;

public class MediaPlayerVideoActivity extends AppCompatActivity {

    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        VideoView videoView =(VideoView)findViewById(R.id.videoView1);

        Bundle extras = getIntent().getExtras();
        fileName= extras.getString("FILE_NAME");

        //Creating MediaController
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        //String path = "android.resource://" + getPackageName() + "/" + R.raw.sample;
            String path = CameraHelper.getPathDir(CameraHelper.MEDIA_TYPE_VIDEO).getPath() + "/" + fileName;

        //specify the location of media file
        Uri uri=Uri.parse(path);

        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();



    }
}
