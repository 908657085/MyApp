package com.gaoxh.myapp.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseActivity;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.HasComponent;
import com.gaoxh.myapp.di.components.DaggerMainActivityComponent;
import com.gaoxh.myapp.di.components.MainActivityComponent;
import com.gaoxh.myapp.di.modules.MainModule;
import com.gaoxh.myapp.di.modules.ShareModule;
import com.gaoxh.myapp.main.fragment.NewsFragment;
import com.gaoxh.widgets.BottomTabView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 高雄辉
 * @Description:
 * @date 16/9/19 下午3:31
 */
public class MainActivity extends BaseActivity implements HasComponent<MainActivityComponent> {

    private static final String TAG = MainActivity.class.getName();
    @Inject
    @ContextType(ContextType.Type.Application)
    public Context applicationContext;
    @Inject
    @ContextType(ContextType.Type.Activity)
    public Context context;
    @BindView(R.id.v_main_bottomTab)
    public BottomTabView bottomTabView;
    @BindView(R.id.vp_main)
    public ViewPager mMainVP;
    private MainActivityComponent component;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void setView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initializeInjector() {
        component = DaggerMainActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mainModule(new MainModule(this))
                .shareModule(new ShareModule())
                .build();
        component.inject(this);
    }

    @Override
    public MainActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);
        bottomTabView.setOnCheckedChangeListener(new BottomTabView.onCheckedChangeListener() {
            @Override
            public void checkedChange(int index) {
                Toast.makeText(context, "点击了+" + index, Toast.LENGTH_SHORT).show();
                mMainVP.setCurrentItem(index,true);
            }
        });
        for (int i = 0; i < 4; i++) {
            NewsFragment fragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", "card " + i);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        mMainVP.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mMainVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomTabView.setCheckedItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.btn_react_native)
    public void navigateToMainReact() {
        Intent intent = new Intent(context, MainReactActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.btn_map)
    public void navigateToMap() {
        Intent intent = new Intent(context, MapActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.btn_user)
    public void navigateToUser() {
        Intent intent = new Intent(context, UserActivity.class);
        context.startActivity(intent);
    }
}
