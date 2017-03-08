package com.gaoxh.myapp.demo;

import com.gaoxh.myapp.base.BaseView;

/**
 * Created by devil on 2017/3/7.
 */

public interface SpeechCalculatorView extends BaseView{
    void addInstruction(String instruction);
    void clearInstructions();
    void cancelInstruction();
    void setCalculateResult(String calculateResult);
    void setVolume(int i);
}
