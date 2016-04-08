package com.example.aa.aaapp.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 16-4-8.
 */
public class BodyItem {
    public BodyItem(String itemName, Integer itemImage) {
        this.itemName = itemName;
        this.itemImageInteger = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    private String itemName;

    public Integer getItemImage() {
        return itemImageInteger;
    }

    public void setItemImage(Integer itemImage) {
        this.itemImageInteger = itemImage;
    }

    private Integer itemImageInteger;

}
