package com.gaoxh.myapp.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;

import com.gaoxh.lib_share.ShareUtil;
import com.gaoxh.lib_share.bean.ShareItem;
import com.gaoxh.lib_share.bean.ShareParams;
import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseActivity;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.HasComponent;
import com.gaoxh.myapp.di.components.DaggerMainActivityComponent;
import com.gaoxh.myapp.di.components.MainActivityComponent;
import com.gaoxh.myapp.di.modules.MainModule;
import com.gaoxh.myapp.di.modules.ShareModule;
import com.gaoxh.widgets.CanvasView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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
    @BindView(R.id.btn_share)
    FloatingActionButton btnShare;

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

    @Inject
    public Lazy<ShareUtil> shareUtil;

    public Subscription testSubscription;

    @Override
    public void setView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initializeInjector() {
        component = DaggerMainActivityComponent.builder().applicationComponent(getApplicationComponent()).activityModule(getActivityModule()).mainModule(new MainModule(this)).shareModule(new ShareModule()).build();
        component.inject(this);
    }

    @Override
    public MainActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        System.out.println("a======" + a);
//        System.out.println("b======" + b);
//        System.out.println("c======" + c);
//        System.out.println("c.get()=====" + c.get());
//        System.out.println("c.get()=====" + c.get());
        ButterKnife.bind(this);
//        System.out.println(applicationContext);
//        System.out.println(context);
//        testSubscription= Observable.just("test").delay(2000, TimeUnit.MILLISECONDS).observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        System.out.println("延时执行");
//                        System.out.println(s);
//                        System.out.println(a);
//                       // shareUtil.get().share(context,null,null,null);
//                    }
//                });
//        Observable.just("test").delay(1,TimeUnit.SECONDS).observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        System.out.println("开始结束");
//                        MainActivity.this.finish();
//                    }
//                });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick({R.id.btn_reset, R.id.btn_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reset:
                vCanvas.reset();
                break;
            case R.id.btn_share:
                shareUtil.get().share(context, new ShareItem("这是标题!!","http://www.baidu.com","http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&ie=utf-8&in=24401&cl=2&lm=-1&st=-1&step_word=&rn=1&cs=&ln=1998&fmq=1402900904181_R&ic=0&s=&se=1&sme=0&tab=&width=&height=&face=0&is=&istype=2&ist=&jit=&fr=ala&ala=1&alatpl=others&pos=1&pn=1&word=%E5%9B%BE%E7%89%87%20%E8%90%8C%E5%AE%A0&di=0&os=3884057647,3235632142&pi=0&objurl=http%3A%2F%2Fh.hiphotos.bdimg.com%2Falbum%2Fw%253D2048%2Fsign%3D69b2037aca1349547e1eef6462769358%2Fd000baa1cd11728b707d37d9c9fcc3cec2fd2cfc.jpg","QQ分享老不好用了！！！！！"), (new ShareParams.Builder()).build(), new ShareUtil.OnShareCallBackListener() {
                    @Override
                    public void success() {
                        Toast.makeText(context,"分享成功!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void fail() {
                        Toast.makeText(context,"分享失败!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void cancel() {
                        Toast.makeText(context,"分享取消!",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
