package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by eyngzui on 2/23/2016.
 */
public class MoviesRecommendedFragment extends Fragment {
    private final static String TAG = "MoviesRecFragment";
    private GridView mMoviesView;
    private ImageSwitcher mPosterSwitch;
    private int mCurrentPosterIndex = 0;
    private Timer mSwitchTimer = null;
    private TimerTask mSwitchTask = null;
    private Handler mSwitchHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.movies_recommended, null);

        mMoviesView = (GridView) view.findViewById(R.id.id_movies_rec_gridview);
        MovieGridAdapter adapter = new MovieGridAdapter(getActivity().getBaseContext(), Config.instance().getMovies());
        if(mMoviesView != null) {
            mMoviesView.setAdapter(adapter);
            mMoviesView.setOnItemClickListener(mGridListener);
        }
        mPosterSwitch = (ImageSwitcher)view.findViewById(R.id.imageswitcher_poster);
        if(mPosterSwitch != null) {
            mPosterSwitch.setFactory(new ViewSwitcher.ViewFactory() {
                @Override
                public View makeView() {
                    ImageView imageView=new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    imageView.setAdjustViewBounds(true);
                    return imageView;
                }
            });

            mPosterSwitch.setInAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
            mPosterSwitch.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
            mPosterSwitch.setImageResource(Config.instance().getPosters()[mCurrentPosterIndex]);
        }

        mPosterSwitch.setOnTouchListener(new View.OnTouchListener() {
            int downX;
            int upX;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch event = " + event.getAction() );
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mPosterSwitch.getParent().requestDisallowInterceptTouchEvent(true);
                    downX = (int)event.getX();
                    stopTimer();
                    return true;
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    mPosterSwitch.getParent().requestDisallowInterceptTouchEvent(true);
                    upX = (int)event.getX();
                    if(upX - downX > 100){
                        int index  = --mCurrentPosterIndex;
                        index = (index >=0) ? index : 0;
                        mCurrentPosterIndex = index;
                        mPosterSwitch.setImageResource(Config.instance().getPosters()[index]);
                    }else if(downX - upX > 100){
                        int index = ++mCurrentPosterIndex;
                        index = (index < Config.instance().getPosters().length)? index : Config.instance().getPosters().length -1;
                        mCurrentPosterIndex = index;
                        mPosterSwitch.setImageResource(Config.instance().getPosters()[index]);
                    }
                    startTimer();
                    return true;
                }
                return true;
            }
        });

        mSwitchHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mCurrentPosterIndex++;
                if(mCurrentPosterIndex >= Config.instance().getPosters().length) {
                    mCurrentPosterIndex = 0;
                }
                mPosterSwitch.setImageResource(Config.instance().getPosters()[mCurrentPosterIndex]);

                super.handleMessage(msg);
            }
        };

        startTimer();
        return view;
    }

    private void startTimer(){
        if(mSwitchTimer == null){
            mSwitchTimer = new Timer();
        }

        if(mSwitchTask == null){
            mSwitchTask = new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 1;
                    mSwitchHandler.sendMessage(message);
                }
            };
        }
        if(mSwitchTimer != null){
            mSwitchTimer.schedule(mSwitchTask, 5000, 5000);
        }
    }

    private void stopTimer(){
        if(mSwitchTimer != null){
            mSwitchTimer.cancel();
            mSwitchTimer = null;
        }

        if(mSwitchTask != null){
            mSwitchTask.cancel();
            mSwitchTask = null;
        }
    }

    protected AdapterView.OnItemClickListener mGridListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Log.d("Podcast", "CLicked movie " + position);

            Movie[] movies = Config.instance().getMovies();
            if(position < movies.length && !movies[position].getAdaptiveFilename().equals("")) {
                Intent intent = new Intent(getActivity().getApplication(),
                        MovieInfoActivity.class);

                intent.putExtra("movie_index", position);

                startActivity(intent);
//                MovieInfoDialogFragment dialog = new MovieInfoDialogFragment();
//                dialog.show(getChildFragmentManager(),"movieInfoDialog");
            }
        }
    };
}