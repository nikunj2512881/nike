package com.example.atos.myapplication.controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.adapter.ViewPrintAdapter;
import com.example.atos.myapplication.customclasses.PdfGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public class PdfActivity extends AppCompatActivity {

    PdfGenerator pdfObj;
    String text = "Looking for a long term full time job where I can apply my extensive skills and knowledge to the position for which I am hired.";
    String[][] tableData={{"sr.no","Qualification","Score"},{"1","Graduate","78%"},{"2","12th","70%"},{"3","10th","91.27%"}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        pdfObj = new PdfGenerator(this);
        try {
            pdfObj.configurePage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfObj.setTitle("Sample CV");
        pdfObj.setKeyValueInfo("Name","Pooja Waghere");
        pdfObj.setKeyValueInfo("email Id","pooja@gmail.com");
        pdfObj.setKeyValueInfo("mob no.","648236482");
        pdfObj.setImage();
        pdfObj.setMultilineParagraph(text);
        pdfObj.setTable(tableData);
        pdfObj.endPage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void createPDF(View view){
        pdfObj.openPDF();
    }
}
