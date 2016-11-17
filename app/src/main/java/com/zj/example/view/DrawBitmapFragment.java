package com.zj.example.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zj.example.view.customview24_drawbitmap.CustomViewBitmap2;

/**
 * Title: DrawBitmapFragment
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/11/16  13:40
 *
 * @author 郑炯
 * @version 1.0
 */
public class DrawBitmapFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customview24_drawbitmap2, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnCheck = (Button) view.findViewById(R.id.btn_check);
        Button btnUnCheck = (Button) view.findViewById(R.id.btn_uncheck);
        final CustomViewBitmap2 customViewBitmap2 = (CustomViewBitmap2) view.findViewById(R.id.customview);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewBitmap2.check();
            }
        });

        btnUnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewBitmap2.unCheck();
            }
        });
    }
}
