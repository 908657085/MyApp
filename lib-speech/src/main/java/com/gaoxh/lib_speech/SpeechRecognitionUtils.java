package com.gaoxh.lib_speech;

import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by devil on 2017/3/8.
 */

public class SpeechRecognitionUtils {


    /**
     * SDK 初始化
     * @param context
     */
    public static SpeechUtility initSpeechSdk (Context context){
        return  SpeechUtility.createUtility(context, SpeechConstant.APPID +"="+ SpeechConfig.APP_ID);
    }


}
