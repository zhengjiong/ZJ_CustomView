package com.zj.example.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Title: DisplayActivity
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/8/27  12:10
 *
 * @author 郑炯
 * @version 1.0
 */
public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Class fragmentClass = (Class) getIntent().getSerializableExtra("fragment");

            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, Fragment.instantiate(this, fragmentClass.getName()), fragmentClass.getName())
                    .commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
