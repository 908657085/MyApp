package com.gaoxh.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by devil on 2017/3/15.
 * <p>
 * 底部按钮控件栏
 */

public class BottomTabView extends ViewGroup {

    private static String TAG = BottomTabView.class.getSimpleName();

    /**
     * 默认宽度:全屏
     */
    public static int DEFAULT_WIDTH = -1;
    /**
     * 默认高度
     */
    public static int DEFAULT_HEIGHT = -1;

    private Paint linePaint;


    private int mViewWidth;
    private int mViewHeight;

    private int mChildWidth;
    private int mChildHeight;

    private int mChildCount;

    public onCheckedChangeListener mOnCheckedChangeListener;

    public BottomTabView(Context context) {
        super(context);
        initAttrs(context, null);
    }

    public BottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }



    private void initAttrs(Context context, AttributeSet attrs) {
        DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
        DEFAULT_WIDTH = displayMetrics.widthPixels;
        DEFAULT_HEIGHT = (int) (50*displayMetrics.density);
        this.addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                mChildCount = getChildCount();
                initListener();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            // Parent has told us how big to be. So be it.
            mViewWidth = widthSize;
        } else {
            mViewWidth = DEFAULT_WIDTH;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            // Parent has told us how big to be. So be it.
            mViewHeight = heightSize;
        } else {
            mViewHeight = DEFAULT_HEIGHT;
        }

        setMeasuredDimension(mViewWidth, mViewHeight);

        mChildCount = getChildCount();
        mChildWidth=mViewWidth/mChildCount;
        mChildHeight=Math.abs(mViewHeight);
        for(int i = 0;i < mChildCount;i++){
            View child = getChildAt(i);
            int childWidthMeasureSpec=MeasureSpec.makeMeasureSpec(mChildWidth,MeasureSpec.EXACTLY);
            int childHeightMeasureSpec=MeasureSpec.makeMeasureSpec(mChildHeight,MeasureSpec.EXACTLY);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(mChildCount>0){
            int childTop=0;
            int childLeft=0;
            for(int i=0;i<mChildCount;i++){
                View view=getChildAt(i);
                view.layout(childLeft,childTop,childLeft+mChildWidth,mChildHeight);
                childLeft+=mChildWidth;
            }
        }

    }





    private void initListener(){
        for(int i=0;i<mChildCount;i++){
            BottomTab bottomTab= (BottomTab) getChildAt(i);
            bottomTab.setIndex(i);
            bottomTab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index=((BottomTab)v).getIndex();
                    Log.d(TAG,"clickItem"+index);
                    BottomTabView.this.setCheckedItem(index);
                    if(mOnCheckedChangeListener!=null){
                        mOnCheckedChangeListener.checkedChange(index);
                    }
                }
            });
        }
    }

    public void setCheckedItem( int index){
        for(int i=0;i<mChildCount;i++){
            BottomTab bottomTab= (BottomTab) getChildAt(i);
            if(i==index){
                bottomTab.setCheckState(true);
            }else{
                bottomTab.setCheckState(false);
            }
        }
    }


    public interface onCheckedChangeListener{
        void checkedChange(int index);
    }

}
