package com.example.atos.myapplication.customclasses;

import android.content.Context;
import android.content.res.Resources;

import com.example.atos.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.provider.Settings.Global.getString;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData(Context context) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> restApiLlist = new ArrayList<String>();
        String POST = context.getString(R.string.POST);
        String GET = context.getString(R.string.GET);
        String Download = context.getString(R.string.Download);
        String Upload = context.getString(R.string.Upload);
        restApiLlist.add(POST);
        restApiLlist.add(GET);
        restApiLlist.add(Download);
        restApiLlist.add(Upload);

        List<String> commomUiCompnentsList = new ArrayList<String>();
        String listView = context.getString(R.string.ListView_Text);
        String customlistview = context.getString(R.string.CustomListView_Text);
        String dropdown = context.getString(R.string.Dropdown_Text);
        String alertdialog = context.getString(R.string.AlertDialog_Text);
        commomUiCompnentsList.add(listView);
        commomUiCompnentsList.add(customlistview);
        commomUiCompnentsList.add(dropdown);
        commomUiCompnentsList.add(alertdialog);
        List<String> dataPersiList = new ArrayList<String>();
        String sqlitedatabase = context.getString(R.string.SQLiteDatabases);

        dataPersiList.add(sqlitedatabase);

        List<String> mapList = new ArrayList<String>();
        mapList.add(context.getString(R.string.GoogleMap));

        List<String> PDFList = new ArrayList<String>();
        //PDFList.add(context.getString(R.string.UITextToPDF));
        PDFList.add(context.getString(R.string.drawPDF));
        //PDFList.add(context.getString(R.string.ExcelToPDF));

        List<String> mediaList = new ArrayList<String>();
        //mediaList.add(context.getString(R.string.Audio));
        //mediaList.add(context.getString(R.string.video));
        mediaList.add(context.getString(R.string.recordVideo));
        mediaList.add(context.getString(R.string.myVideos));
        mediaList.add(context.getString(R.string.camera));

        List<String> imageViewList = new ArrayList<String>();
        imageViewList.add(context.getString(R.string.roundedImageView));
        imageViewList.add(context.getString(R.string.borderImageView));

        List<String> dateModelList = new ArrayList<String>();
        dateModelList.add(context.getString(R.string.nod));

        List<String> notificationList = new ArrayList<String>();
        notificationList.add(context.getString(R.string.localnotification));

        expandableListDetail.put(context.getString(R.string.RESTApi), restApiLlist);
        expandableListDetail.put(context.getString(R.string.UIComponents), commomUiCompnentsList);
        expandableListDetail.put(context.getString(R.string.StorageOptions),dataPersiList);
        expandableListDetail.put(context.getString(R.string.Maps),mapList);
        expandableListDetail.put(context.getString(R.string.PDFGenerator),PDFList);
        expandableListDetail.put(context.getString(R.string.mediaManager),mediaList);
        expandableListDetail.put(context.getString(R.string.customImageView),imageViewList);
        expandableListDetail.put(context.getString(R.string.dateModel),dateModelList);
        expandableListDetail.put(context.getString(R.string.notification),notificationList);


        /*        expandableListDetail.put("", football);
        expandableListDetail.put("BASKETBALL TEAMS", basketball);
        */

        return expandableListDetail;
    }
}
