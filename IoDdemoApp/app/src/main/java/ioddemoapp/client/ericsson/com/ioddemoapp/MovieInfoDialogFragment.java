package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by eyngzui on 2/25/2016.
 */
public class MovieInfoDialogFragment extends DialogFragment{

    private ImageView mPlayIcon;
    private ImageView mExitIcon;
    private ImageView mLandscapePosterView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.activity_movie_info, container);
        mPlayIcon = (ImageView)view.findViewById(R.id.id_movie_play_icon);
        if(mPlayIcon != null){
            mPlayIcon.setOnClickListener(mPlayListener);
        }

        mExitIcon = (ImageView)view.findViewById(R.id.id_movie_exit_icon);
        if(mExitIcon != null){
            mExitIcon.setOnClickListener(mExitListener);
        }

        mLandscapePosterView = (ImageView)view.findViewById(R.id.id_movie_landscape_poster_iv);
        mLandscapePosterView.setImageResource(R.drawable.movie1_poster);
        return view;
    }

    private View.OnClickListener mPlayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("podcast", "play button is clicked");

            Intent intent = new Intent(getActivity().getApplication(),
                    PlayerActivity.class);
            intent.putExtra("movie_index", 0);

             startActivity(intent);
        }
    };

    private View.OnClickListener mExitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
