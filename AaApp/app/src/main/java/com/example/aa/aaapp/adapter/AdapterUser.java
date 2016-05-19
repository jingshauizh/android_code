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
import com.example.aa.aaapp.greendao.model.UserEntity;
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
        List<UserEntity> _Model_UserList = m_Business_User.getNotHideUsers();
       setList(_Model_UserList);
    }

    public Boolean refreshData(){
        List<UserEntity> _Model_UserList = m_Business_User.getNotHideUsers();
        setList(_Model_UserList);
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder _holder;
        if (convertView == null) {

            convertView = this.getmLayoutInflater().inflate(R.layout.user_list_item_layout, null);
            _holder = new Holder();
            _holder.imgUserIcon = (ImageView) convertView.findViewById(R.id.imgUserIcon);
            _holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            convertView.setTag(_holder);
        } else {
            _holder = (Holder) convertView.getTag();
        }
        UserEntity _Model_User = (UserEntity)this.mItemList.get(position);
        if(_holder != null){
            _holder.tvUserName.setText(_Model_User.getUserName());
            _holder.imgUserIcon.setImageResource(R.drawable.ic_person_black_36dp);
        }

        return convertView;
    }
}
