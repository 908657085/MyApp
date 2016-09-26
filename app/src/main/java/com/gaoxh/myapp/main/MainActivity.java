package com.gaoxh.myapp.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseActivity;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.HasComponent;
import com.gaoxh.myapp.di.components.DaggerMainActivityComponent;
import com.gaoxh.myapp.di.components.MainActivityComponent;
import com.gaoxh.myapp.di.modules.MainModule;
import com.gaoxh.widgets.CanvasView;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;

/**
 * @author 高雄辉
 * @Description:
 * @date 16/9/19 下午3:31
 */
public class MainActivity extends BaseActivity implements HasComponent<MainActivityComponent> {

    @BindView(R.id.v_canvas)
    CanvasView vCanvas;
    @BindView(R.id.btn_reset)
    FloatingActionButton btnReset;

    private MainActivityComponent component;

    @Inject
    @ContextType(ContextType.Type.Application)
    public Context applicationContext;
    @Inject
    @ContextType(ContextType.Type.Activity)
    public Context context;

    @Inject
    @Named("A")
    public String a;

    @Inject
    @Named("B")
    public String b;

    @Inject
    @Named("C")
    public Lazy<String> c;


    @Override
    public void setView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initializeInjector() {
        component = DaggerMainActivityComponent.builder().applicationComponent(getApplicationComponent()).activityModule(getActivityModule()).mainModule(new MainModule(this)).build();
        component.inject(this);
    }

    @Override
    public MainActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("a======" + a);
        System.out.println("b======" + b);
        System.out.println("c======" + c);
        System.out.println("c.get()=====" + c.get());
        System.out.println("c.get()=====" + c.get());
        ButterKnife.bind(this);
        System.out.println(applicationContext);
        System.out.println(context);
    }

    @OnClick(R.id.btn_reset)
    public void onClick() {
        vCanvas.reset();
    }
}
