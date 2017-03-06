package com.gaoxh.myapp.main.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by admin on 2017/3/6.
 */

public class TestService extends Service {

    private static final String TAG=TestService.class.getName();

    private int item=0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        item++;
        Log.d(TAG,"onBind");
        return new TestBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        item--;
        Log.d(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    public class TestBinder extends Binder{

        public int getCurrentItem(){
            return TestService.this.item;
        }
    }
}
