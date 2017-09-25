package com.example.atos.myapplication.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.customclasses.GeneralDialogFragment;

/**
 * Created by Atos on 22/08/17.
 */

public class AlertDialogActivity extends
        FragmentActivity implements GeneralDialogFragment.OnDialogFragmentClickListener {

    Button alertdfragbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_alert);

        // Locate the button in activity_main.xml
        alertdfragbutton = (Button) findViewById(R.id.alertdfragbutton);

        // Capture button clicks
        alertdfragbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                GeneralDialogFragment generalDialogFragment =
                        GeneralDialogFragment.newInstance("ATOS", "Android Common Components");
                generalDialogFragment.show(getFragmentManager(),"dialog");
            }
        });
    }
    @Override
    public void onOkClicked(GeneralDialogFragment dialog) {
        // do your stuff
    }

    @Override
    public void onCancelClicked(GeneralDialogFragment dialog) {
        // do your stuff
    }
}

