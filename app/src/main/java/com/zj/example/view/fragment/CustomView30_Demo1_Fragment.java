package com.zj.example.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zj.example.view.R;
import com.zj.example.view.customview30_scroll_conflict.demo1.CustomViewPager;
import com.zj.example.view.utils.DisplayUtils;

/**
 * Title: CustomView30_Demo1_Fragment
 * Description: 解决horizontalScroll嵌套viewpager滑动冲突的方法有两种:
 * 1.重写HorizontalScrollView(本demo的方案)
 * 2.重写ViewPager
 * CreateTime:16/12/16  10:25
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomView30_Demo1_Fragment extends Fragment {
    private CustomViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customview30_scroll_conflict_demo1, container, false);
        viewPager = (CustomViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter());

        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, DisplayUtils.dp2px(getContext(), 300)));
        return view;
    }

    public class CustomPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
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
