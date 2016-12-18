package com.zj.example.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zj.example.view.bean.Item;
import com.zj.example.view.fragment.CustomView2Fragment;
import com.zj.example.view.fragment.CustomView30_Demo1_Fragment;
import com.zj.example.view.fragment.CustomView30_Demo2_Fragment;
import com.zj.example.view.fragment.CustomView3Activity;
import com.zj.example.view.fragment.CustomView8Fragment;
import com.zj.example.view.fragment.CustomView9Fragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Copyright:Copyright(c)2016
 * CreateTime:16/8/14  20:01
 * @author 郑炯
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items.add(new Item("Demo0-getLeft_getX_getRawX_getLocationInWindow", DisplayFragment.class, true, R.layout.fragment_customview0));
        items.add(new Item("Demo0.1-onTouchEvent-事件分发测试", DisplayFragment.class, true, R.layout.fragment_customview0_touchevent));
        items.add(new Item("Demo1-CircleView", DisplayFragment.class, true, R.layout.activity_circleview));
        items.add(new Item("Demo2-CircleView", CustomView2Fragment.class, true));
        items.add(new Item("Demo3-自定义View几种设置属性的方法", CustomView3Activity.class, false));
        items.add(new Item("Demo4-ProgressView", DisplayFragment.class, true, R.layout.activity_customview4));
        items.add(new Item("Demo6-canvas_save,translate,restore", DisplayFragment.class, true, R.layout.fragment_customview6));
        items.add(new Item("Demo7-LinearGradient-Matrix", DisplayFragment.class, true, R.layout.fragment_customview7));
        items.add(new Item("Demo8-ProgressView-Animation", CustomView8Fragment.class, true));
        items.add(new Item("Demo9-VerticalLine-LinearLayout", CustomView9Fragment.class, true));
        items.add(new Item("Demo10-Delivery-status", DisplayFragment.class, true, R.layout.fragment_customview10));
        items.add(new Item("Demo11-TouchMove-getRawX", DisplayFragment.class, true, R.layout.fragment_customview11));
        items.add(new Item("Demo12-TouchMove-getX", DisplayFragment.class, true, R.layout.fragment_customview12));
        items.add(new Item("Demo13-TouchMove-offsetLeftAndRight", DisplayFragment.class, true, R.layout.fragment_customview13));
        items.add(new Item("Demo14-TouchMove-Layoutparams", DisplayFragment.class, true, R.layout.fragment_customview14));
        items.add(new Item("Demo15-TouchMove-scrollby", DisplayFragment.class, true, R.layout.fragment_customview15));
        items.add(new Item("Demo16-TouchMove-scrollto", DisplayFragment.class, true, R.layout.fragment_customview16));
        items.add(new Item("Demo17-TouchMove-scroller", DisplayFragment.class, true, R.layout.fragment_customview17));
        items.add(new Item("Demo18-TouchMove-scroller2", DisplayFragment.class, true, R.layout.fragment_customview18));
        items.add(new Item("Demo19-TouchMove-使用GestureDetector判断是左右滑动还是上下滑动", DisplayFragment.class, true, R.layout.fragment_customview19));
        items.add(new Item("Demo20-TouchMove-自己判断是左右滑动还是上下滑动", DisplayFragment.class, true, R.layout.fragment_customview20));
        items.add(new Item("Demo21-StrokeStyle", DisplayFragment.class, true, R.layout.fragment_stroke_style_layout));
        items.add(new Item("Demo22-PieChatView(圆形百分比控件)", DisplayFragment.class, true, R.layout.fragment_customview22_pie_chat));
        items.add(new Item("Demo23-drawPicture", DisplayFragment.class, true, R.layout.fragment_customview23_picture));
        items.add(new Item("Demo24-drawBitmap", DisplayFragment.class, true, R.layout.fragment_customview24_drawbitmap));
        items.add(new Item("Demo24-drawBitmap2", DrawBitmapFragment.class, true));
        items.add(new Item("Demo25-drawText", DisplayFragment.class, true, R.layout.fragment_customview25_drawtext));
        items.add(new Item("Demo28-垂直滚动textview", DisplayFragment.class, true, R.layout.fragment_customview28_vertical_scroll_textview));
        items.add(new Item("Demo29-垂直滚动textview-用onDraw实现", DisplayFragment.class, true, R.layout.fragment_customview29_vertical_scroll_textview));
        items.add(new Item("Demo30-滑动冲突-horizontalScorllview嵌套viewpager解决方案1", CustomView30_Demo1_Fragment.class, true));
        items.add(new Item("Demo30-滑动冲突-horizontalScorllview嵌套viewpager解决方案2", CustomView30_Demo2_Fragment.class, true));

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new ListAdapter());
    }


    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {

        public class ItemViewHolder extends RecyclerView.ViewHolder{
            private TextView tvName;

            public ItemViewHolder(View itemView) {
                super(itemView);
                tvName = (TextView) itemView.findViewById(R.id.name);
            }

            public void bindData(Item item){
                tvName.setText(item.name);
            }
        }

        class ItemClickListener implements View.OnClickListener{
            Item item;
            ItemClickListener(Item item){
                this.item = item;
            }


            @Override
            public void onClick(View view) {
                Intent intent;
                if (item.isFragment) {
                    intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putExtra("fragment", item.clazz);
                } else {
                    intent = new Intent(MainActivity.this, item.clazz);
                }
                if (item.layout != 0) {
                    intent.putExtra("layout", item.layout);
                }

                startActivity(intent);
            }
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            Item item = items.get(position);

            holder.bindData(item);
            holder.itemView.setOnClickListener(new ItemClickListener(item));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}


