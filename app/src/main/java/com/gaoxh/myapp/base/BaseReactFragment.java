package com.gaoxh.myapp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.gaoxh.myapp.sys.AndroidApplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author 高雄辉
 * @createDate 19-3-8
 * @description
 */
public abstract class BaseReactFragment extends BaseFragment {

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mReactInstanceManager = ((AndroidApplication) getActivity().getApplication()).getReactNativeHost().getReactInstanceManager();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mReactRootView = new ReactRootView(getActivity());
        return mReactRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mReactRootView.startReactApplication(mReactInstanceManager, getMainComponentName(), null);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mReactRootView.unmountReactApplication();
    }

    protected abstract String getMainComponentName();
}
