package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends Activity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "WelcomeActivity";
    private ViewPager mViewPager;
    private ViewPagerAdapter mVpAdapter;
    private List<View> mViews;
    private ImageView[] dots;
    private int mCurrentIndex;
    private Button mLogInBt;
    private Button mCreateAccountBt;
    private Button mExploreAppBt;
    private TextView mTermsAndConditionsTv;
    private static final String SHAREDPREFERENCES_NAME = "first_in_pref";

    private static  final int[] pics = {R.drawable.welcome_one,R.drawable.welcome_two,
            R.drawable.welcome_three, R.drawable.welcome_four};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initViews();
        initDots();
    }

    private void initViews(){
        LayoutInflater inflater = LayoutInflater.from(this);

        mViews = new ArrayList<View>();
        mViews.add(inflater.inflate(R.layout.welcome_1, null));
        mViews.add(inflater.inflate(R.layout.welcome_2, null));
        mViews.add(inflater.inflate(R.layout.welcome_3, null));
        mViews.add(inflater.inflate(R.layout.welcome_4, null));



        mViewPager = (ViewPager) findViewById(R.id.welcome_viewpager);
        mVpAdapter = new ViewPagerAdapter(mViews, this);
        mViewPager.setAdapter(mVpAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    private void initDots(){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.welcome_dots_ll);
        dots = new ImageView[pics.length];

        for(int i = 0; i < pics.length; i++){
            dots[i] = (ImageView) linearLayout.getChildAt(i);
            dots[i].setEnabled(true);
            //dots[i].setOnClickListener(this);
            dots[i].setTag(i);
        }

        mCurrentIndex = 0;
        dots[mCurrentIndex].setEnabled(false);
    }

    private void setCurDot(int position){
        if(position < 0 || position > pics.length - 1  || mCurrentIndex == position){
            return;
        }
        dots[position].setEnabled(false);
        dots[mCurrentIndex].setEnabled(true);
        mCurrentIndex = position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurDot(position);
        if(position == mViews.size() -1 ){
            mLogInBt = (Button)findViewById(R.id.log_in_bt);
            mCreateAccountBt = (Button)findViewById(R.id.create_account_bt);
            mExploreAppBt = (Button)findViewById(R.id.explore_app_bt);
            mTermsAndConditionsTv = (TextView)findViewById(R.id.terms_and_conditions_tv);
            mLogInBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
                    preferences.edit().putBoolean("isFirstIn", false).commit();
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    WelcomeActivity.this.startActivity(intent);
                    WelcomeActivity.this.finish();
                }
            });

            mCreateAccountBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            mExploreAppBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
