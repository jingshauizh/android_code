package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by eyngzui on 2/23/2016.
 */
public class MoviesFragment extends Fragment {
    private final static String TAG = "MoviesFragment";
    private TabPageIndicator mPageIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.movies_fragment, null);
        mPageIndicator = (TabPageIndicator) view.findViewById(R.id.id_movies_indicator);
        mViewPager = (ViewPager) view.findViewById(R.id.id_movies_pager);
        mAdapter = new MoviesTabAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mPageIndicator.setViewPager(mViewPager, 0);
        return view;
    }
}
