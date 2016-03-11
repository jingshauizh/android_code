package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.app.Activity;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlayerActivity extends Activity {
    private static final String TAG = "PlayerActivity";
    public static final int VIDEO_START_EVENT = 0;
    public static final int VIDEO_PAUSE_EVENT = 1;
    public static final int VIDEO_RESUME_EVENT = 2;
    public static final int VIDEO_SKIP_EVENT = 3;
    private VideoView mVideoView;
    private PlayerFreezeHandler mFreezeHandler;
    private Handler mMovieStartHandler;
    private Handler mSkipHandler;
    private Handler mCoverageFullscreenHandler;
    private ProgressBar mProgressBar;
    private TextView mSkipButton;
    private ImageView mCoverageView;
    private PopupWindow mPopupWindow;
    private int mMovieIndex;
    private String mMovieName;
    private Movie mMovie;
    private String mAdName;
    private String mAdLink;
    private int mAdLinkedPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player);

        Log.d("podcast", "PlayerActivity onCreate");

        mProgressBar = (ProgressBar)findViewById(R.id.progressbar_buffering);
        mVideoView = (VideoView) findViewById(R.id.videoview_player);

        mSkipButton = (TextView)findViewById(R.id.textview_skip);
        if(mSkipButton != null) {
            mSkipButton.setOnClickListener(mSkipListener);
            mSkipButton.setVisibility(View.INVISIBLE);
        }

        mCoverageView = (ImageView)findViewById(R.id.imageview_coverage);
        if(mCoverageView != null) {
            mCoverageView.setVisibility(View.INVISIBLE);
            mCoverageView.setOnClickListener(mCoverageListener);
        }

        mMovieIndex = this.getIntent().getIntExtra("movie_index", 0);
        final Movie[] movies = Config.instance().getMovies();
        if(mMovieIndex >= movies.length || movies[mMovieIndex].getAdaptiveFilename().equals("")) {
            Log.d("podcast", "can not play video because invalid index or empty url");
            return;
        }

        final Movie movie = movies[mMovieIndex];
        mMovie = movie;
        if(movie.isDownloaded() && Config.instance().isPodcastEnabled()) {
            mMovieName = movie.getPrepositionFilename();
        } else {
            mMovieName = movie.getAdaptiveFilename();
        }

        Log.d("podcast", " to play movie " + mMovieName);

        mFreezeHandler = new PlayerFreezeHandler(mVideoView, mProgressBar, mMovieName);

        mMovieStartHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
            if (msg.what == PlayerActivity.VIDEO_START_EVENT) {

                mProgressBar.setVisibility(View.INVISIBLE);
                playVideo();

                if( !Config.instance().isPodcastEnabled() || !movie.isDownloaded()) {
                    final int freezeInterval = Config.instance().getRandomFreezeInterval();
                    Log.d("podcast", "video will buffer after " + freezeInterval + " at beginning");

                    HttpClient httpClient = new HttpClient("ABR", mMovieName,
                            HttpClient.MESSAGE_TYPE.MSG_TYPE_BUFFER_STOP,
                            Config.instance().getDeviceName());
                    httpClient.start();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = PlayerActivity.VIDEO_PAUSE_EVENT;
                            mFreezeHandler.sendMessage(message);
                        }
                    }, freezeInterval);
                }
            }
        }};

        mSkipHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == PlayerActivity.VIDEO_SKIP_EVENT) {
                    mSkipButton.setVisibility(View.VISIBLE);
                    mCoverageView.setVisibility(View.VISIBLE);
                }
            }
        };

        // for use story 2, different ads in different places
        if(mMovieIndex == 1) {
            // movie 1 will play ads first
            if(Config.instance().isPodcastEnabled()) {
                mAdName = movie.getAds()[0];
                mAdLink = movie.getAdLinks()[0];
                mAdLinkedPage = movie.getAdLinkedPages()[0];
            } else {
                mAdName = movie.getAds()[1];
                mAdLink = movie.getAdLinks()[1];
                mAdLinkedPage = movie.getAdLinkedPages()[1];
            }

            mMovieName = movie.getPrepositionFilename(); // always play prepositioned video for user story 2
            mProgressBar.setVisibility(View.INVISIBLE);
            playAd();
            return;
        }

        // for mMovieIndex = 0, use story 1
        if( movies[mMovieIndex].isDownloaded() && Config.instance().isPodcastEnabled() )
        {
            Log.d("podcast", "play preposition movie");
            mProgressBar.setVisibility(View.INVISIBLE);

            HttpClient httpClient =
                    new HttpClient("PRP", mMovieName,
                            HttpClient.MESSAGE_TYPE.MSG_TYPE_MOVIE_START,
                            Config.instance().getDeviceName());
            httpClient.start();

            playVideo();
        } else {
            // if podcast is not enabled, we will play it as adaptive movie
            Log.d("podcast", "play adaptive movie");
            mProgressBar.setVisibility(View.VISIBLE);

            HttpClient httpClient =
                    new HttpClient("ABR", mMovieName,
                            HttpClient.MESSAGE_TYPE.MSG_TYPE_MOVIE_START,
                            Config.instance().getDeviceName());
            httpClient.start();

            final int startBufferingTime = Config.instance().getRandomFreezeTime();
            Log.d("podcast", "video needs buffering at beginning " + startBufferingTime);

            httpClient = new HttpClient("ABR", mMovieName,
                    HttpClient.MESSAGE_TYPE.MSG_TYPE_BUFFER_START,
                    Config.instance().getDeviceName());
            httpClient.start();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = PlayerActivity.VIDEO_START_EVENT;
                    mMovieStartHandler.sendMessage(message);
                }
            }, startBufferingTime);
        }
    }

    protected void playAd() {
        //Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.movie1);
        String filepath = Environment.getExternalStorageDirectory() + "/movie/" + mAdName;
        //Toast.makeText(this, filepath, Toast.LENGTH_SHORT).show();
        Uri videoUri = Uri.parse(filepath);
        mVideoView.setVideoURI(videoUri);
        mVideoView.setOnCompletionListener(mAdOnCompletionListener);
        mVideoView.start();

        // show skip button after 5 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = PlayerActivity.VIDEO_SKIP_EVENT;
                mSkipHandler.sendMessage(message);
            }
        }, 5000);
    }

    protected MediaPlayer.OnCompletionListener mAdOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mCoverageView.setVisibility(View.INVISIBLE);
            mSkipButton.setVisibility(View.INVISIBLE);

            playVideo();
        }
    };

    protected MediaPlayer.OnCompletionListener mMovieOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mCoverageView.setVisibility(View.INVISIBLE);
            mSkipButton.setVisibility(View.INVISIBLE);

            HttpClient httpClient =
                    new HttpClient("NA", mMovieName,
                            HttpClient.MESSAGE_TYPE.MSG_TYPE_MOVIE_STOP,
                            Config.instance().getDeviceName());
            httpClient.start();
        }
    };

    protected View.OnClickListener mCoverageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("podcast", "Coverage image is clicked");
            showCoverageFullscreen();
        }
    };

    protected void showCoverageFullscreen() {
        mCoverageFullscreenHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
               if(mPopupWindow != null) {
                   mPopupWindow.dismiss();
               }
            }
        };

        View popupView = getLayoutInflater().inflate(R.layout.popupwindow_caverage, null);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        mPopupWindow.setAnimationStyle(R.anim.abc_fade_in);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        View rootView = findViewById(R.id.layout_root);
        //mPopupWindow.showAsDropDown(rootView, 0, -rootView.getHeight());
        mPopupWindow.showAtLocation(rootView, Gravity.LEFT | Gravity.TOP, 0, 0);

        TextView urlView = (TextView)mPopupWindow.getContentView().findViewById(R.id.textview_url);
        if(urlView != null) {
            urlView.setOnClickListener(mUrlListener);
            urlView.setText(mAdLink);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                mCoverageFullscreenHandler.sendMessage(message);
            }
        }, 10000);
    }

    protected View.OnClickListener mSkipListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mVideoView.stopPlayback();
            mSkipButton.setVisibility(View.INVISIBLE);
            mCoverageView.setVisibility(View.INVISIBLE);

            playVideo();
        }
    };

    protected View.OnClickListener mUrlListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("podcast", "URL is clicked");
            ImageView imageView = (ImageView)mPopupWindow.getContentView().findViewById(R.id.imageview_coverage_full);
            imageView.setImageResource(mAdLinkedPage);
        }
    };

    private class getContentUrlFromRemote extends Thread {
        private String mRemoteUrl;
        private String mContentUrl = "";
        getContentUrlFromRemote(String remoteUrl){
            mRemoteUrl = remoteUrl;
        }
        public String getContentUrl(){
            return mContentUrl;
        }
        @Override
        public void run() {
                String contentUrl = null;
                String responseData = "";
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(mRemoteUrl);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(1000);
                    conn.setReadTimeout(1000);
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        Log.d(TAG, "send http request OK. " + mRemoteUrl);
                        InputStream is = new BufferedInputStream(conn.getInputStream());
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String str;
                        while ((str = br.readLine()) != null) {
                            responseData += str;
                        }
                        Log.d(TAG, "responseData = " + responseData);
                        mContentUrl = responseData;
                        Log.d(TAG, "mContentUrl = " + mContentUrl);
                    } else {
                        Log.d(TAG, "send http request failed. " + mRemoteUrl);
                    }
                } catch (Exception e) {
                    Log.d(TAG, "send http request failed. " + mRemoteUrl);
                    Log.d(TAG, "send http exception " + e.toString());
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
        }
    }

    private class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String remoteUrl = params[0];
            String contentUrl = "";
            String responseData = "";
            HttpURLConnection conn = null;
            try {
                URL url = new URL(remoteUrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(1000);
                conn.setReadTimeout(1000);
                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    Log.d(TAG, "send http request OK. " + remoteUrl);
                    InputStream is = new BufferedInputStream(conn.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String str;
                    while ((str = br.readLine()) != null) {
                        responseData += str;
                    }
                    Log.d(TAG, "responseData = " + responseData);
                    contentUrl = responseData;
                    Log.d(TAG, "mContentUrl = " + contentUrl);
                } else {
                    Log.d(TAG, "send http request failed. " + remoteUrl);
                }
            } catch (Exception e) {
                Log.d(TAG, "send http request failed. " + remoteUrl);
                Log.d(TAG, "send http exception " + e.toString());
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return contentUrl;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i(TAG, "onPostExecute(Result result) called");
            playVideo(result);
        }
    }

    protected void playVideo(String url){
        if(url != "") {
            //String filepath = Environment.getExternalStorageDirectory() + "/movie/" + mMovieName;
            Uri videoUri = Uri.parse(url);
            Log.d(TAG, "play video from " + videoUri.toString());
            mVideoView.setOnCompletionListener(mMovieOnCompletionListener);
            mVideoView.setVideoURI(videoUri);
            mVideoView.start();
        }else {
            Toast.makeText(this, "the url is incorrect: " + url, Toast.LENGTH_LONG).show();
        }

    }

    protected void playVideo() {
        Uri videoUri = null;
        if(!mMovie.isADEnabled() && Config.instance().isRemoteContentEnabled()) {
            //String url = Config.instance().getContentServer() + "/" +  mMovieName;
            String remoteUrl = Config.instance().getContentServer() + "/movie/playback?name=" + mMovieName;
            //String url = getContentUrlFromRemote(remoteUrl);
            /*
            getContentUrlFromRemote gTH = new getContentUrlFromRemote(remoteUrl);
            gTH.start();
            String url = "";
            while((url = gTH.getContentUrl()) == ""){
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    Log.d(TAG, "Exception = " + e.getMessage());
                }
            }
            Log.d(TAG,"## videoUri = " + url);
            videoUri = Uri.parse(url);
            */
            MyTask myTask = new MyTask();
            myTask.execute(remoteUrl);
        } else {
            //Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.movie1);
            String filepath = Environment.getExternalStorageDirectory() + "/movie/" + mMovieName;
            //Toast.makeText(this, filepath, Toast.LENGTH_SHORT).show();
            videoUri = Uri.parse(filepath);
            Log.d(TAG, "play video from " + videoUri.toString());
            mVideoView.setOnCompletionListener(mMovieOnCompletionListener);
            mVideoView.setVideoURI(videoUri);
            mVideoView.start();
        }

//        Log.d("podcast", "play video from " + videoUri.toString());
//
//        mVideoView.setOnCompletionListener(mMovieOnCompletionListener);
//        mVideoView.setVideoURI(videoUri);
//        mVideoView.start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPause() {
        super.onPause();

       HttpClient httpClient =
                new HttpClient("NA", mMovieName,
                        HttpClient.MESSAGE_TYPE.MSG_TYPE_MOVIE_STOP,
                        Config.instance().getDeviceName());
        httpClient.start();
    }
}
