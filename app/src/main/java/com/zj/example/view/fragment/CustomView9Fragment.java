package com.zj.example.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zj.example.view.R;
import com.zj.example.view.customview9_vertical_line.VerticalLineLinearlayout;

/**
 * Title: CustomView9Fragment
 * Description:
 * Copyright:Copyright(c)2016
 * CreateTime:16/8/27  16:02
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomView9Fragment extends Fragment {

    private VerticalLineLinearlayout mVerticalLineLinearlayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customview9, container, false);
        mVerticalLineLinearlayout = (VerticalLineLinearlayout) view.findViewById(R.id.verticalline_linerlayout);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View item1 = getLayoutInflater(savedInstanceState).inflate(R.layout.item_list_refund_detail_layout, mVerticalLineLinearlayout, false);
        View item2 = getLayoutInflater(savedInstanceState).inflate(R.layout.item_list_refund_detail_layout, mVerticalLineLinearlayout, false);
        View item3 = getLayoutInflater(savedInstanceState).inflate(R.layout.item_list_refund_detail_layout, mVerticalLineLinearlayout, false);


        mVerticalLineLinearlayout.addView(item1);
        mVerticalLineLinearlayout.addView(item2);
        mVerticalLineLinearlayout.addView(item3);
    }
}
