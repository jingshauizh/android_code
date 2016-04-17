package com.example.aa.aaapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.GridView;

import com.example.aa.aaapp.activity.ActivityBase;
import com.example.aa.aaapp.activity.ActivityFrame;
import com.example.aa.aaapp.adapter.BodyAdapter;
import com.example.aa.aaapp.controls.SliderMenuItem;
import com.example.aa.aaapp.controls.SliderMenuView;

public class MainActivity extends ActivityFrame implements SliderMenuView.OnSliderMenuListenerIF {


    private GridView gvGridView;
    private BodyAdapter _BodyAdapter;

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
        appendMainBody(R.layout.bodygridlayout);

        initVariable();
        initView();
        initListeners();
        bindData();
        this.createSliderMenu(R.array.initDefaultUsername);
    }

    public void initVariable(){
        this.intItem();
        _BodyAdapter = new BodyAdapter(this,this._lBodyItemList);
    }

    public void initView(){
        gvGridView = (GridView)findViewById(R.id.gvGridView);
    }
    public void initListeners(){

    }
    public void bindData(){
        gvGridView.setAdapter(_BodyAdapter);
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
