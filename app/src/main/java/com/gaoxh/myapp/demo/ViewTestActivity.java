package com.gaoxh.myapp.demo;

import android.app.Activity;
import android.os.Bundle;

import com.gaoxh.myapp.R;
import com.gaoxh.widgets.VolumeView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/3/9.
 */

public class ViewTestActivity extends Activity {
    @BindView(R.id.v_volume)
    public VolumeView vVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        vVolume.setVolumeState(VolumeView.DEFAULT_VOLUME_STATE_ERROR);

//       new Thread(){
//           @Override
//           public void run() {
//               for(int i=0;i<10;i++){
//                   vVolume.setVolume(new Random().nextInt(35));
//                   try {
//                       Thread.currentThread().sleep(500);
//                   } catch (InterruptedException e) {
//                       e.printStackTrace();
//                   }
//               }
//               vVolume.setVolumeState(VolumeView.DEFAULT_VOLUME_STATE_ERROR);
//           }
//       }.start();
    }
}
