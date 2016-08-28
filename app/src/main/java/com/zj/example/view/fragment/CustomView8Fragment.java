package com.zj.example.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zj.example.view.R;
import com.zj.example.view.customview8_progressview.ProgressView;

/**
 * Title: CustomView8Fragment
 * Description:
 * Copyright:Copyright(c)2016
 * CreateTime:16/8/27  15:41
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomView8Fragment extends Fragment {

    private ProgressView progressView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customview8, container, false);
        progressView = (ProgressView) view.findViewById(R.id.progressview);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressView.animateX();
    }
}
