package com.gaoxh.myapp.main.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.gaoxh.data.entity.map.Location;
import com.gaoxh.data.net.ApiResult;
import com.gaoxh.domain.service.user.MapService;
import com.gaoxh.myapp.base.utils.LogUtil;
import com.gaoxh.myapp.di.components.DaggerLocationServiceComponent;
import com.gaoxh.myapp.di.components.LocationServiceComponent;
import com.gaoxh.myapp.sys.AndroidApplication;

import javax.inject.Inject;

import rx.Observer;

/**
 * @author 高雄辉
 * @createDate 19-1-31
 * @description
 */
public class LocationService extends Service {
    private static final String TAG = LocationService.class.getSimpleName();
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    @Inject
    public MapService mapService;

    private LocationServiceComponent component;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
        initLocation();
    }

    private void initComponent(){
        component = DaggerLocationServiceComponent.builder()
                .applicationComponent(((AndroidApplication)getApplication()).getComponent())
                .build();
        component.inject(this);
    }

    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);


        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认GCJ02
        //GCJ02：国测局坐标；
        //BD09ll：百度经纬度坐标；
        //BD09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

        option.setScanSpan(30*1000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
        //可选，V7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        option.setIsNeedLocationDescribe(true);
        //可选，是否需要位置描述信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的位置信息，此处必须为true

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明

        new Thread() {
            @Override
            public void run() {
                mLocationClient.start();
                //mLocationClient为第二步初始化过的LocationClient对象
                //调用LocationClient的start()方法，便可发起定位请求
                mLocationClient.startIndoorMode();// 开启室内定位模式（重复调用也没问题），开启后，定位SDK会融合各种定位信息（GPS,WI-FI，蓝牙，传感器等）连续平滑的输出定位结果；
            }
        }.start();
    }


    @Override
    public void onDestroy() {
        mLocationClient.stopIndoorMode();
        mLocationClient.stop();
        super.onDestroy();
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation location) {
            String locationDescribe = location.getLocationDescribe();
            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息

            LogUtil.d(TAG, "on location result: " + addr + " " + country + province + city + district + street + " " + locationDescribe);

            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            if (location.getFloor() != null) {
                // 当前支持高精度室内定位
                String buildingID = location.getBuildingID();// 百度内部建筑物ID
                String buildingName = location.getBuildingName();// 百度内部建筑物缩写
                String floor = location.getFloor();// 室内定位的楼层信息，如 f1,f2,b1,b2
                LogUtil.d(TAG, "楼层信息: " + buildingID + " " + buildingName + " " + floor);
                mLocationClient.startIndoorMode();// 开启室内定位模式（重复调用也没问题），开启后，定位SDK会融合各种定位信息（GPS,WI-FI，蓝牙，传感器等）连续平滑的输出定位结果；
            }
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f

            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            final Location currentLocation = new Location(latitude,longitude,radius,locationDescribe,addr,country,province,city,district,street);
            mapService.saveLocation(currentLocation);
            new Thread(){
                @Override
                public void run() {
                   mapService.uploadLocation(currentLocation).subscribe(new Observer<ApiResult>() {
                       @Override
                       public void onCompleted() {
                           Log.d(TAG, "onCompleted: ");
                       }

                       @Override
                       public void onError(Throwable e) {
                           Log.d(TAG, "onError: ");
                       }

                       @Override
                       public void onNext(ApiResult apiResult) {
                           Log.d(TAG, "onNext: ");
                       }
                   });
                }
            }.start();
        }
    }
}
