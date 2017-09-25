package com.example.atos.myapplication.customclasses;

import android.content.Context;
import android.util.AttributeSet;

import com.example.atos.myapplication.R;

/**
 * Created by Atos on 09/08/17.
 */

public class CustomEditTextField extends android.support.v7.widget.AppCompatEditText {

    public CustomEditTextField(Context context) {
        super(context);
        init();
    }

    public CustomEditTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

            this.setBackgroundDrawable(context.getDrawable(R.drawable.edit_text_custom));
            //getDrawable(getResources(),R.drawable.edit_text_custom,null));
    }

    public CustomEditTextField(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        // set your input filter here
    }
}
