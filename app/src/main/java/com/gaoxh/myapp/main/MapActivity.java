package com.gaoxh.myapp.main;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.gaoxh.data.entity.map.Location;
import com.gaoxh.domain.service.user.MapService;
import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseActivity;
import com.gaoxh.myapp.base.utils.LogUtil;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.HasComponent;
import com.gaoxh.myapp.di.components.DaggerMapActivityComponent;
import com.gaoxh.myapp.di.components.MapActivityComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 高雄辉
 * @createDate 19-1-7
 * @description
 */
public class MapActivity extends BaseActivity implements HasComponent<MapActivityComponent> {

    private static final String TAG = MapActivity.class.getSimpleName();
    private static final int WHAT_REFRESH_LOCATION = 1;
    private static final long DELAY_REFRESH_LOCATION = 5000;
    @Inject
    @ContextType(ContextType.Type.Application)
    public Context applicationContext;
    @Inject
    @ContextType(ContextType.Type.Activity)
    public Context context;
    @BindView(R.id.v_map)
    public MapView mapView;
    @BindView(R.id.tv_address)
    public TextView addressTV;

    @Inject
    public MapService mapService;
    private MapActivityComponent component;
    private BaiduMap baiduMap;
    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
//原有BDLocationListener接口暂时同步保留。具体介绍请参考后文第四步的说明
    private boolean needZoom = true;
    private volatile boolean needRefresh = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    refreshLocation();
                    if (needRefresh) {
                        sendEmptyMessageDelayed(WHAT_REFRESH_LOCATION, DELAY_REFRESH_LOCATION);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void setView() {
        setContentView(R.layout.activity_map);
    }

    @Override
    public void initializeInjector() {
        component = DaggerMapActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        component.inject(this);
    }

    @Override
    public MapActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        LogUtil.d(TAG, "application: " + applicationContext + " context:" + context + " mapService: " + mapService);
        initMap();
    }

    private void initMap() {
        baiduMap = mapView.getMap();
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);

// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_location);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
        baiduMap.setMyLocationConfiguration(config);
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
        needRefresh = true;
        handler.sendEmptyMessage(WHAT_REFRESH_LOCATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
        needRefresh = false;
        handler.removeMessages(WHAT_REFRESH_LOCATION);
    }

    private void refreshLocation() {
        LogUtil.d(TAG,"refreshLocation");
        Location location = mapService.getCurrentLocation();
        LogUtil.d(TAG,"location: " + location.toString());
        if (location != null) {
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置定位数据
            baiduMap.setMyLocationData(locData);
            if (needZoom) {
                needZoom = false;
                baiduMap.animateMapStatus(MapStatusUpdateFactory.zoomTo(baiduMap.getMaxZoomLevel()));
            }
            addressTV.setText(location.getAddr());
        }

    }


}
