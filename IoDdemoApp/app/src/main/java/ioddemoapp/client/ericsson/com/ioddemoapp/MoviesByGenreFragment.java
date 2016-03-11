package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by eyngzui on 2/23/2016.
 */
public class MoviesByGenreFragment  extends Fragment {
    private final static String TAG = "MoviesBGFragment";
    private ListView mListView;
    private String[] mListContents;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.movies_bygenre, null);
        mListView = (ListView) view.findViewById(R.id.id_movies_bg_lv);
        mListContents = getActivity().getResources().getStringArray(R.array.movies_by_genre_array);
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.drawer_list_item, mListContents));
        return view;
    }
}
