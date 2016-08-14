package com.zj.example.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zj.example.view.customview2.CustomView;

/**
 * Created by zhengjiong on 15/10/31.
 */
public class CustomView2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview2);

        CustomView customView = (CustomView) findViewById(R.id.customview);

        new Thread(customView).start();
    }
}
