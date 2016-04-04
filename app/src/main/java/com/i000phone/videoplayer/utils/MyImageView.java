package com.i000phone.videoplayer.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/3/29.
 */
public class MyImageView extends ImageView {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
         Drawable drawable = getDrawable();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (drawable!=null && (widthMode
                == MeasureSpec.EXACTLY && heightMode!= MeasureSpec.EXACTLY)) {
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);
            int h = widthSize*3/2;//drawable.getIntrinsicHeight() * widthSize / drawable.getIntrinsicWidth();
            switch (heightMode) {
                case MeasureSpec.AT_MOST:
                    heightSize = Math.min(h,heightSize);
                break;
                case MeasureSpec.UNSPECIFIED:
                    heightSize = h;
                    break;
            }
            setMeasuredDimension(widthSize, heightSize);
            //生成一个MeasureSpec的数
//            MeasureSpec.makeMeasureSpec()
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }
}
