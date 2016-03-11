package com.example.eqruvvz.testpocapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.eqruvvz.testpocapp.viewpager.ViewPagerActivity;
import com.example.eqruvvz.testpocapp.viewpager.dotsindicator.ViewPagerDotsActivity;
import com.example.eqruvvz.testpocapp.viewpager.dotsindicator.ViewPagerDotsActivityDync;
import com.example.eqruvvz.testpocapp.viewpager.nostate.ViewPagerNoStateActivity;

public class MainActivity extends AppCompatActivity {
    private Button buttonViewPager;
    private Button buttonViewPager2;
    private Button buttonViewPager3;
    private Button buttonViewPager4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        buttonViewPager = (Button)findViewById(R.id.button);
        buttonViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
            }
        });

        buttonViewPager2 = (Button)findViewById(R.id.button2);
        buttonViewPager2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerNoStateActivity.class);
                startActivity(intent);
            }
        });

        buttonViewPager3 = (Button)findViewById(R.id.button3);
        buttonViewPager3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerDotsActivity.class);
                startActivity(intent);
            }
        });

        buttonViewPager4 = (Button)findViewById(R.id.button4);
        buttonViewPager4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerDotsActivityDync.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
