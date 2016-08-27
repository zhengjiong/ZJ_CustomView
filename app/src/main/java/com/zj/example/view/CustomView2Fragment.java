package com.zj.example.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zj.example.view.customview2_circleview.CustomView;

/**
 * Created by zhengjiong on 15/10/31.
 */
public class CustomView2Fragment extends Fragment {

    private CustomView customView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_customview2, container, false);
        customView = (CustomView) view.findViewById(R.id.customview);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new Thread(customView).start();
    }
}
