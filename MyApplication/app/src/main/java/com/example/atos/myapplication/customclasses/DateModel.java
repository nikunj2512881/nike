package com.example.atos.myapplication.customclasses;

import android.text.StaticLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Atos on 07/09/17.
 */

public class DateModel {

    public static String getStringFromDate(Date date){

        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String dateStr = df.format(date);
        return dateStr;
    }
    public static Date getDateFromString(String dateStr){
        //String dtStart = "2010-10-15T09:27:37Z";
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
             date = format.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
    public static String getCountOfDays(Date dateOne, Date dateTwo) {

        long timeOne = dateOne.getTime();
        long timeTwo = dateTwo.getTime();
        long oneDay = 1000 * 60 * 60 * 24;
        long delta = (timeTwo - timeOne) / oneDay;

        if (delta >= 0) {
            return "dateTwo is " + delta + " days after dateOne";
        }
        else {
            delta *= -1;
            return "dateTwo is " + delta + " days before dateOne";
        }
    }

}
