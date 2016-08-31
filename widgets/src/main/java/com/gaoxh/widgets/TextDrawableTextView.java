package com.gaoxh.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 高雄辉 on 2016/6/12 10:34
 */
public class TextDrawableTextView extends TextView {
    private String text_drawableTop;
    private int textColor_drawableTop;
    private int textSize_drawableTop;
    private Paint paint_textTop;


    private String text_drawableBottom;
    private int textColor_drawableBottom;
    private int textSize_drawableBottom;
    private Paint paint_textBottom;

    private String text_drawableLeft;
    private int textColor_drawableLeft;
    private int textSize_drawableLeft;
    private Paint paint_textLeft;


    private String text_drawableRight;
    private int textColor_drawableRight;
    private int textSize_drawableRight;
    private Paint paint_textRight;



    private int width;
    private int height;
    private int width_text;
    private int height_text;

    public TextDrawableTextView(Context context) {
        super(context);
    }

    public TextDrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs,0);
    }

    public TextDrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs,defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextDrawableTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextDrawableTextView);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.TextDrawableTextView_drawableTopText) {
                text_drawableTop = a.getString(attr);
            } else if (attr == R.styleable.TextDrawableTextView_drawableTopTextColor) {
                textColor_drawableTop = a.getColor(attr, Color.BLACK);
            } else if (attr == R.styleable.TextDrawableTextView_drawableTopTextSize) {
                textSize_drawableTop = a.getDimensionPixelSize(attr, R.dimen.textsize_default);
            } else if (attr == R.styleable.TextDrawableTextView_drawableBottomText) {
                text_drawableBottom = a.getString(attr);
            } else if (attr == R.styleable.TextDrawableTextView_drawableBottomTextColor) {
                textColor_drawableBottom = a.getColor(attr, Color.BLACK);
            } else if (attr == R.styleable.TextDrawableTextView_drawableBottomTextSize) {
                textSize_drawableBottom = a.getDimensionPixelSize(attr, R.dimen.textsize_default);
            } else if (attr == R.styleable.TextDrawableTextView_drawableLeftText) {
                text_drawableLeft = a.getString(attr);
            } else if (attr == R.styleable.TextDrawableTextView_drawableLeftTextColor) {
                textColor_drawableLeft = a.getColor(attr, Color.BLACK);
            } else if (attr == R.styleable.TextDrawableTextView_drawableLeftTextSize) {
                textSize_drawableLeft = a.getDimensionPixelSize(attr, R.dimen.textsize_default);
            } else if (attr == R.styleable.TextDrawableTextView_drawableRightText) {
                text_drawableRight = a.getString(attr);
            } else if (attr == R.styleable.TextDrawableTextView_drawableRightTextColor) {
                textColor_drawableRight = a.getColor(attr, Color.BLACK);
            } else if (attr == R.styleable.TextDrawableTextView_drawableRightTextSize) {
                textSize_drawableRight = a.getDimensionPixelSize(attr, R.dimen.textsize_default);
            }
        }
        a.recycle();
        initPaint();
    }

    private void initPaint(){
        paint_textTop=new Paint();
        paint_textTop.setTextSize(textSize_drawableTop);
        paint_textTop.setColor(textColor_drawableTop);

        paint_textBottom=new Paint();
        paint_textBottom.setTextSize(textSize_drawableBottom);
        paint_textBottom.setColor(textColor_drawableBottom);

        paint_textLeft=new Paint();
        paint_textLeft.setTextSize(textSize_drawableLeft);
        paint_textLeft.setColor(textColor_drawableLeft);

        paint_textRight=new Paint();
        paint_textRight.setTextSize(textSize_drawableRight);
        paint_textRight.setColor(textColor_drawableRight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        int measuredWidth=getMeasuredWidth();
        int measuredHeight=getMeasuredHeight();
        if (widthMode == MeasureSpec.EXACTLY) {
            // Parent has told us how big to be. So be it.
            width = widthSize;
        } else {

            width=measuredWidth;
            if(text_drawableLeft!=null){
                width=measuredWidth+(int)paint_textLeft.measureText(text_drawableLeft);
            }
            if(text_drawableRight!=null){
                width=measuredWidth+(int)paint_textRight.measureText(text_drawableRight);
            }
        }

        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else{

            height=measuredHeight;
            if(text_drawableTop!=null){
                height=measuredHeight+ (int) paint_textTop.measureText(text_drawableTop);
            }

            if(text_drawableBottom!=null){
                height=measuredHeight+(int)paint_textBottom.measureText(text_drawableBottom);
            }
        }
        setMeasuredDimension(width,height);
        this.width_text=measuredWidth;
        this.height_text=measuredHeight;
        this.width=width;
        this.height=height;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(text_drawableTop!=null){
            canvas.drawText(text_drawableTop,0,0,paint_textTop);
        }

        if(text_drawableLeft!=null){
            canvas.drawText(text_drawableLeft,0,height/2,paint_textLeft);
        }

        if(text_drawableBottom!=null){
            canvas.drawText(text_drawableBottom,0,height,paint_textBottom);
        }
        if(text_drawableRight!=null){
            canvas.drawText(text_drawableRight,width,0,paint_textRight);
        }
    }
}
