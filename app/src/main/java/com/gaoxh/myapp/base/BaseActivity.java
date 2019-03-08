package com.gaoxh.myapp.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.gaoxh.myapp.di.components.ApplicationComponent;
import com.gaoxh.myapp.di.modules.ActivityModule;
import com.gaoxh.myapp.sys.AndroidApplication;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Base {@link Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setView();
    initializeInjector();
  }

  public abstract void setView();
  public abstract void initializeInjector();

  protected ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication)getApplication()).getComponent();
  }


  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

  /**
   * 显示 加载
   */
  @Override
  public void showLoading() {

  }

  /**
   * 隐藏 加载
   */
  @Override
  public void hideLoading() {

  }

  public void showMsg(String msg){
    Toast.makeText(BaseActivity.this,msg,Toast.LENGTH_SHORT).show();
  }
}
