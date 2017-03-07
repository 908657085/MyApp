package com.gaoxh.myapp.demo;

import android.content.Context;

import com.gaoxh.myapp.di.ContextType;

import javax.inject.Inject;

/**
 * Created by devil on 2017/3/7.
 */

public class SpeechCalculatorPresenter {

    private Context context;

    private SpeechCalculatorModel speechCalculatorModel;

    private SpeechCalculatorView speechCalculatorView;

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


    public void parseResult(String result){
        speechCalculatorView.addInstruction(result);
    }

    private void calculate(){

    }

}
