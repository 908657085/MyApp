package com.gaoxh.myapp.base.utils;

import android.util.Log;

import com.gaoxh.data.contstants.Constants_Sys;

/**
 * Created by devil on 2017/3/8.
 */

public class LogUtil{

    public static String getTag(Class cls){
        return Constants_Sys.LOG_TAG+cls.getName();
    }

    public static void v(String tag,String msg){
        if(Constants_Sys.DEBUG_MODE){
            Log.v(tag,msg);
        }
    }

    public static void d(String tag,String msg){
        if(Constants_Sys.DEBUG_MODE){
            Log.d(tag,msg);
        }
    }



    public static void i(String tag,String msg){
        if(Constants_Sys.DEBUG_MODE){
            Log.i(tag,msg);
        }
    }
    public static void w(String tag,String msg){
        if(Constants_Sys.DEBUG_MODE){
            Log.w(tag,msg);
        }
    }

    public static void e(String tag,String msg){
        if(Constants_Sys.DEBUG_MODE){
            Log.e(tag,msg);
        }
    }

}
