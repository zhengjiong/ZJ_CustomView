package com.zj.example.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Copyright:Copyright(c)2016
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
            int layoutRes = getIntent().getIntExtra("layout", 0);

            Fragment instantiate = Fragment.instantiate(this, fragmentClass.getName());
            if (layoutRes != 0) {
                Bundle args = new Bundle();
                args.putInt("layout", layoutRes);
                instantiate.setArguments(args);
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, instantiate, fragmentClass.getName())
                    .commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
