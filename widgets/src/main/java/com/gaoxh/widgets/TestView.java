package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 高雄辉 on 2017/3/9.
 */

public class TestView extends View {
    private int width;
    private int height;


    Paint paint;

    Paint paint1;

    private Point centerPoint;
    private float startAngle;
    private float sweepAngle;
    private int radius;
    private int largestRadius;

    public TestView(Context context) {
        super(context);
        init();
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(50);
        paint.setStyle(Paint.Style.STROKE);

        paint1=new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.BLUE);
        paint1.setStrokeWidth(50);
        paint1.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=MeasureSpec.getSize(widthMeasureSpec);
        height=MeasureSpec.getSize(heightMeasureSpec);
        centerPoint=new Point(width/2,height);
        calculateRadius();
    }

    private void calculateRadius(){
        if(width/2>height){
            radius=height;
        }else{
            radius=width/2;
        }
        double hypotenuse=Math.sqrt(Math.pow(width/2,2)+Math.pow(height,2));
        double radian=Math.asin(height/hypotenuse);
        double degrees= Math.toDegrees(radian);
        largestRadius= (int) hypotenuse;
        startAngle= (float) (-180+degrees);
        sweepAngle= (float) (180-2*degrees);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path=new Path();
        path.lineTo(0,height);
        path.lineTo(width,height);
        path.lineTo(width,0);
        path.close();
        canvas.drawPath(path,paint);

        canvas.drawArc(new RectF(centerPoint.x-radius,centerPoint.y-radius,centerPoint.x+radius,centerPoint.y+radius),startAngle,sweepAngle,true,paint);

        canvas.drawArc(new RectF(centerPoint.x-largestRadius,centerPoint.y-largestRadius,centerPoint.x+largestRadius,centerPoint.y+largestRadius),startAngle,sweepAngle,true,paint1);
    }
}
