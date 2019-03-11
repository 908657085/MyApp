package com.gaoxh.myapp.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaoxh.myapp.R;
import com.gaoxh.myapp.base.BaseFragment;
import com.gaoxh.myapp.di.ContextType;
import com.gaoxh.myapp.di.components.MainActivityComponent;
import com.gaoxh.myapp.main.activity.MainReactActivity;
import com.gaoxh.myapp.main.activity.MapActivity;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 高雄辉
 * @createDate 19-3-11
 * @description
 */
public class MainFragment extends BaseFragment {

    private MainActivityComponent component;

    @Inject
    @ContextType(ContextType.Type.Activity)
    public Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        component = getComponent(MainActivityComponent.class);
        component.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,view);
        return view;
    }


    @OnClick(R.id.btn_react_native)
    public void navigateToMainReact() {
        Intent intent = new Intent(context, MainReactActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.btn_map)
    public void navigateToMap() {
        Intent intent = new Intent(context, MapActivity.class);
        context.startActivity(intent);
    }
}
