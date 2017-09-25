package com.example.atos.myapplication.customclasses;

/**
 * Created by Atos on 17/08/17.
 */
import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogue {

    private ProgressDialog mProgressDialog;
    private Context context;
    public ProgressDialogue(Context context){
      this.context = context;
    }
    public void showProgressDialog() {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setTitle("Loading");
        mProgressDialog.setMessage("In Progress...");
        mProgressDialog.setCancelable(false); // disable dismiss by tapping outside of the dialog
        mProgressDialog.show();
    }

    /**
     * Method used to stop progress dialog
     */
    public void stopProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
