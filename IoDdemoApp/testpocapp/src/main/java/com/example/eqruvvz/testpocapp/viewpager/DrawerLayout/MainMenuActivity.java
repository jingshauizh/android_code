package com.example.eqruvvz.testpocapp.viewpager.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eqruvvz.testpocapp.R;



public class MainMenuActivity extends Activity implements View.OnClickListener {

    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //
        initBtn();
    }

    private void initBtn() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn1:
                intent.setClass(MainMenuActivity.this, FirstDemoActivity.class);
                break;
            case R.id.btn2:
                intent.setClass(MainMenuActivity.this, SecondDemoActivity.class);
                break;
            case R.id.btn3:
                intent.setClass(MainMenuActivity.this, ThirdDemoActivity.class);
                break;
        }
        startActivity(intent);
    }

}
