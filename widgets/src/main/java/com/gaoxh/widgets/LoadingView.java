package com.gaoxh.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 高雄辉 on 2016/6/16 9:35
 */
public class LoadingView extends View {
    private static final  float C=0.552284749831f;
    private static final String TAG="LoadingView";
    //白色
    private static final int WHITE_COLOR=0xFFFFFFFF;
    // 橙色
    private static final int ORANGE_COLOR = 0xffffa800;
    // 中等振幅大小
    private static final int MIDDLE_AMPLITUDE = 13;
    // 不同类型之间的振幅差距
    private static final int AMPLITUDE_DISPARITY = 5;
    // 总进度
    private static final int TOTAL_PROGRESS = 100;
    // 叶子飘动一个周期所花的时间
    private static final long LEAF_FLOAT_TIME = 3000;
    // 叶子旋转一周需要的时间
    private static final long LEAF_ROTATE_TIME = 2000;
    //进度条跑完的时间
    public static final long PROGRESS_FLOAT_TIME=200*100;
    //默认宽度
    private static int defaultWidth=400;
    //默认高度
    private static int defaultHeight=100;
    //间距
    private int padding=20;
    //风车轮子间距
    private int wheelPadding=5;
    //
    private int mWidth;
    private int mHeight;
    private int progressWidth;
    private int progressHeight;

    private int bgColor=WHITE_COLOR;
    private int progressColor=ORANGE_COLOR;
    private int wheelBgColor=ORANGE_COLOR;
    private int wheelColor=WHITE_COLOR;
    private Paint bgPaint,progressPaint,leafPaint,wheelPaint_wheel,wheelPaint_bg;


    private Path bgPath;
    private Path progressPath;
    private Path wheelPath;


    private int progress=0;
    private int rotate=0;


    // 当前所在的绘制的进度条的位置
    private int mCurrentProgressPosition;
    // 弧形的半径
    private int mArcRadius;


    // 中等振幅大小
    private int mMiddleAmplitude = MIDDLE_AMPLITUDE;
    // 振幅差
    private int mAmplitudeDisparity = AMPLITUDE_DISPARITY;

    // 叶子飘动一个周期所花的时间
    private long mLeafFloatTime = LEAF_FLOAT_TIME;
    // 叶子旋转一周需要的时间
    private long mLeafRotateTime = LEAF_ROTATE_TIME;

    private Bitmap mLeafBitmap;

    private int mLeafWidth, mLeafHeight;

    // 用于产生叶子信息
    private LeafFactory mLeafFactory;
    // 产生出的叶子信息
    private List<Leaf> mLeafInfos;
    // 用于控制随机增加的时间不抱团
    private int mAddTime;
    //控制轮子转啊转
    private Timer timer=new Timer();

    private boolean rePeat=true;
    private long progressFloatTime=PROGRESS_FLOAT_TIME;


    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){
        mLeafFloatTime = LEAF_FLOAT_TIME;
        mLeafRotateTime = LEAF_ROTATE_TIME;
        initBitmap();
        initPaint();
        mLeafFactory = new LeafFactory();
        mLeafInfos = mLeafFactory.generateLeafs();
    }

    private void initBitmap(){
         mLeafBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.leaf);
        mLeafWidth = mLeafBitmap.getWidth();
        mLeafHeight = mLeafBitmap.getHeight();
    }
    private void initPaint(){
        bgPaint=new Paint();
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(bgColor);
        bgPaint.setAlpha(50);
        bgPaint.setAntiAlias(true);
        bgPaint.setDither(true);

        progressPaint=new Paint();
        progressPaint.setStyle(Paint.Style.FILL);
        progressPaint.setColor(progressColor);
        progressPaint.setAntiAlias(true);
        progressPaint.setDither(true);

        leafPaint=new Paint();

        wheelPaint_bg=new Paint();
        wheelPaint_bg.setStyle(Paint.Style.FILL);
        wheelPaint_bg.setColor(wheelBgColor);
        wheelPaint_bg.setAntiAlias(true);
        wheelPaint_bg.setDither(true);

        wheelPaint_wheel=new Paint();
        wheelPaint_wheel.setStyle(Paint.Style.FILL);
        wheelPaint_wheel.setColor(wheelColor);
        wheelPaint_wheel.setAntiAlias(true);
        wheelPaint_wheel.setDither(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            // Parent has told us how big to be. So be it.
            width = widthSize;
        } else {
            width=defaultWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            // Parent has told us how big to be. So be it.
            height = heightSize;
        } else {
            height=defaultHeight;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
       initSize(w,h);
    }

    public void initSize(int w,int h){
        mWidth=w;
        mHeight=h;
        padding=mHeight/5;
        wheelPadding=mHeight/20;
        progressHeight=mHeight-padding*2;
        progressWidth=mWidth-padding*2;

        mArcRadius = progressHeight / 2;

        rectF_bg=new RectF(0,0,mWidth,mHeight);


        rectF_progress_arc=new RectF();
        rectF_progress_rect=new RectF();

        bgPath=new Path();
        bgPath.moveTo(mHeight/2,0);
        bgPath.cubicTo(mHeight/2*(1-C),0,0,mHeight/2*(1-C),0,mHeight/2);
        bgPath.cubicTo(0,mHeight/2*(1+C),mHeight/2*(1-C),mHeight,mHeight/2,mHeight);
        bgPath.lineTo(mWidth-mHeight/2,mHeight);
        bgPath.cubicTo(mWidth-mHeight/2*(1-C),mHeight,mWidth,mHeight/2*(1+C),mWidth,mHeight/2);
        bgPath.cubicTo(mWidth,mHeight/2*(1-C),mWidth-mHeight/2*(1-C),0,mWidth-mHeight/2,0);
        bgPath.close();

        progressPath=new Path();

        //top
        wheelPath=new Path();
        wheelPath.moveTo(0,-wheelPadding);
        wheelPath.quadTo(-(mHeight/2-wheelPadding*3)*3/4,-(mHeight/2-wheelPadding*2),0,-(mHeight/2-wheelPadding*2));
        wheelPath.quadTo((mHeight/2-wheelPadding*3)*3/4,-(mHeight/2-wheelPadding*2),0,-wheelPadding);
        wheelPath.close();
        //left
        // Path pathLeft=new Path();
        wheelPath.moveTo(-wheelPadding,0);
        wheelPath.quadTo(-(mHeight/2-wheelPadding*2),-(mHeight/2-wheelPadding*3)*3/4,-(mHeight/2-wheelPadding*2),0);
        wheelPath.quadTo(-(mHeight/2-wheelPadding*2),(mHeight/2-wheelPadding*3)*3/4,-wheelPadding,0);
        wheelPath.close();
        //right
        wheelPath.moveTo(wheelPadding,0);
        wheelPath.quadTo(mHeight/2-wheelPadding*2,-(mHeight/2-wheelPadding*3)*3/4,(mHeight/2-wheelPadding*2),0);
        wheelPath.quadTo(mHeight/2-wheelPadding*2,(mHeight/2-wheelPadding*3)*3/4,wheelPadding,0);
        wheelPath.close();
        //bottom
        wheelPath.moveTo(0,wheelPadding);
        wheelPath.quadTo(-(mHeight/2-wheelPadding*3)*3/4,mHeight/2-wheelPadding*2,0,mHeight/2-wheelPadding*2);
        wheelPath.quadTo((mHeight/2-wheelPadding*3)*3/4,mHeight/2-wheelPadding*2,0,wheelPadding);
        wheelPath.close();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBg(canvas);
        drawLeafs(canvas);
        if(progress>0){
            drawProgress(canvas);
        }
        drawWheel(canvas);
        postInvalidate();
    }

    private RectF rectF_bg;
    private RectF rectF_progress_arc;
    private RectF rectF_progress_rect;


    public void drawBg(Canvas canvas){
        canvas.drawRoundRect(rectF_bg,mHeight/2,mHeight/2,bgPaint);
    }


    /**
     * 绘制叶子
     *
     * @param canvas
     */
    private void drawLeafs(Canvas canvas) {
        mLeafRotateTime = mLeafRotateTime <= 0 ? LEAF_ROTATE_TIME : mLeafRotateTime;
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < mLeafInfos.size(); i++) {
            Leaf leaf = mLeafInfos.get(i);
            if (currentTime > leaf.startTime && leaf.startTime != 0) {
                // 绘制叶子－－根据叶子的类型和当前时间得出叶子的（x，y）
                getLeafLocation(leaf, currentTime);
                // 根据时间计算旋转角度
                canvas.save();
                // 通过Matrix控制叶子旋转
                Matrix matrix = new Matrix();
                float transX = padding + leaf.x;
                float transY = padding + leaf.y;
            //    Log.i(TAG, "left.x = " + leaf.x + "--leaf.y=" + leaf.y);
                matrix.postTranslate(transX, transY);
                // 通过时间关联旋转角度，则可以直接通过修改LEAF_ROTATE_TIME调节叶子旋转快慢
                float rotateFraction = ((currentTime - leaf.startTime) % mLeafRotateTime)
                        / (float) mLeafRotateTime;
                int angle = (int) (rotateFraction * 360);
                // 根据叶子旋转方向确定叶子旋转角度
                int rotate = leaf.rotateDirection == 0 ? angle + leaf.rotateAngle : -angle
                        + leaf.rotateAngle;
                matrix.postRotate(rotate, transX
                        + mLeafWidth / 2, transY + mLeafHeight / 2);
                canvas.drawBitmap(mLeafBitmap, matrix, leafPaint);
                canvas.restore();
            } else {
                continue;
            }
        }
    }

    private void getLeafLocation(Leaf leaf, long currentTime) {
        long intervalTime = currentTime - leaf.startTime;
        mLeafFloatTime = mLeafFloatTime <= 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
        if (intervalTime < 0) {
            return;
        } else if (intervalTime > mLeafFloatTime) {
            leaf.startTime = System.currentTimeMillis()
                    + new Random().nextInt((int) mLeafFloatTime);
        }

        float fraction = (float) intervalTime / mLeafFloatTime;
        leaf.x = (int) (progressWidth - progressWidth * fraction);
        leaf.y = getLocationY(leaf);
    }

    // 通过叶子信息获取当前叶子的Y值
    private int getLocationY(Leaf leaf) {
        // y = A(wx+Q)+h
        float w = (float) ((float) 2 * Math.PI / progressWidth);
        float a = mMiddleAmplitude;
        switch (leaf.type) {
            case LITTLE:
                // 小振幅 ＝ 中等振幅 － 振幅差
                a = mMiddleAmplitude - mAmplitudeDisparity;
                break;
            case MIDDLE:
                a = mMiddleAmplitude;
                break;
            case BIG:
                // 小振幅 ＝ 中等振幅 + 振幅差
                a = mMiddleAmplitude + mAmplitudeDisparity;
                break;
            default:
                break;
        }
        //Log.i(TAG, "---a = " + a + "---w = " + w + "--leaf.x = " + leaf.x);
        return (int) (a * Math.sin(w * leaf.x)) + mArcRadius * 2 / 3;
    }

    public void drawProgress(Canvas canvas){
        if(progress>=TOTAL_PROGRESS){
            if(rePeat){
                progress=0;
            }else{
                progress=TOTAL_PROGRESS;
            }
        }
        mCurrentProgressPosition=(progressWidth-progressHeight/2)*progress/100;
        if(mCurrentProgressPosition>0&&mCurrentProgressPosition<=progressHeight/2){
            rectF_progress_arc.left=padding;
            rectF_progress_arc.top=padding;
            rectF_progress_arc.right=padding+mCurrentProgressPosition*2;
            rectF_progress_arc.bottom=mHeight-padding;
            canvas.drawArc(rectF_progress_arc,90,180,false,progressPaint);
        }else{
            rectF_progress_arc.left=padding;
            rectF_progress_arc.top=padding;
            rectF_progress_arc.right=padding+progressHeight;
            rectF_progress_arc.bottom=padding+progressHeight;
            canvas.drawArc(rectF_progress_arc,90,180,false,progressPaint);
        }
        if(mCurrentProgressPosition>progressHeight/2){
            rectF_progress_rect.left=padding+progressHeight/2;
            rectF_progress_rect.top=padding;
                    rectF_progress_rect.right=padding+mCurrentProgressPosition;
                    rectF_progress_rect.bottom=mHeight-padding;
            canvas.drawRect(rectF_progress_rect,progressPaint);
        }
    }

    public void drawWheel(Canvas canvas){
        canvas.save();
        canvas.translate(mWidth-mHeight/2,mHeight/2);
        canvas.rotate(rotate);
        canvas.drawCircle(0,0,mHeight/2,wheelPaint_wheel);
        canvas.drawCircle(0,0,mHeight/2-wheelPadding,wheelPaint_bg);
        canvas.drawCircle(0,0,wheelPadding/2,wheelPaint_wheel);
        canvas.drawPath(wheelPath,wheelPaint_wheel);
        canvas.restore();
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        timer.cancel();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        },50,50);
        if(rePeat){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(1);
                }
            },50,progressFloatTime/100);
        }

    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                if(rotate==-360){
                    rotate=0;
                }
                rotate-=20;
            }else if(msg.what==1){
                progress++;
            }
        }
    };

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void setWheelPadding(int wheelPadding) {
        this.wheelPadding = wheelPadding;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public void setWheelBgColor(int wheelBgColor) {
        this.wheelBgColor = wheelBgColor;
    }

    public void setWheelColor(int wheelColor) {
        this.wheelColor = wheelColor;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setmMiddleAmplitude(int mMiddleAmplitude) {
        this.mMiddleAmplitude = mMiddleAmplitude;
    }

    public void setmAmplitudeDisparity(int mAmplitudeDisparity) {
        this.mAmplitudeDisparity = mAmplitudeDisparity;
    }

    public void setmLeafRotateTime(long mLeafRotateTime) {
        this.mLeafRotateTime = mLeafRotateTime;
    }

    public void setmLeafFloatTime(long mLeafFloatTime) {
        this.mLeafFloatTime = mLeafFloatTime;
    }

    private enum StartType {
        LITTLE, MIDDLE, BIG
    }

    private class Leaf{
        // 在绘制部分的位置
        float x, y;
        // 控制叶子飘动的幅度
        StartType type;
        // 旋转角度
        int rotateAngle;
        // 旋转方向--0代表顺时针，1代表逆时针
        int rotateDirection;
        // 起始时间(ms)
        long startTime;

    }


    private class LeafFactory {
        private static final int MAX_LEAFS = 6;
        Random random = new Random();

        // 生成一个叶子信息
        public Leaf generateLeaf() {
            Leaf leaf = new Leaf();
            int randomType = random.nextInt(3);
            // 随时类型－ 随机振幅
            StartType type = StartType.MIDDLE;
            switch (randomType) {
                case 0:
                    break;
                case 1:
                    type = StartType.LITTLE;
                    break;
                case 2:
                    type = StartType.BIG;
                    break;
                default:
                    break;
            }
            leaf.type = type;
            // 随机起始的旋转角度
            leaf.rotateAngle = random.nextInt(360);
            // 随机旋转方向（顺时针或逆时针）
            leaf.rotateDirection = random.nextInt(2);
            // 为了产生交错的感觉，让开始的时间有一定的随机性
            mAddTime += random.nextInt((int) (LEAF_FLOAT_TIME * 1.5));
            leaf.startTime = System.currentTimeMillis() -mAddTime;
            return leaf;
        }

        // 根据最大叶子数产生叶子信息
        public List<Leaf> generateLeafs() {
            return generateLeafs(MAX_LEAFS);
        }

        // 根据传入的叶子数量产生叶子信息
        public List<Leaf> generateLeafs(int leafSize) {
            List<Leaf> leafs = new LinkedList<Leaf>();
            for (int i = 0; i < leafSize; i++) {
                leafs.add(generateLeaf());
            }
            return leafs;
        }
    }
}
