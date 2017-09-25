package com.example.atos.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.customclasses.DateModel;
import com.example.atos.myapplication.customclasses.GeneralDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateModelActivity extends AppCompatActivity {

    EditText startDateET;
    EditText endDateET;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_model);

        startDateET = (EditText) findViewById(R.id.startDate);
        endDateET = (EditText) findViewById(R.id.endDate);
        button = (Button) findViewById(R.id.button);

        //Date to String conversion
        String dateStr = DateModel.getStringFromDate(Calendar.getInstance().getTime());

        //String to Date conversion
        Date date = DateModel.getDateFromString("2010-10-15T09:27:37Z");

        //Calculate Days
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String oldFormat = "dd/MM/yyyy";
                SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
                Date startDate = null;
                Date endDate = null;

                try {
                    startDate = dateFormat.parse(startDateET.getText().toString());
                    endDate = dateFormat.parse(endDateET.getText().toString());

                } catch (java.text.ParseException e) {
                    Toast.makeText(getApplicationContext(), "Please enter valid date format.." ,
                            Toast.LENGTH_LONG).show();
                }

                if(startDate == null || endDate == null || startDate.equals("") || endDate.equals("")){
                    /*Toast.makeText(getApplicationContext(), "Fields can not be empty.." ,
                            Toast.LENGTH_LONG).show();*/
                }
                else {
                    String daysMessage = DateModel.getCountOfDays(startDate, endDate);
                    Toast.makeText(getApplicationContext(), daysMessage ,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
