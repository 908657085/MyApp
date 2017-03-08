package com.gaoxh.myapp.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.gaoxh.myapp.di.HasComponent;

/**
 * Created by 高雄辉 on 2016/5/24.
 */
public class BaseFragment extends Fragment implements BaseView{
    
    public static  String TAG="Fragment_";



    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        Activity activity=getActivity();
        return componentType.cast(((HasComponent<C>) activity).getComponent());
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
        Toast.makeText(BaseFragment.this.getContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
