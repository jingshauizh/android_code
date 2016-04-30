package com.example.aa.aaapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.example.aa.aaapp.activity.ActivityFrame;
import com.example.aa.aaapp.adapter.AdapterUser;
import com.example.aa.aaapp.adapter.BodyAdapter;
import com.example.aa.aaapp.controls.SliderMenuItem;
import com.example.aa.aaapp.controls.SliderMenuView;

public class UserActivity extends ActivityFrame implements SliderMenuView.OnSliderMenuListenerIF {


    private ListView lvUserList;
    private AdapterUser _AdapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        appendMainBody(R.layout.user_layout);

        initVariable();
        initView();
        initListeners();
        bindData();
        this.createSliderMenu(R.array.initDefaultUsername);
    }

    public void initVariable(){
        this.intItem();
        _AdapterUser = new AdapterUser(this);
    }

    public void initView(){
        lvUserList = (ListView)findViewById(R.id.lvUserList);
    }
    public void initListeners(){

    }
    public void bindData(){
        lvUserList.setAdapter(_AdapterUser);
    }

    @Override
    public void onSliderMenuItemClick(View p_View, SliderMenuItem p_SliderMenuItem) {
        this.showMsg("eeeeerrr title="+p_SliderMenuItem.getmTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
