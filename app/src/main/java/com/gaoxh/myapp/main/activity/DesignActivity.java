package com.gaoxh.myapp.main.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseActivity;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.components.ActivityComponent;
import com.gaoxh.myapp.di.components.DaggerActivityComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 高雄辉
 * @Description:
 * @date 16/10/12 下午2:16
 */
public class DesignActivity extends BaseActivity {

    private static Random random = new Random(255);

    @BindView(R.id.rv_drag)
    RecyclerView rvDrag;

    private ActivityComponent activityComponent;

    @Inject
    @ContextType(ContextType.Type.Application)
    public Context applicationContext;
    @Inject
    @ContextType(ContextType.Type.Activity)
    public Context context;

    private List<int[]> colorList;

    @Override
    public void setView() {
        setContentView(R.layout.activity_design);
    }

    @Override
    public void initializeInjector() {
        activityComponent = DaggerActivityComponent.builder().applicationComponent(getApplicationComponent()).activityModule(getActivityModule()).build();
        activityComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = DesignActivity.this;
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        colorList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int r = random.nextInt();
            int g = random.nextInt();
            int b = random.nextInt();
            colorList.add(new int[]{r, g, b});
        }
        rvDrag.setLayoutManager(new LinearLayoutManager(context));
        rvDrag.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(context).inflate(R.layout.item_recycleview, parent, false);
                return new ViewHolder(v);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                int color = Color.rgb(colorList.get(position)[0], colorList.get(position)[1], colorList.get(position)[2]);
                holder.vDrag.setBackgroundColor(color);
                holder.tvDrag.setText(position + "");
            }

            @Override
            public int getItemCount() {
                return colorList.size();
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN, ItemTouchHelper.UP) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        itemTouchHelper.attachToRecyclerView(rvDrag);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_drag)
        TextView tvDrag;

        @BindView(R.id.v_drag)
        View vDrag;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
