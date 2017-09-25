package com.example.atos.myapplication.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.atos.myapplication.R;


/**
 * Created by Atos on 06/09/17.
 */

public class RoundedImageViewActivity extends Activity {

    private ImageView imageViewRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded_imageview);
        imageViewRound=(ImageView)findViewById(R.id.imageView_round);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.newark_prudential_sunny);

        imageViewRound.setImageBitmap(icon);
    }
}
