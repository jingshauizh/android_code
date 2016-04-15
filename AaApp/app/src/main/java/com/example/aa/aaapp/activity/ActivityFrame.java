package com.example.aa.aaapp.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.aa.aaapp.controls.SliderMenuItem;
import com.example.aa.aaapp.controls.SliderMenuView;
import com.example.aa.aaapp.model.BodyItem;
import com.example.aa.aaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-4-7.
 */
public class ActivityFrame extends ActivityBase {
    protected List<BodyItem> _lBodyItemList;

    private SliderMenuView _SliderMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);



    }

    protected void appendMainBody(int pResId){
        LinearLayout _MainBody = (LinearLayout)findViewById(R.id.layMainbody);
        View _View = LayoutInflater.from(this).inflate(pResId,null);
        RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        _MainBody.addView(_View, _LayoutParams);
    }

    protected void createSliderMenu(int resId){
        _SliderMenuView = new SliderMenuView(this);
        String [] _menuItemArray = this.getResources().getStringArray(resId);
        for(int i=0; i<_menuItemArray.length;i++){
            SliderMenuItem _item = new SliderMenuItem();
            _item.setmId(i);
            _item.setmTitle(_menuItemArray[i]);
            _SliderMenuView.add(_item);
        }
        _SliderMenuView.bindList();
    }

    protected void intItem(){
        BodyItem _BodyItem0 = new BodyItem(this.getString(R.string.app_add),R.drawable.ic_cake_black_48dp);
        BodyItem _BodyItem1 = new BodyItem(this.getString(R.string.app_category),R.drawable.ic_domain_black_48dp);
        BodyItem _BodyItem2 = new BodyItem(this.getString(R.string.app_mamber_manage),R.drawable.ic_group_black_48dp);
        BodyItem _BodyItem3 = new BodyItem(this.getString(R.string.app_manage),R.drawable.ic_location_city_black_48dp);
        BodyItem _BodyItem4 = new BodyItem(this.getString(R.string.app_my_record),R.drawable.ic_mood_black_48dp);
        BodyItem _BodyItem5 = new BodyItem(this.getString(R.string.app_name),R.drawable.ic_notifications_none_black_48dp);
        BodyItem _BodyItem6 = new BodyItem(this.getString(R.string.app_record_manage),R.drawable.ic_pages_black_48dp);
        BodyItem _BodyItem7 = new BodyItem(this.getString(R.string.app_stat_manage),R.drawable.ic_party_mode_black_48dp);
        BodyItem _BodyItem8 = new BodyItem(this.getString(R.string.app_search),R.drawable.ic_whatshot_black_48dp);
        _lBodyItemList = new ArrayList<BodyItem>();
        _lBodyItemList.add(_BodyItem0);
        _lBodyItemList.add(_BodyItem1);
        _lBodyItemList.add(_BodyItem2);
        _lBodyItemList.add(_BodyItem3);
        _lBodyItemList.add(_BodyItem4);
        _lBodyItemList.add(_BodyItem5);
        _lBodyItemList.add(_BodyItem6);
        _lBodyItemList.add(_BodyItem7);
        _lBodyItemList.add(_BodyItem8);

    }
}
