package com.example.aa.aaapp.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.example.aa.aaapp.model.CommonItem;

import java.util.List;

/**
 * Created by Administrator on 16-4-13.
 */
public abstract class AdapterSliderBase extends BaseAdapter{


    protected Context mContext;
    protected List mItemList;
    protected LayoutInflater mLayoutInflater;


    public AdapterSliderBase(Context pContext, List pItemList) {
        this.mContext = pContext;
        this.mItemList = pItemList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public Context getmContext() {
        return mContext;
    }

    public LayoutInflater getmLayoutInflater() {
        return mLayoutInflater;
    }

    public List getList() {
        return mItemList;
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
