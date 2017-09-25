package com.example.atos.myapplication.customclasses;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.widget.Toast;

import com.example.atos.myapplication.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Atos on 04/09/17.
 */

public class PdfGenerator {

    private FileOutputStream outputStream;
    private File file;
    private String filename;
    private Context context;
    private PrintedPdfDocument document;
    private PdfDocument.Page page;
    private int titleBaseLine = 72;
    private int leftMargin = 25;
    private int pageWidth;
    private int pageHeight;
    private Paint paint;
    private Canvas canvas;
    private int tableStartBaseLine;
    private int imgStartBaseLine=72;
    private int columnWidth = 0;
    private int rowWidth = 50;
    private int tableLeftMargin = 20;
    private int tableTopMargin = 30;
    private boolean isTableHeader = true;

    public PdfGenerator(Context context){
        this.context = context;
        if(isExternalStorageWritable()) {
            this.filename = getFileName();
            this.file = new File(getAlbumStorageDir("PDF"), filename);
            try {
                this.outputStream = new FileOutputStream(file);
                //createPDF(outputStream);
                //outputStream.flush();
                //outputStream.close();
                //this.openPDF();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            paint = new Paint();

        }
    }
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    private String getFileName() {
        //TODO: 06/10/2015
        return "Sample" + ".pdf";
    }
    private File getAlbumStorageDir(String albumName) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(root, albumName);
            if (!file.mkdirs()) {
                Log.e("Tag", "Directory not created");
            }
            return file;

    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void configurePage() throws IOException {
        this.document = new PrintedPdfDocument(context, getPrintAttributes());
        // start a page
        this.page = document.startPage(1);
        this.pageWidth = page.getCanvas().getWidth();
        this.pageHeight = page.getCanvas().getHeight();
        this.canvas = page.getCanvas();

        // draw something on the page

        //drawPage(page);

    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private PrintAttributes getPrintAttributes() {
        PrintAttributes.Builder builder = new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setResolution(new PrintAttributes.Resolution("res1","Resolution",50,50)).setMinMargins(new PrintAttributes.Margins(5, 5, 5, 5));
        PrintAttributes printAttributes = builder.build();
        return printAttributes;
    }
    public void setTitle(String title) {

        //Title backgroud
        drawBackgroud(15,15,pageWidth-15,95);

        //Title Text
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText(title,leftMargin + 30,titleBaseLine,paint);

        titleBaseLine = titleBaseLine + 65;

    }
    public void setMultilineParagraph(String paragraph){
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(30);
        StaticLayout textLayout = new StaticLayout(paragraph,textPaint,pageWidth-25, Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
        canvas.save();
        canvas.translate(leftMargin,getTitleBaseLine());
        textLayout.draw(canvas);
        canvas.restore();
        titleBaseLine = titleBaseLine + (textLayout.getLineCount() * 40);

    }
    public void setKeyValueInfo(String key,String value) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawText(key,leftMargin,getTitleBaseLine(),paint);
        canvas.drawText(value,leftMargin + 130,getTitleBaseLine(),paint);

        titleBaseLine = titleBaseLine + 45;
    }
    public void setTable(String[][] tableData){
        int rowCount = tableData.length;
        int columnCount = tableData[0].length;

        paint.setColor(Color.BLACK);
        paint.setTextSize(30);

        drawHorizontalLines(rowCount);
        drawVerticalLines(columnCount,rowCount);
        for (int i=0;i<rowCount;i++){
            if(i == 0){
                isTableHeader = true;
            }
            else {
                isTableHeader = false;
            }
            drawTableData(tableData[i],tableStartBaseLine);
            tableStartBaseLine = tableStartBaseLine + rowWidth;
        }
    }
    public int getTitleBaseLine(){
        return titleBaseLine + 20;
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void endPage(){
        // finish the page
        document.finishPage(page);
        // write the document content
        try {
            document.writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //close the document
        document.close();
    }
    private void drawHorizontalLines(int rowCount) {

        paint.setColor(Color.BLACK);
        tableStartBaseLine = getTitleBaseLine();

        if(isTableHeader){
            drawBackgroud(leftMargin,tableStartBaseLine,pageWidth-15,tableStartBaseLine+rowWidth);
        }

        for (int i=0;i<=rowCount;i++){
            canvas.drawLine(leftMargin,getTitleBaseLine(),(pageWidth-15),getTitleBaseLine(),paint);
            titleBaseLine = titleBaseLine + rowWidth;
        }
    }
    private void drawVerticalLines(int columnCount,int rowCount) {

        paint.setColor(Color.BLACK);
        int tablewidth = pageWidth - (leftMargin) - 15;
        int tempLeftMargin = leftMargin;
        columnWidth = tablewidth/columnCount;
        for (int i=0;i<=columnCount;i++){
            canvas.drawLine(tempLeftMargin,tableStartBaseLine,tempLeftMargin,tableStartBaseLine + (rowCount * rowWidth),paint);
            tempLeftMargin = tempLeftMargin + columnWidth;
        }
    }
    private void drawTableData(String[] rowData , int tableStartBaseLine){
        if(isTableHeader){
            paint.setColor(Color.WHITE);
            paint.setTextSize(25);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        }
        else {
            paint.setColor(Color.BLACK);
            paint.setTextSize(25);
        }

        int tempLeftMargin = leftMargin;

        for (int i = 0;i<rowData.length;i++) {
            canvas.drawText(rowData[i], tempLeftMargin + tableLeftMargin, tableStartBaseLine + tableTopMargin, paint);
            tempLeftMargin = tempLeftMargin + columnWidth;
        }
    }
    private void drawBackgroud(int left,int top,int right,int bottom){
        paint.setColor(Color.parseColor("#0067a1"));
        canvas.drawRect(left, top,right, bottom, paint);
    }
    public void setImage(){
        Resources res = context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.cvgirl);
        canvas.drawBitmap(bitmap,400,imgStartBaseLine + 32, paint);
    }
    public void openPDF(){
        String filename = getFileName();
        File file = new File(getAlbumStorageDir("PDF"), filename);
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file),"application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        Intent intent = Intent.createChooser(target, "Open File");
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            System.out.println(e.getCause());
            // Instruct the user to install a PDF reader here, or something
        }
    }
}
