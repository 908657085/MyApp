package com.gaoxh.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by devil on 2017/3/23.
 */

public class BottomTab extends RelativeLayout {


    private Context mContext;

    private Drawable mTabIconNormal;
    private Drawable mTabIconChecked;
    private int mTabTextColorNormal;
    private int mTabTextColorChecked;
    private String mTabText;
    private boolean mTabShowTips;
    private int mTabIconSize;
    private float mTabTextSize;
    private int mTabTextMarginTop;
    private boolean mTabDefaultChecked;

    private boolean mChecked;

    private int mIndex;

    private ImageView iv_tabIcon;
    private TextView tv_tabName;

    public BottomTab(Context context) {
        super(context);
        init(context, null);
    }

    public BottomTab(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BottomTab(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        initAttrs(context,attrs);
        initView();

    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs){
        mContext=context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomTab);
        mTabIconNormal = typedArray.getDrawable(R.styleable.BottomTab_tabIconNormal);
        mTabIconChecked = typedArray.getDrawable(R.styleable.BottomTab_tabIconChecked);
        mTabTextColorNormal = typedArray.getColor(R.styleable.BottomTab_tabTextColorNormal, Color.GRAY);
        mTabTextColorChecked = typedArray.getColor(R.styleable.BottomTab_tabTextColorChecked, Color.DKGRAY);
        mTabText = typedArray.getString(R.styleable.BottomTab_tabText);
        mTabShowTips = typedArray.getBoolean(R.styleable.BottomTab_tabShowTips, false);
        mTabDefaultChecked = typedArray.getBoolean(R.styleable.BottomTab_tabDefaultChecked, false);
        typedArray.recycle();
        mChecked = mTabDefaultChecked;

        float density = getResources().getDisplayMetrics().density;
        mTabIconSize = (int) (20 * density);
        mTabTextSize = 10;
        mTabTextMarginTop = (int) (5 * density);
    }

    private void initView(){
        iv_tabIcon = new ImageView(mContext);
        tv_tabName = new TextView(mContext);
        checkStateChanged();
        tv_tabName.setTextSize(mTabTextSize);
        tv_tabName.setText(mTabText);
        LinearLayout contentLayout = new LinearLayout(mContext);
        contentLayout.setGravity(Gravity.CENTER);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams l_tabIcon = new LinearLayout.LayoutParams(mTabIconSize, mTabIconSize);
        contentLayout.addView(iv_tabIcon, l_tabIcon);
        LinearLayout.LayoutParams l_tabText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        l_tabText.setMargins(0, mTabTextMarginTop, 0, 0);
        contentLayout.addView(tv_tabName, l_tabText);
        LayoutParams l_content = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        l_content.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(contentLayout, l_content);

        this.setClickable(true);
        this.setBackgroundColor(Color.WHITE);
    }


    private void checkStateChanged() {
        if (mChecked) {
            iv_tabIcon.setBackground(mTabIconChecked);
            tv_tabName.setTextColor(mTabTextColorChecked);
        } else {
            iv_tabIcon.setBackground(mTabIconNormal);
            tv_tabName.setTextColor(mTabTextColorNormal);
        }
    }


    public void setCheckState(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            checkStateChanged();
        }
    }


    public boolean getCheckState() {
        return mChecked;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public int getIndex() {
        return mIndex;
    }

}
