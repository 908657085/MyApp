package com.gaoxh.myapp.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;
import android.widget.Button;

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
        this.context = this;
        navigateToMainReact();
        Button button= (Button) findViewById(R.id.btn_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMainReact();
            }
        });
    }

    @Override
    public void setView() {
        setContentView(R.layout.activity_start);
    }

    @Override
    public void initializeInjector() {

    }

    private void navigateToMainReact() {
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
               // Intent intent = new Intent(context, MainReactActivity.class);
                Intent intent=new Intent(context,DragActivity.class);
                context.startActivity(intent);
            }
        }, 2000);
    }
}
