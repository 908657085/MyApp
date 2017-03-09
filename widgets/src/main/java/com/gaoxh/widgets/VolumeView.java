package com.gaoxh.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gaoxh.data.utils.ScreenUtils;

/**
 * Created by devil on 2017/3/8.
 */

public class VolumeView extends View {

    private Context context;

    public static final int DEFAULT_VOLUME_STATE_NORMAL = 0;
    public static final int DEFAULT_VOLUME_STATE_ERROR = -1;

    public static int DEFAULT_PAINT_WIDTH = 20;

    public static int DEFAULT_LEVEL_MAX = 7;

    public static int DEFAULT_VOLUME_MAX = 35;

    public static int[] DEFAULT_PAINT_GRADUAL_COLORS = {-65536, -33024, -256, -16711936, -16776961, -11861886, -7667457};

    private int screenWidth;

    private int screenHeight;


    private int viewWidth;

    private int viewHeight;

    private Paint normalPaint;

    private Paint errorPaint;

    private Point centerPoint;

    private float startAngle;

    private float sweepAngle;
    private float volumeRadiusMax;

    private float volumeRadiusCurrent;


    private int paintWidth = DEFAULT_PAINT_WIDTH;
    private int levelMax = DEFAULT_LEVEL_MAX;
    private int volumeMax = DEFAULT_VOLUME_MAX;
    private int paintColorNormal = Color.GRAY;
    private int paintColorError = Color.GRAY;
    private int[] paintGradualColors = DEFAULT_PAINT_GRADUAL_COLORS;

    private int volumeState = DEFAULT_VOLUME_STATE_NORMAL;
    private boolean gradualState = true;
    private int volumeCurrent = volumeMax;
    private int levelCurrent = levelMax;


    public VolumeView(Context context) {
        super(context);
        init(context);
    }

    public VolumeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VolumeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        screenWidth = ScreenUtils.getScreenWidth(context);
        screenHeight = ScreenUtils.getScreenHeight(context);
        normalPaint = new Paint();
        normalPaint.setAntiAlias(true);
        normalPaint.setStrokeCap(Paint.Cap.ROUND);
        normalPaint.setStyle(Paint.Style.STROKE);
        normalPaint.setColor(paintColorNormal);
        errorPaint = new Paint();
        errorPaint.setAntiAlias(true);
        errorPaint.setStrokeCap(Paint.Cap.BUTT);
        errorPaint.setStyle(Paint.Style.FILL);
        errorPaint.setColor(paintColorError);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (viewWidth > screenWidth) {
            viewWidth = screenWidth;
        }
        if (viewHeight > screenHeight) {
            viewHeight = screenHeight;
        }
        calculateParams();
    }

    private void calculateParams() {
        paintWidth = viewHeight / (levelMax * 2);
        normalPaint.setStrokeWidth(paintWidth);
        errorPaint.setStrokeWidth(paintWidth);

        int viewWidthHalf = viewWidth / 2;
        centerPoint = new Point(viewWidthHalf, viewHeight);
        double hypotenuse = Math.sqrt(Math.pow(viewWidth / 2, 2) + Math.pow(viewHeight, 2));
        double radian = Math.asin(viewHeight / hypotenuse);
        double degrees = Math.toDegrees(radian);
        volumeRadiusMax = (float) hypotenuse;
        startAngle = (float) (-180 + degrees);
        sweepAngle = (float) (180 - 2 * degrees);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawVolume(canvas);
    }

    private void drawVolume(Canvas canvas) {
        switch (volumeState) {
            case DEFAULT_VOLUME_STATE_NORMAL:
                drawVolumeNormal(canvas);
                break;
            case DEFAULT_VOLUME_STATE_ERROR:
                drawVolumeError(canvas);
                break;
            default:
                drawVolumeError(canvas);
                break;
        }
    }

    private void drawVolumeNormal(Canvas canvas) {
        for (int i = 0; i < levelCurrent; i++) {
            if (!gradualState) {
                normalPaint.setColor(paintColorNormal);
            } else {
                int color = (i < paintGradualColors.length) ? paintGradualColors[i] : paintGradualColors[i % paintGradualColors.length];
                normalPaint.setColor(color);
            }
            volumeRadiusCurrent = (float) ((2 * (i + 1) - 0.5) * paintWidth);
            canvas.drawArc(new RectF(centerPoint.x - volumeRadiusCurrent,
                            centerPoint.y - volumeRadiusCurrent,
                            centerPoint.x + volumeRadiusCurrent,
                            centerPoint.y + volumeRadiusCurrent),
                    startAngle,
                    sweepAngle,
                    false,
                    normalPaint
            );
        }
    }

    private void drawVolumeError(Canvas canvas) {
        errorPaint.setColor(Color.GRAY);
        volumeRadiusCurrent = (float) (viewHeight - (1.5 * paintWidth));
        canvas.drawArc(new RectF(centerPoint.x - volumeRadiusCurrent,
                        centerPoint.y - volumeRadiusCurrent,
                        centerPoint.x + volumeRadiusCurrent,
                        centerPoint.y + volumeRadiusCurrent),
                startAngle,
                sweepAngle,
                true,
                errorPaint
        );
    }


    public void setVolume(int volume) {
        volumeState = DEFAULT_VOLUME_STATE_NORMAL;
        if (volume < 0) {
            volumeCurrent = 0;
            volumeState = DEFAULT_VOLUME_STATE_ERROR;
        } else if (volume > volumeMax) {
            volumeCurrent = volumeMax;
        } else {
            volumeCurrent = volume;
        }
        levelCurrent = (int) (Math.round((double) volumeCurrent / (double) volumeMax * levelMax));
        postInvalidate();
    }

    public void setVolumeState(int volumeState) {
        this.volumeState = volumeState;
        postInvalidate();
    }
}
