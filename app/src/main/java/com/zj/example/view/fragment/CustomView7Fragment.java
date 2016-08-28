package com.zj.example.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zj.example.view.R;

/**
 * Title: CustomView7Fragment
 * Description:
 * Copyright:Copyright(c)2016
 * CreateTime:16/8/27  12:31
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomView7Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customview7, container, false);
        return view;
    }
}