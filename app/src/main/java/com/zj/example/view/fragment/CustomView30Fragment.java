package com.zj.example.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zj.example.view.R;
import com.zj.example.view.customview30_scroll_conflict.CustomViewPager;
import com.zj.example.view.utils.DisplayUtils;

import java.util.List;

/**
 * Title: CustomView30Fragment
 * Description: ScrollView嵌套Viewpager
 * Copyright:Copyright(c)2016
 * CreateTime:16/12/12  16:18
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomView30Fragment extends Fragment {
    private CustomViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customview30_scroll_conflict, container, false);
        viewPager = (CustomViewPager) view.findViewById(R.id.viewpager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager.setAdapter(new CustomPagerAdapter());
    }

    public class CustomPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.cheese_2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(getContext(), 300)));

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
