package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by eyngzui on 2/23/2016.
 */
public class MoviesNewReleasesFragment extends Fragment {
    private final static String TAG = "MoviesNRFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.movies_newreleases, null);
        TextView tv = (TextView)view.findViewById(R.id.id_movies_nr_fg_tv);
        tv.setText("Movies New Releases");
        return view;
    }
}