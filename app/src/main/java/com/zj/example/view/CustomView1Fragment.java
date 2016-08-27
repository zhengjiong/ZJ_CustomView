package com.zj.example.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description:
 * Copyright:Copyright(c)2016
 * CreateTime:16/8/14  11:41
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomView1Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_circleview, container, false);
        return view;
    }

}
