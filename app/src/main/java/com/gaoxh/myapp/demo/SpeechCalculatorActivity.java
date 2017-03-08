package com.gaoxh.myapp.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseActivity;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.HasComponent;
import com.gaoxh.myapp.di.components.DaggerSpeechCalculatorActivityComponent;
import com.gaoxh.myapp.di.components.SpeechCalculatorActivityComponent;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by devil on 2017/3/7.
 */

public class SpeechCalculatorActivity extends BaseActivity implements SpeechCalculatorView, HasComponent<SpeechCalculatorActivityComponent> {

    private SpeechCalculatorActivityComponent speechCalculatorActivityComponent;

    @Inject
    @ContextType
    public Context context;
    @Inject
    public SpeechCalculatorPresenter speechCalculatorPresenter;

    @BindView(R.id.rv_speech_calculator)
    public RecyclerView rvSpeechCalculator;
    @BindView(R.id.tv_speech_calculator_result)
    public TextView tvSpeechCalculatorResult;
    @BindView(R.id.tv_speech_calculator_volume)
    public TextView tvSpeechCalculatorVolume;

    @Inject
    public SpeechCalculatorAdapter speechCalculatorAdapter;


    @Override
    public void setView() {
        setContentView(R.layout.activity_speech_calculator);
    }

    @Override
    public void initializeInjector() {
        speechCalculatorActivityComponent = DaggerSpeechCalculatorActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        speechCalculatorActivityComponent.inject(this);
    }

    @Override
    public SpeechCalculatorActivityComponent getComponent() {
        return speechCalculatorActivityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        speechCalculatorPresenter.setView(this);
        speechCalculatorPresenter.initSpeech();
        rvSpeechCalculator.setLayoutManager(new LinearLayoutManager(context));
        rvSpeechCalculator.setAdapter(speechCalculatorAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        speechCalculatorPresenter.startSpeech();
    }

    @Override
    protected void onDestroy() {
        speechCalculatorPresenter.destroy();
        super.onDestroy();
    }


    @Override
    public void addInstruction(String instruction) {
        speechCalculatorAdapter.addInstruction(instruction);
    }

    @Override
    @OnClick(R.id.btn_speech_calculator_reset)
    public void clearInstructions() {
        speechCalculatorAdapter.clear();
        tvSpeechCalculatorResult.setText("");
    }

    @Override
    @OnClick(R.id.btn_speech_calculator_cancel)
    public void cancelInstruction() {

    }

    @Override
    public void setCalculateResult(String calculateResult) {
        tvSpeechCalculatorResult.setText(calculateResult);
    }

    @Override
    public void setVolume(int i) {
        tvSpeechCalculatorVolume.setText(Integer.toString(i));
    }


}
