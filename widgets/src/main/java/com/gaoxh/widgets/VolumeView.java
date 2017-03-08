package com.gaoxh.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by devil on 2017/3/8.
 */

public class VolumeView extends View {

    private int width;

    private int height;

    private int volume_radius;

    public VolumeView(Context context) {
        super(context);
    }

    public VolumeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VolumeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    private void calculateRadius(int width,int height){

    }


}
