package com.gaoxh.myapp.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseActivity;


/**
 * Created by 高雄辉 on 2016/5/3.
 */
public class StartActivity extends BaseActivity {

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=this;
        navigateToMainReact();
    }

    @Override
    public void setView() {
        setContentView(R.layout.activity_start);
    }

    @Override
    public void initializeInjector() {

    }

    private void navigateToMainReact(){
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(context,MainReactActivity.class);
                context.startActivity(intent);
            }
        },2000);
    }
}
