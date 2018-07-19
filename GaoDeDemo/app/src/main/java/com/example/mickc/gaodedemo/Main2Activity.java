package com.example.mickc.gaodedemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.mickc.gaodedemo.overlay.DrivingRouteOverlay;

import java.util.List;

public class Main2Activity extends AppCompatActivity  {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE    };
    private MapView mapView;
    private EditText toSpace;
    private Button button;
    private AMap aMap;
    private AMapLocationClient mLocationClient;
    private double jingdu;
    private double weidu;
    private double latitude1;
    private double longitude1;
    private LatLonPoint latLonPoint;
    private LatLonPoint latLonPoint1;
    private String cityMes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mapView.onCreate(savedInstanceState);// 此方法必须重写

        //获取amap对象 改对象用来操作地图
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        //检查权限
        checkPermisson();
    }

    private void initView() {
        mapView = (MapView) findViewById(R.id.map);
        toSpace = findViewById(R.id.mEdit_Name1);
        button = findViewById(R.id.mEdit_But);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dest=toSpace.getText().toString().trim();
                //查询目的地的经纬度坐标
                latLonPoint1 = queryLat(dest);
                //发起算路
                if (latLonPoint1  != null) {
                    routePlay(latLonPoint1);
                }
            }
        });

    }


    //bus路径规划
    private void routePlay( LatLonPoint tospace) {
        //创建路径规划对象
        RouteSearch routeSearch = new RouteSearch(this);
        //设置监听
        routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
            @Override
            public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

            }

            @Override
            public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
               driverSearch(result, errorCode);
            }
            @Override
            public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

            }

            @Override
            public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

            }
        });
        //起点
        LatLonPoint from = new LatLonPoint(jingdu,weidu);
        //设置算路的起始点
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(from,tospace);
        //设置开车的路径规划
        RouteSearch.DriveRouteQuery query1 = new RouteSearch.DriveRouteQuery(fromAndTo, 0, null, null, "");
        routeSearch.calculateDriveRouteAsyn(query1);


    }


    private void driverSearch(DriveRouteResult result, int errorCode) {
        aMap.clear();// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    final DrivePath drivePath = result.getPaths()
                            .get(0);
                    DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                            Main2Activity.this, aMap, drivePath,
                            result.getStartPos(),
                            result.getTargetPos(), null);
                    drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();
                }
            }

        }
    }
    /**
     * 获取目的地的经纬度
     * @param dest  传入一个目的地  返回目的地对于的经纬度坐标点
     */
    private   LatLonPoint queryLat(String dest) {
        //创建一个地理编码查询器
        GeocodeSearch geocoderSearch = new GeocodeSearch(this);
        //给查询器设置一个监听  通过接口回调拿结果   okUtils.getData（url,new CallBack(){}）
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }
            @Override//在监听的 onGeocodeSearched  就可以拿到结果了
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                //在这里拿到目的地的经纬度
                List<GeocodeAddress> geocodeAddressList = geocodeResult.getGeocodeAddressList();
                GeocodeAddress geocodeAddress = geocodeAddressList.get(0);
                latLonPoint = geocodeAddress.getLatLonPoint();
                latitude1 = latLonPoint.getLatitude();
                longitude1 = latLonPoint.getLongitude();
            }
        });
        //传入目的地和所在的城市
        GeocodeQuery query = new GeocodeQuery(dest, cityMes);
        //发起云检索，获取目的地的经纬度坐标
        geocoderSearch.getFromLocationNameAsyn(query);
        return latLonPoint;

    }


    private void dingWeiMethod() {
        //创建一个client 对象
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //给client对象设置个定位的监听 能随时监听位置的变化
        mLocationClient.setLocationListener(aMapLocationListener);

        // 给监听设置一些参数
        AMapLocationClientOption option = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。  设置定位方式
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
         //设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);


        // 调用start方法来开启定位
        if(null != mLocationClient){
            mLocationClient.setLocationOption(option);
            mLocationClient.startLocation();
            if(null != mLocationClient){
                mLocationClient.setLocationOption(option);
                //关闭定位使用下面这行代码 一般放到onDestory方法里面
                // mLocationClient.stopLocation();
                mLocationClient.startLocation();
            }
        }
    }


    private AMapLocationListener  aMapLocationListener=new AMapLocationListener() {

        private CameraUpdate cameraUpdate;

        @Override//我们可以直接通过这个方法里面的参数来直接获取位置信息
        public void onLocationChanged(AMapLocation aMapLocation) {

            //获取经度
            jingdu =aMapLocation.getLatitude();
            //获取维度
            weidu = aMapLocation.getLongitude();
            //获取到城市  在我们的查询目的地的经纬度坐标的时候回用到
            cityMes=aMapLocation.getCity();

            String address=  aMapLocation.getAddress();
            System.out.println("经度+"+jingdu+"唯独"+weidu+address);

            //latlng 表示坐标点    BitmapDescriptorFactory    岳云鹏 夹克和肉丝
              //              .defaultMarker(BitmapDescriptorFactory.HUE_RED)
            LatLng latLng=new LatLng(jingdu,weidu);
            aMap.addMarker(new MarkerOptions().position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            //移动camery 让定位显示到屏幕正中央
            cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                    latLng, 18, 30, 30));
            aMap.moveCamera(cameraUpdate );
        }
    };


    /**
     * 检查是否获取权限
     */
    private void checkPermisson() {

        int permission0 = ActivityCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permission1 = ActivityCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permission2 = ActivityCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.READ_PHONE_STATE);
        int permission3 = ActivityCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission0 != PackageManager.PERMISSION_GRANTED|permission3 != PackageManager.PERMISSION_GRANTED|permission1 != PackageManager.PERMISSION_GRANTED|permission2 != PackageManager.PERMISSION_GRANTED) {
            // 这里是申请权限  requestPermissions  申请权限的结果在result里面拿
            ActivityCompat.requestPermissions(
                    Main2Activity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }else//如果检查权限发现已经获取过了 直接去定位
            dingWeiMethod();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i <grantResults.length ; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                //获取权限成功开始去定位
                dingWeiMethod();

            }
        }
    }


}
