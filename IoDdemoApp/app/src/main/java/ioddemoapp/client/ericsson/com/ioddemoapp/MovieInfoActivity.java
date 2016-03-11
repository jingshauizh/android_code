package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MovieInfoActivity extends Activity {

    private final static String TAG = "MovieInfoActivity";
    private ImageView mPlayIcon;
    private ImageView mExitIcon;
    private ImageView mLandscapePosterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        mPlayIcon = (ImageView)findViewById(R.id.id_movie_play_icon);
        if(mPlayIcon != null){
            mPlayIcon.setOnClickListener(mPlayListener);
        }

        mExitIcon = (ImageView)findViewById(R.id.id_movie_exit_icon);
        if(mExitIcon != null){
            mExitIcon.setOnClickListener(mExitListener);
        }

        mLandscapePosterView = (ImageView)findViewById(R.id.id_movie_landscape_poster_iv);
        mLandscapePosterView.setImageResource(R.drawable.movie1_poster);
    }

    private View.OnClickListener mPlayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("podcast", "play button is clicked");

            Intent intent = new Intent(MovieInfoActivity.this.getApplication(),
                    PlayerActivity.class);
            intent.putExtra("movie_index", 0);

            startActivity(intent);
        }
    };

    private View.OnClickListener mExitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
