package com.example.aa.aaapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 16-4-7.
 */
public class ActivityBase extends AppCompatActivity {
    protected void showMsg(String msg){
        Integer duration =1000;
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }

    protected void openActivity(Class<?> classA)  {
        Intent intent = new Intent();
        intent.setClass(this,classA);
        startActivity(intent);
    }
}
