package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by eyngzui on 2/23/2016.
 */
public class MoviesTabAdapter extends FragmentPagerAdapter {
    private final static String TAG = "MoviesTabAdapter";
    public static final String[] TITLES = new String[] { "BY GENRE", "RECOMMENDED", "NEW RELEASES"};
    private MoviesByGenreFragment mMoviesBGFragment;
    private MoviesRecommendedFragment mMoviesRecFragment;
    private MoviesNewReleasesFragment mMoviesNRFragment;
    private Fragment[] fragments = new Fragment[3];
    private FragmentManager mFragmentManager;
    public MoviesTabAdapter(FragmentManager fm){
        super(fm);
        mFragmentManager = fm;
    }



    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch(i){
            case 0:
                if(mMoviesBGFragment == null){
                    mMoviesBGFragment = new MoviesByGenreFragment();
                }
                fragment = mMoviesBGFragment;
                fragments[0] = fragment;
                break;
            case 1:
                if(mMoviesRecFragment == null) {
                    mMoviesRecFragment = new MoviesRecommendedFragment();
                }
                fragment = mMoviesRecFragment;
                fragments[1] = fragment;
                break;
            case 2:
                if(mMoviesNRFragment == null) {
                    mMoviesNRFragment = new MoviesNewReleasesFragment();
                }
                fragment = mMoviesNRFragment;
                fragments[2] = fragment;
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position % TITLES.length];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

}
