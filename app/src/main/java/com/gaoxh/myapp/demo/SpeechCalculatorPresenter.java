package com.gaoxh.myapp.demo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.gaoxh.lib_speech.RecognizerResultParser;
import com.gaoxh.lib_speech.SpeechRecognition;
import com.gaoxh.myapp.base.utils.LogUtil;
import com.gaoxh.myapp.di.ContextType;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;

import javax.inject.Inject;

/**
 * Created by devil on 2017/3/7.
 */

public class SpeechCalculatorPresenter {

    private static final String TAG= LogUtil.getTag(SpeechCalculatorPresenter.class);

    private Context context;

    private SpeechCalculatorModel speechCalculatorModel;

    private SpeechCalculatorView speechCalculatorView;
    private SpeechRecognition speechRecognition;
    private InitListener initListener;
    private RecognizerListener recognizerListener;
    private RecognizerResultParser recognizerResultParser;


    @Inject
    public SpeechCalculatorPresenter(@ContextType(ContextType.Type.Activity) Context context, SpeechCalculatorModel speechCalculatorModel) {
        this.context=context;
        this.speechCalculatorModel=speechCalculatorModel;

    }

    public void setView(SpeechCalculatorView speechCalculatorView){
        this.speechCalculatorView=speechCalculatorView;
    }

    public void destroy(){
        speechCalculatorView=null;
        speechCalculatorModel=null;
        context=null;
    }



    public void initSpeech(){
        speechRecognition=new SpeechRecognition();
        recognizerListener=new RecognizerListener() {
            @Override
            public void onVolumeChanged(int i, byte[] bytes) {
                speechCalculatorView.setVolume(i);
            }

            @Override
            public void onBeginOfSpeech() {
                LogUtil.d(TAG,"语音识别开始");
                speechCalculatorView.showMsg("语音识别开始");
            }

            @Override
            public void onEndOfSpeech() {
                LogUtil.d(TAG,"语音识别结束");
                speechCalculatorView.showMsg("语音识别结束");
            }

            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                LogUtil.d(TAG,"识别到声音");
                recognizerResultParser.parseIatResult(recognizerResult.getResultString());
                if(b){
                    speechCalculatorView.addInstruction(recognizerResultParser.resultBuffer.toString());
                    recognizerResultParser.reset();
                    LogUtil.d(TAG,"识别声音结束");
                    speechRecognition.reStartRecognize();
                }
            }

            @Override
            public void onError(SpeechError speechError) {
                LogUtil.d(TAG,"语音识别错误");
                speechCalculatorView.showMsg(speechError.getErrorDescription());
                speechRecognition.reStartRecognize();
            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        };
        speechRecognition.configRecognizer(context,initListener);
        speechRecognition.setRecognizerListener(recognizerListener);
        recognizerResultParser=new RecognizerResultParser();
    }

    public void startSpeech(){
        speechRecognition.startRecognize();
    }

    private void calculate(){

    }

}
