package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by eyngzui on 2/4/2016.
 */
public class ViewPagerAdapter extends PagerAdapter{

    private List<View> mViews;
    private Activity mActivity;
    private static final String SHAREDPREFERENCES_NAME = "first_in_pref";

    public ViewPagerAdapter(List<View> views, Activity activity){
        mViews = views;
        mActivity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(mViews.get(position), 0);
        if (position == mViews.size() - 1 ){
            // add code to login
        }
        return mViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView(mViews.get(position));
    }

    @Override
    public int getCount() {
        if (mViews != null){
            return mViews.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}
