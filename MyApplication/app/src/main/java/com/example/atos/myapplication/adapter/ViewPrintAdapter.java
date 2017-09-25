package com.example.atos.myapplication.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.view.View;
import android.widget.ScrollView;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Atos on 31/08/17.
 */

@TargetApi(Build.VERSION_CODES.KITKAT)
public class ViewPrintAdapter extends PrintDocumentAdapter {

    private PrintedPdfDocument mDocument;
    private Context mContext;
    private ScrollView mView;
    PdfDocument.Page[] writtenPagesArray;

    private static final String TAG = "PrintAdapter";


    private int mPages;



    public ViewPrintAdapter(Context context, ScrollView view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes,
                         CancellationSignal cancellationSignal,
                         LayoutResultCallback callback, Bundle extras) {


// Create a new PdfDocument with the requested page attributes
        mDocument = new PrintedPdfDocument(mContext, newAttributes);

        // Respond to cancellation request
        if (cancellationSignal.isCanceled() ) {
            callback.onLayoutCancelled();
            return;
        }

        // Compute the expected number of printed pages
        int pages = computePageCount(newAttributes);

        if (pages > 0) {
            // Return print information to print framework
            PrintDocumentInfo info = new PrintDocumentInfo
                    .Builder("print_output.pdf")
                    .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                    .setPageCount(pages)
                    .build();
            // Content layout reflow is complete
            callback.onLayoutFinished(info, true);
        } else {
            // Otherwise report an error to the print framework
            callback.onLayoutFailed("Page count calculation failed.");
        }
    }
/*
    @Override
    public void onWrite(PageRange[] pages, ParcelFileDescriptor destination,
                        CancellationSignal cancellationSignal,
                        WriteResultCallback callback) {

        // Start the page
        int i = mDocument.getPages().size();
        PdfDocument.Page page = mDocument.startPage(1);

        // Create a bitmap and put it a canvas for the view to draw to. Make it the size of the view
        Bitmap bitmap = Bitmap.createBitmap(mView.getWidth(), mView.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mView.draw(canvas);
        // create a Rect with the view's dimensions.
        Rect src = new Rect(0, 0, mView.getWidth(), mView.getHeight());
        // get the page canvas and measure it.
        Canvas pageCanvas = page.getCanvas();
        float pageWidth = pageCanvas.getWidth();
        float pageHeight = pageCanvas.getHeight();
        // how can we fit the Rect src onto this page while maintaining aspect ratio?
        float scale = Math.min(pageWidth/src.width(), pageHeight/src.height());
        float left = pageWidth / 2 - src.width() * scale / 2;
        float top = pageHeight / 2 - src.height() * scale / 2;
        float right = pageWidth / 2 + src.width() * scale / 2;
        float bottom = pageHeight / 2 + src.height() * scale / 2;
        RectF dst = new RectF(left, top, right, bottom);

        pageCanvas.drawBitmap(bitmap, src, dst, null);
        mDocument.finishPage(page);

        try {
            mDocument.writeTo(new FileOutputStream(
                    destination.getFileDescriptor()));
        } catch (IOException e) {
            callback.onWriteFailed(e.toString());
            return;
        } finally {
            mDocument.close();
            mDocument = null;
        }
        callback.onWriteFinished(new PageRange[]{new PageRange(0, 0)});
    }*/


    @Override
    public void onWrite(PageRange[] pages, ParcelFileDescriptor destination,
                        CancellationSignal cancellationSignal, WriteResultCallback callback) {
        // TODO Auto-generated method stub

        // Iterate over each page of the document,
        // check if it's in the output range.


	        // Check to see if this page is in the output range.


				// If so, add it to writtenPagesArray. writtenPagesArray.size()
	            // is used to compute the next output page index.
	        //	writtenPagesArray.append(writtenPagesArray.size(), i);
            for(int i = 0; i< 5; i++) {
                PdfDocument.Page page = mDocument.startPage(1);
                // check for cancellation

                // Draw page content for printing
                drawPage(page);
                // Rendering is complete, so page can be finalized.
                mDocument.finishPage(page);
            }




        // Write PDF document to file
        try {
            mDocument.writeTo(new FileOutputStream(
                    destination.getFileDescriptor()));
        } catch (IOException e) {
            callback.onWriteFailed(e.toString());
            return;
        } finally {
            mDocument.close();
            mDocument = null;
        }
        //PageRange[] writtenPages = computeWrittenPages();
        // Signal the print framework the document is complete
        callback.onWriteFinished(pages);


    }

    private int computePageCount(PrintAttributes printAttributes) {
        int itemsPerPage = 4; // default item count for portrait mode

        PrintAttributes.MediaSize pageSize = printAttributes.getMediaSize();
        if (!pageSize.isPortrait()) {
            // Six items per page in landscape orientation
            itemsPerPage = 6;
        }

        // Determine number of print items
        int printItemCount = 5;	//getPrintItemCount();

        return (int) Math.ceil(printItemCount / itemsPerPage);
    }

    private void drawPage(PdfDocument.Page page) {
        Canvas canvas = page.getCanvas();

        // units are in points (1/72 of an inch)
        int titleBaseLine = 72;
        int leftMargin = 54;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(36);
        canvas.drawText("Test Title", leftMargin, titleBaseLine, paint);

        paint.setTextSize(11);
        canvas.drawText("Test paragraph", leftMargin, titleBaseLine + 25, paint);

        paint.setColor(Color.BLUE);
        canvas.drawRect(100, 100, 172, 172, paint);
    }
}
