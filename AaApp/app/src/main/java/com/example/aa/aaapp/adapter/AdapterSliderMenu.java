package com.example.aa.aaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aa.aaapp.R;
import com.example.aa.aaapp.adapter.base.AdapterSliderBase;
import com.example.aa.aaapp.controls.SliderMenuItem;
import com.example.aa.aaapp.model.CommonItem;

import java.util.List;

/**
 * Created by Administrator on 16-4-13.
 */
public  class AdapterSliderMenu extends AdapterSliderBase {

    private class Holder {
        TextView tvMenuName;
    }
    public AdapterSliderMenu(Context pContext, List pItemList) {
        super(pContext, pItemList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder _holder;
        if (convertView == null) {

            convertView = this.getmLayoutInflater().inflate(R.layout.slider_list_item, null);
            _holder = new Holder();
            _holder.tvMenuName = (TextView) convertView.findViewById(R.id.tvListItemTitle);
            convertView.setTag(_holder);
        } else {
            _holder = (Holder) convertView.getTag();
        }
        SliderMenuItem _SliderMenuItem = (SliderMenuItem)this.mItemList.get(position);
        _holder.tvMenuName.setText(_SliderMenuItem.getmTitle());
        return convertView;
    }
}
