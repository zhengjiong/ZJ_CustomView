package com.zj.example.view.bean;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

/**
 * Title: Item
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/8/14  11:47
 *
 * @author 郑炯
 * @version 1.0
 */
public class Item {
    public String name;
    public Class clazz;

    public Item(String name, Class clazz) {
        this.name = name;
        this.clazz = clazz;
    }
}
