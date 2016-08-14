package com.zj.example.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zj.example.view.bean.Item;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
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


        items.add(new Item("Demo1-CircleView", CircleViewActivity.class));
        items.add(new Item("Demo2-CircleView", CustomView2Activity.class));
        items.add(new Item("Demo3-自定义View几种设置属性的方法", CustomView3Activity.class));
        items.add(new Item("Demo4-ProgressView", CustomView4Activity.class));

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
                startActivity(new Intent(MainActivity.this, item.clazz));
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


