package com.example.aa.aaapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aa.aaapp.R;
import com.example.aa.aaapp.adapter.base.AdapterSliderBase;
import com.example.aa.aaapp.business.Business_User;
import com.example.aa.aaapp.business.base.Business_Base;
import com.example.aa.aaapp.controls.SliderMenuItem;
import com.example.aa.aaapp.model.Model_User;

import java.util.List;

/**
 * Created by Administrator on 16-4-13.
 */
public  class AdapterUser extends AdapterSliderBase {

    private class Holder {
        ImageView imgUserIcon;
        TextView tvUserName;
    }

    private Business_User m_Business_User;
    public AdapterUser(Context pContext) {

        super(pContext, null);
        m_Business_User = new Business_User(pContext);
        List<Model_User> _Model_UserList = m_Business_User.getNotHideUsers();
       setList(_Model_UserList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder _holder;
        if (convertView == null) {

            convertView = this.getmLayoutInflater().inflate(R.layout.slider_list_item, null);
            _holder = new Holder();
            //_holder.imgUserIcon = (ImageView) convertView.findViewById(R.id.imgUserIcon);
            _holder.tvUserName = (TextView) convertView.findViewById(R.id.tvListItemTitle);
            convertView.setTag(_holder);
        } else {
            _holder = (Holder) convertView.getTag();
        }
        Model_User _Model_User = (Model_User)this.mItemList.get(position);
        if(_holder != null){
            _holder.tvUserName.setText(_Model_User.getName());
            //_holder.imgUserIcon.setImageResource(R.drawable.ic_cake_black_18dp);
        }

        return convertView;
    }
}
