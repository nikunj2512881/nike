package com.example.atos.myapplication.customclasses;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.atos.myapplication.R;

/**
 * Created by Atos on 06/09/17.
 */

public class BorderedImageView extends android.support.v7.widget.AppCompatImageView {

    public BorderedImageView(Context context) {
        super(context);
        init();
    }

    public BorderedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        this.setBackgroundDrawable(context.getDrawable(R.drawable.custom_image_view_border));
        //getDrawable(getResources(),R.drawable.edit_text_custom,null));
    }

    public BorderedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        // set your input filter here
    }
}
