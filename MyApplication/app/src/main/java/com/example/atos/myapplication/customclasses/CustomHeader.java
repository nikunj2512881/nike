package com.example.atos.myapplication.customclasses;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.controller.GetRequestActivity;
import com.example.atos.myapplication.controller.MainActivity;
import com.example.atos.myapplication.controller.media.MediaImageSelectorActivity;
import com.example.atos.myapplication.global.PermissionUtils;

import java.util.Locale;

/**
 * Created by Atos on 24/08/17.
 */



public class CustomHeader {
    public interface OnHeaderElementClickListener {
        public void onLeftButtonClicked();
        public void onRightButtonClicked();
    }

    Context context;
    OnHeaderElementClickListener buttonListener;
    ActionBar mActionBar;
    String leftImgName;
    String rightImgName;
    ImageButton imageLeftButton;
    ImageButton imageRightButton;
    String headerTitle;

    public CustomHeader(OnHeaderElementClickListener buttonListener,Context context,ActionBar actionBar,String leftImgName,String rightImgName,String headerTitle){
        this.buttonListener = buttonListener;
        this.context=context;
        this.mActionBar=actionBar;
        this.leftImgName = leftImgName;
        this.rightImgName = rightImgName;
        this.headerTitle = headerTitle;
    }



    public void setHeader() {
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(context);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText(headerTitle);

        if(leftImgName != ""){
            imageLeftButton = (ImageButton) mCustomView
                    .findViewById(R.id.imageButton1);

            String leftImgUri = "@drawable/"+leftImgName;
            int leftImageResource = context.getResources().getIdentifier(leftImgUri, null, context.getPackageName());
            Drawable leftRes = context.getResources().getDrawable(leftImageResource);
            imageLeftButton.setImageDrawable(leftRes);

            imageLeftButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    buttonListener.onLeftButtonClicked();
                }
            });
        }

        if(rightImgName != "") {

            imageRightButton = (ImageButton) mCustomView
                    .findViewById(R.id.imageButton2);

            String rightImgUri = "@drawable/"+rightImgName;
            int rightImageResource = context.getResources().getIdentifier(rightImgUri, null, context.getPackageName());
            Drawable rightRes = context.getResources().getDrawable(rightImageResource);
            imageRightButton.setImageDrawable(rightRes);

            imageRightButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    buttonListener.onRightButtonClicked();
                }
            });
        }

        ActionBar.LayoutParams layout = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        mActionBar.setCustomView(mCustomView , layout);
        mActionBar.setDisplayShowCustomEnabled(true);
        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0, 0);
    }
}
