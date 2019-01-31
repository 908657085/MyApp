package com.gaoxh.data.entity.map;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 高雄辉
 * @createDate 19-1-31
 * @description 定位位置信息
 */
public class Location implements Parcelable{
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 经度
     */
    private double longitude;

    /**
     * 定位精度 默认值为0.0f
     */
    private float radius;

    /**
     * 定位描述信息
     */
    private String locationDescribe;
    /**
     * 详细地址信息
     */
    private String addr;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区县
     */
    private String district;
    /**
     * 街道信息
     */
    private String street;

    public Location(double latitude, double longitude, float radius, String locationDescribe, String addr, String country, String province, String city, String district, String street) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.locationDescribe = locationDescribe;
        this.addr = addr;
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
    }

    protected Location(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        radius = in.readFloat();
        locationDescribe = in.readString();
        addr = in.readString();
        country = in.readString();
        province = in.readString();
        city = in.readString();
        district = in.readString();
        street = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeFloat(radius);
        dest.writeString(locationDescribe);
        dest.writeString(addr);
        dest.writeString(country);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(district);
        dest.writeString(street);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public String getLocationDescribe() {
        return locationDescribe;
    }

    public void setLocationDescribe(String locationDescribe) {
        this.locationDescribe = locationDescribe;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", radius=" + radius +
                ", locationDescribe='" + locationDescribe + '\'' +
                ", addr='" + addr + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
