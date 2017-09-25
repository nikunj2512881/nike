package com.example.atos.myapplication.customclasses;

import android.content.Context;
import android.util.AttributeSet;

import com.example.atos.myapplication.R;

/**
 * Created by Atos on 14/08/17.
 */

public class CustomButton extends android.support.v7.widget.AppCompatButton {

    public CustomButton(Context context) {
        super(context);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        this.setBackgroundDrawable(context.getDrawable(R.drawable.button_custom));
        //getDrawable(getResources(),R.drawable.edit_text_custom,null));
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        // set your input filter here
    }
}
