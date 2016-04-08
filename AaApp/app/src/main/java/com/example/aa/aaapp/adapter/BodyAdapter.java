package com.example.aa.aaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aa.aaapp.R;
import com.example.aa.aaapp.model.BodyItem;

import java.util.List;

/**
 * Created by Administrator on 16-4-8.
 */
public class BodyAdapter extends BaseAdapter {
    private List<BodyItem> _lBodyItemList;
    private Context _mContext;

    private class Holder {
        ImageView imIcon;
        TextView tvText;
    }

    public BodyAdapter(Context mContext,List<BodyItem> bodyItemList) {
        this._mContext = mContext;
        this._lBodyItemList = bodyItemList;
    }


    @Override
    public int getCount() {
        return _lBodyItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return _lBodyItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder _holder;
        if (convertView == null) {
            LayoutInflater _LayoutInflater = LayoutInflater.from(_mContext);
            convertView = _LayoutInflater.inflate(R.layout.layout_grid_item, null);
            _holder = new Holder();
            _holder.imIcon = (ImageView) convertView.findViewById(R.id.img_imageView);
            _holder.tvText = (TextView) convertView.findViewById(R.id.tv_TextView);
            convertView.setTag(_holder);
        } else {
            _holder = (Holder) convertView.getTag();
            _holder.imIcon.setImageResource(_lBodyItemList.get(position).getItemImage());
            _holder.tvText.setText(_lBodyItemList.get(position).getItemName());
        }
        return convertView;
    }




}
