package com.example.aa.aaapp.controls;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.aa.aaapp.R;
import com.example.aa.aaapp.Util.UITool;
import com.example.aa.aaapp.adapter.AdapterSliderMenu;
import com.example.aa.aaapp.adapter.BodyAdapter;
import com.example.aa.aaapp.model.CommonItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-4-13.
 */
public class SliderMenuView {
    private Activity mActivity;
    private List mMenuList;
    private Boolean mIdClosed;
    private RelativeLayout layBottomBox;
    private OnSliderMenuListenerIF m_OnSliderMenuListener;

    public interface OnSliderMenuListenerIF{
        public abstract void onSliderMenuItemClick(View p_View, SliderMenuItem p_SliderMenuItem);
    }


    public SliderMenuView(Activity pActivity) {
        this.mActivity = pActivity;
        this.m_OnSliderMenuListener = (OnSliderMenuListenerIF)pActivity;
        initVariable();
        initView();
        initListeners();
    }

    public void initVariable(){
        mMenuList = new ArrayList();
        mIdClosed = true;
    }

    public void initView(){
        layBottomBox =(RelativeLayout) mActivity.findViewById(R.id.includeBottom);
    }
    public void initListeners(){

        layBottomBox.setOnClickListener(new OnSliderMenuClick());
        layBottomBox.setFocusableInTouchMode(true);
        layBottomBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_UP){
                    toggle();
                }
                return false;
            }
        });
    }

    private void open(){
        RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        _LayoutParams.addRule(RelativeLayout.BELOW,R.id.includeTitle);
        layBottomBox.setLayoutParams(_LayoutParams);
        mIdClosed = false;
    }
    private void close(){
        RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UITool.pixelsFromDP(mActivity,68));
        _LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layBottomBox.setLayoutParams(_LayoutParams);
        mIdClosed = true;
    }
    public void toggle(){
        if(mIdClosed){
            open();
        }
        else{
            close();
        }
    }
    public void add(SliderMenuItem pSliderMenuItem){
        mMenuList.add(pSliderMenuItem);
    }
    public void bindList(){
        AdapterSliderMenu _AdapterSliderMenu = new AdapterSliderMenu(mActivity,mMenuList);
        ListView _ListView =(ListView) mActivity.findViewById(R.id.lvSliderView);
        _ListView.setAdapter(_AdapterSliderMenu);
        _ListView.setOnItemClickListener(new OnSliderItemClick());
    }


    private class OnSliderMenuClick implements OnClickListener{
        @Override
        public void onClick(View v) {
            toggle();
        }
    };

    private class OnSliderItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView p_AdapterView, View p_view, int position, long id) {
            SliderMenuItem _SliderMenuItem = (SliderMenuItem)p_AdapterView.getAdapter().getItem(position);
            m_OnSliderMenuListener.onSliderMenuItemClick(p_view,_SliderMenuItem);
        }
    };
}
