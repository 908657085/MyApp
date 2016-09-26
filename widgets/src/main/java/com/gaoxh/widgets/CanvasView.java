package com.gaoxh.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author 高雄辉
 * @Description:
 * @date 16/9/23 上午10:48
 */
public class CanvasView extends View {
    private int width;
    private int height;

    private int strokeWidth = 5;



    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        initCacheBitmap();
    }

    private Bitmap cacheBitmap;
    private Canvas cacheCanvas;
    private Path path;
    private Paint cachePaint;

    private void initCacheBitmap() {
        //创建缓冲区
        cacheBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        path = new Path();

        cacheCanvas.setBitmap(cacheBitmap);

        cachePaint = new Paint(Paint.DITHER_FLAG);
        cachePaint.setStrokeWidth(strokeWidth);
        cachePaint.setColor(Color.RED);
        cachePaint.setStyle(Paint.Style.STROKE);
        //抗锯齿
        cachePaint.setAntiAlias(true);
        //防抖动
        cachePaint.setDither(true);
    }

    float preX;
    float preY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取拖动事件发生的位置
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                preX=x;
                preY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX,preY,x,y);
                preX=x;
                preY=y;
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path,cachePaint);
                path.reset();
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint bmpPaint = new Paint();
        //绘制缓存区域
        canvas.drawBitmap(cacheBitmap,0,0,bmpPaint);
        //绘制最新区域
        canvas.drawPath(path,cachePaint);
    }

    public void reset(){
        cacheBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        cacheCanvas.setBitmap(cacheBitmap);
        invalidate();
    }

}
