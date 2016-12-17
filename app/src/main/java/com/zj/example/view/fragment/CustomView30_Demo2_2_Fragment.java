package com.zj.example.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zj.example.view.R;

/**
 * Title: CustomView30_Demo2_1_Fragment
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/12/16  10:25
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomView30_Demo2_2_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customview30_scroll_conflict_demo2_1, container, false);
        return view;
    }


}
