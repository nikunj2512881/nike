package com.example.atos.myapplication.controller;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.customclasses.BaseActivity;
import com.example.atos.myapplication.global.Constants;
import com.example.atos.myapplication.customclasses.ProgressDialogue;
import com.example.atos.myapplication.global.Utils;
import com.example.atos.myapplication.handler.DownloadServiceCallHandler;
import com.example.atos.myapplication.interfaces.CallBackInterfaceDetail;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class DownloadRequestActivity extends BaseActivity implements CallBackInterfaceDetail {


    ProgressDialogue obj;
    ImageView downloadImgView = null;
    EditText requestEditTxt = null;
     ArrayList<String> arrayImageList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_request);
        downloadImgView = (ImageView) findViewById(R.id.downloadImageView);
        requestEditTxt = (EditText) findViewById(R.id.requestDowloadFileEdtTxt);

        Button btnAccess = (Button) findViewById(R.id.requestDowloadBtn);
        arrayImageList.add(Constants.downloadSampleURL1);
        arrayImageList.add(Constants.downloadSampleURL2);
        arrayImageList.add(Constants.downloadSampleURL3);
        arrayImageList.add(Constants.downloadSampleURL4);
        arrayImageList.add(Constants.downloadSampleURL5);

        obj = new ProgressDialogue(this);

        btnAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.showProgressDialog();

                if (Utils.isNetworkConnected(DownloadRequestActivity.this)) {

                    int idx = new Random().nextInt(arrayImageList.size());
                    String randomURl = (String) arrayImageList.get(idx);

                    requestEditTxt.setText(randomURl);
                    downloadImgView.setVisibility(View.GONE);
                    DownloadServiceCallHandler handler = new DownloadServiceCallHandler(DownloadRequestActivity.this);
                    String fileName = "sample.jpg";

                    File file = new File(getApplicationContext().getFilesDir() + fileName);
                    boolean deleted = file.delete();
                    handler.downloadServiceCall(randomURl,file);
                } else {
                    obj.stopProgressDialog();
                    showToast("Network not available!!!");
                }
            }
        });
    }


    @Override
    public void callBack(String response) {
        obj.stopProgressDialog();
      //  this.showToast(response);
        this.showView();

    }

    @Override
    public void failedWithErrorMessage(String errorMessage) {
        obj.stopProgressDialog();
    }


    public void showView()
    {


        this.downloadImgView.setVisibility(View.VISIBLE);
       // https://pbs.twimg.com/profile_images/667711300814925824/5RSRUymL.jpg
        File pdfFile = new File(getApplicationContext().getFilesDir()  + "sample.jpg");  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        this.downloadImgView.setImageResource(0);
        this.downloadImgView.setImageURI(path);

        /*
        File pdfFile = new File(getApplicationContext().getFilesDir()  + "sample.pdf");  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        String pathStr = path.getPath();
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        String appPath = getApplicationContext().getFilesDir().getAbsolutePath();
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        try{

            startActivity(pdfIntent);
        }catch(ActivityNotFoundException e){
            Toast.makeText(DownloadRequestActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
        */
    }
}
