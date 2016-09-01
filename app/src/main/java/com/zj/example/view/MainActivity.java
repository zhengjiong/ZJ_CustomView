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
        items.add(new Item("Demo20-StrokeStyle", DisplayFragment.class, true, R.layout.fragment_stroke_style_layout));

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


