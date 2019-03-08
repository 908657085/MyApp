package com.gaoxh.myapp.main.activity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.react.uimanager.OnLayoutEvent;
import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 高雄辉
 * @Description:
 * @date 16/10/11 下午3:26
 */
public class DragActivity extends BaseActivity {

    private static String TAG = "拖动=====";

    @BindView(R.id.rl_drag)
    RelativeLayout rlDrag;

    @BindView(R.id.img_drag)
    ImageView imgDrag;

    @BindView(R.id.img_drag_shadow)
    ImageView imgDragShadow;

    private RelativeLayout.LayoutParams layoutParams;

    private int width;
    private int height;

    @Override
    public void setView() {
        setContentView(R.layout.activity_drag);
    }

    @Override
    public void initializeInjector() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        imgDrag.setTag("dragImage");
        imgDrag.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData clipData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder builder = new View.DragShadowBuilder(imgDragShadow);
                v.startDrag(clipData, builder, null, 0);
                return true;
            }
        });
        imgDrag.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (width == 0 || height == 0) {
                    width = imgDrag.getMeasuredWidth();
                    height = imgDrag.getMeasuredHeight();
                }
                if (width != 0 && height != 0) {
                    imgDrag.getViewTreeObserver().removeOnPreDrawListener(this);
                }
                return true;
            }
        });
        rlDrag.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.d(TAG, "event.getAction()===ACTION_DRAG_STARTED");
                        Log.d(TAG, "event.getX()" + event.getX());
                        Log.d(TAG, "event.getY()" + event.getY());
                        layoutParams = (RelativeLayout.LayoutParams) imgDrag.getLayoutParams();
                        imgDrag.setBackgroundColor(Color.RED);
                        imgDrag.invalidate();
                        return true;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(TAG, "event.getAction()===ACTION_DRAG_ENTERED");
                        Log.d(TAG, "event.getX()" + event.getX());
                        Log.d(TAG, "event.getY()" + event.getY());
                        imgDrag.setBackgroundColor(Color.CYAN);
                        imgDrag.invalidate();
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        Log.d(TAG, "event.getAction()===ACTION_DRAG_LOCATION");
                        Log.d(TAG, "event.getX()" + event.getX());
                        Log.d(TAG, "event.getY()" + event.getY());
                        layoutParams.leftMargin = (int) event.getX() - width / 2;
                        layoutParams.topMargin = (int) event.getY() - height / 2;
                        imgDrag.setLayoutParams(layoutParams);
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d(TAG, "event.getAction()===ACTION_DRAG_EXITED");
                        Log.d(TAG, "event.getX()" + event.getX());
                        Log.d(TAG, "event.getY()" + event.getY());
                        imgDrag.setBackgroundColor(Color.RED);
                        imgDrag.invalidate();
                        return true;
                    case DragEvent.ACTION_DROP:
                        Log.d(TAG, "event.getAction()===ACTION_DROP");
                        Log.d(TAG, "event.getX()" + event.getX());
                        Log.d(TAG, "event.getY()" + event.getY());
                        imgDrag.setBackgroundColor(Color.BLUE);
                        imgDrag.invalidate();

                        return false;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.d(TAG, "event.getAction()===ACTION_DRAG_ENDED");
                        Log.d(TAG, "event.getX()" + event.getX());
                        Log.d(TAG, "event.getY()" + event.getY());
                        break;
                    default:
                        Log.d(TAG, "event.getAction()" + event.getAction());
                        Log.d(TAG, "event.getX()" + event.getX());
                        Log.d(TAG, "event.getY()" + event.getY());
                        break;
                }
                return true;
            }
        });
    }


}
