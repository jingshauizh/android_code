package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class SplashScreenActivity extends Activity {

    boolean mIsFisetIn = false;
    private static final int GOTO_MAIN = 1001;
    private static final int GOTO_WELCOME = 1002;
    //delay time
    private static final long SPLASHSCREEN_SHOW_TIME = 3000;
    private static final String SHAREDPREFERENCES_NAME = "first_in_pref";

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GOTO_MAIN:
                    gotoMain();
                    break;
                case GOTO_WELCOME:
                    gotoWelcome();
                    break;
                default:
                    gotoMain();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
    }

    private void init(){
        SharedPreferences preferences = getSharedPreferences(SHAREDPREFERENCES_NAME, MODE_PRIVATE);
        mIsFisetIn = preferences.getBoolean("isFirstIn", true);

        if(!mIsFisetIn){
            mHandler.sendEmptyMessageDelayed(GOTO_MAIN, SPLASHSCREEN_SHOW_TIME);
        }else{
            mHandler.sendEmptyMessageDelayed(GOTO_WELCOME, SPLASHSCREEN_SHOW_TIME);
        }
    }

    private void gotoMain(){
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        SplashScreenActivity.this.startActivity(intent);
        SplashScreenActivity.this.finish();
    }

    private void gotoWelcome(){
        Intent intent = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
        SplashScreenActivity.this.startActivity(intent);
        SplashScreenActivity.this.finish();
    }
}
