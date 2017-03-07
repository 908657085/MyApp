package com.gaoxh.lib_speech;

import android.content.Context;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;


/**
 * Created by devil on 2017/3/7.
 */

public class SpeechUtils {

    public static SpeechUtility initSpeech (Context context){
        return SpeechUtility.createUtility(context, SpeechConstant.APPID +"="+SpeechConfig.APP_ID);
    }

    public static void SpeechRecognize(Context context, InitListener initListener, RecognizerListener recognizerListener){
        SpeechRecognizer speechRecognizer=SpeechRecognizer.createRecognizer(context,initListener);
        speechRecognizer.setParameter(SpeechConstant.DOMAIN, "iat");
        speechRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        speechRecognizer.setParameter(SpeechConstant.ACCENT, "mandarin ");
        speechRecognizer.startListening(recognizerListener);
    }
}
