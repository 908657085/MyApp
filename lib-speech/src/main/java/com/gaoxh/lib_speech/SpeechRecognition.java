package com.gaoxh.lib_speech;

import android.content.Context;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechRecognizer;

/**
 * Created by devil on 2017/3/8.
 */

public class SpeechRecognition {



    public SpeechRecognizer speechRecognizer;

    public RecognizerListener recognizerListener;

    /**
     * 语音识别初始化
     * @param context
     * @param initListener
     */
    public void configRecognizer(Context context, InitListener initListener){
        speechRecognizer=SpeechRecognizer.createRecognizer(context,initListener);
        speechRecognizer.setParameter(SpeechConstant.DOMAIN, "iat");
        speechRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        speechRecognizer.setParameter(SpeechConstant.ACCENT, "mandarin ");
        speechRecognizer.setParameter(SpeechConstant.ASR_PTT, "0");
    }


    public void setRecognizerListener(RecognizerListener recognizerListener) {
        this.recognizerListener = recognizerListener;
    }


    /**
     * 开始语音识别
     *
     */
    public void startRecognize(){
        if(!speechRecognizer.isListening()){
            speechRecognizer.startListening(recognizerListener);
        }
    }

    public void reStartRecognize(){
        if(!speechRecognizer.isListening()){
            speechRecognizer.startListening(recognizerListener);
        }else{
            reStartRecognize();
        }
    }


}
