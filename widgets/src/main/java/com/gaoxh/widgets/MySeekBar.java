package com.gaoxh.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by devil on 2017/3/30.
 */

public class MySeekBar extends LinearLayout {
    private static final int SEEK_BAR_MAX_PROGRESS = 100;
    private static final int SEEK_BAR_MIN_VALUE = 0;
    private static final int SEEK_BAR_MAX_VALUE = 100;
    private SeekBar mSeekBar;
    private TextView mTvSeekBarLabel;
    private TextView mTvSeekBarValue;

    private String mSeekBarLabel;
    private int mMaxProgress;
    private int mCurrentProgress;
    private double mMinValue;
    private double mMaxValue;
    private double mCurrentValue;

    private DecimalFormat mDecimalFormat = new DecimalFormat("0.00");

    private OnSeekBarChangeListener mOnSeekBarChangeListener;

    public MySeekBar(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MySeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MySeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.layout_seekbar, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySeekBar);
        for (int i = 0; i < typedArray.length(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.MySeekBar_label) {
                mSeekBarLabel = typedArray.getString(attr);
            } else if (attr == R.styleable.MySeekBar_maxProgress) {
                mMaxProgress = typedArray.getInt(attr, SEEK_BAR_MAX_PROGRESS);
                mCurrentProgress = mMaxProgress / 2;

            } else if (attr == R.styleable.MySeekBar_minValue) {
                mMinValue = typedArray.getFloat(attr, SEEK_BAR_MIN_VALUE);

            } else if (attr == R.styleable.MySeekBar_maxValue) {
                mMaxValue = typedArray.getFloat(attr, SEEK_BAR_MAX_VALUE);
            }
        }

        mSeekBar = (SeekBar) findViewById(R.id.v_seek_bar);
        mTvSeekBarLabel = (TextView) findViewById(R.id.tv_seek_bar_label);
        mTvSeekBarValue = (TextView) findViewById(R.id.tv_seek_bar_value);
        if (TextUtils.isEmpty(mSeekBarLabel)) {
            mTvSeekBarLabel.setVisibility(GONE);
        }
        mTvSeekBarValue.setText(mDecimalFormat.format(mMinValue));
        mSeekBar.setMax(mMaxProgress);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCurrentProgress = progress;
                setSeekBarValue();
                if (fromUser && mOnSeekBarChangeListener != null) {
                    mOnSeekBarChangeListener.onSeekBarChange(mCurrentValue);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setSeekBarValue() {
        mCurrentValue = mCurrentProgress * (mMaxValue - mMinValue) / mMaxProgress + mMinValue;
        mTvSeekBarValue.setText(mDecimalFormat.format(mCurrentValue));
    }

    public void setSeekBarLabel(String seekBarLabel) {
        this.mSeekBarLabel = seekBarLabel;
        if (TextUtils.isEmpty(mSeekBarLabel)) {
            mTvSeekBarLabel.setVisibility(GONE);
        } else {
            mTvSeekBarLabel.setVisibility(VISIBLE);
            mTvSeekBarLabel.setText(mSeekBarLabel);
        }
    }

    public void setMaxProgress(int mMaxProgress) {
        this.mMaxProgress = mMaxProgress;
        mSeekBar.setMax(mMaxProgress);
    }


    public void setMinValue(double minValue) {
        this.mMinValue = minValue;
        setSeekBarValue();
    }

    public void setMaxValue(double maxValue) {
        this.mMaxValue = maxValue;
        setSeekBarValue();
    }

    public void setCurrentProgress(int currentProgress) {
        this.mCurrentProgress = currentProgress;
        mSeekBar.setProgress(mCurrentProgress);
        setSeekBarValue();
    }

    public double getCurrentValue(){
        return mCurrentValue;
    }

    public void setSeekBarCurrentValue(double value) {
        if (value < mMinValue) {
            value = mMinValue;
        }
        if (value > mMaxValue) {
            value = mMaxValue;
        }
        mCurrentProgress = (int) Math.round(value / (mMaxValue - mMinValue));
        mSeekBar.setProgress(mCurrentProgress);
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mOnSeekBarChangeListener = onSeekBarChangeListener;
    }

    public interface OnSeekBarChangeListener {
        void onSeekBarChange(double value);
    }

}
