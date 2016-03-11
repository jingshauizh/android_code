package com.example.eqruvvz.testpocapp.viewpager.dotsindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.eqruvvz.testpocapp.R;
import com.example.eqruvvz.testpocapp.viewpager.ArrayFragment;

import java.util.ArrayList;

public class ViewPagerDotsActivity extends FragmentActivity {

    //这个是有多少个 fragment页面
    static final int NUM_ITEMS = 5;
    private MyAdapter    mAdapter;
    private ViewPager    mPager;
    private int  nowPage;
    ArrayList<String>   titleList    = new ArrayList<String>();
    private PagerTabStrip pagerTabStrip;

    private ImageView[] dots;
    // 记录当前选中位置
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_layout_static);
        mAdapter = new MyAdapter(getSupportFragmentManager() );
        pagerTabStrip = (PagerTabStrip)findViewById(R.id.pagertab);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(android.R.color.holo_green_dark));

        pagerTabStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        mPager = (ViewPager)findViewById(R.id.welcome_viewpager);
        mPager.setAdapter(mAdapter);


        titleList.add("第一页");
        titleList.add("第二页");
        titleList.add("第三页");
        titleList.add("第四页");
        titleList.add("第五页");

        // 初始化底部小点
        initDots();
        currentIndex = 0;
        dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initDotsDefaultColor();
                currentIndex = position;
                dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.welcome_dots_ll);
        dots = new ImageView[NUM_ITEMS];
        // 循环取得小点图片
        for (int i = 0; i < NUM_ITEMS; i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);// 都设为灰色
        }
    }

    private void initDotsDefaultColor(){
        for (int i = 0; i < NUM_ITEMS; i++) {
            dots[i].setEnabled(true);
        }
    }



    @Override
    public void onContentChanged(){


    }


    /**
     *  有状态的 ，只会有前3个存在 其他销毁，  前1个， 中间， 下一个
     */
    public  class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        //得到每个item
        @Override
        public Fragment getItem(int position) {
            return ArrayFragment.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
            return titleList.get(position);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
        }
        // 初始化每个页卡选项
        @Override
        public Object instantiateItem(ViewGroup arg0, int arg1) {
            // TODO Auto-generated method stub
            return super.instantiateItem(arg0, arg1);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            System.out.println( "position Destory" + position);
            super.destroyItem(container, position, object);
        }

    }

}
