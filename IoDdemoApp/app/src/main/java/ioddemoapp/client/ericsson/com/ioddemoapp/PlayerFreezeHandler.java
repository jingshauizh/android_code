package ioddemoapp.client.ericsson.com.ioddemoapp;


import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.VideoView;

/**
 * Created by eshixia on 12/17/2015.
 */
public class PlayerFreezeHandler extends Handler {
    private VideoView mVideoView;
    private ProgressBar mProgressBar;
    private int mStopPosition;
    private String mMovieName;

    public PlayerFreezeHandler(VideoView view, ProgressBar progressBar, String movieName) {
        mVideoView = view;
        mProgressBar = progressBar;
        mMovieName = movieName;
    }

    @Override
    public void handleMessage(Message msg) {
        if(msg.what == PlayerActivity.VIDEO_PAUSE_EVENT) {
            if (mVideoView.isPlaying()) {
                mStopPosition = mVideoView.getCurrentPosition();
                mVideoView.pause();

                mProgressBar.setVisibility(View.VISIBLE);
                HttpClient httpClient = new HttpClient("ABR", mMovieName,
                        HttpClient.MESSAGE_TYPE.MSG_TYPE_BUFFER_START,
                        Config.instance().getDeviceName());
                httpClient.start();

                final int freezeTime = Config.instance().getRandomFreezeTime();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("podcast", "video will play after " + freezeTime);

                        Message message = new Message();
                        message.what = PlayerActivity.VIDEO_RESUME_EVENT;
                        PlayerFreezeHandler.this.sendMessage(message);
                    }
                }, freezeTime);
            }
        } else if(msg.what == PlayerActivity.VIDEO_RESUME_EVENT) {
            mProgressBar.setVisibility(View.INVISIBLE);
            HttpClient httpClient = new HttpClient("ABR", mMovieName,
                    HttpClient.MESSAGE_TYPE.MSG_TYPE_BUFFER_STOP,
                    Config.instance().getDeviceName());
            httpClient.start();

            mVideoView.resume();
            mVideoView.seekTo(mStopPosition);
            mVideoView.start();

            final int freezeInterval = Config.instance().getRandomFreezeInterval();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("podcast", "video needs buffering after " + freezeInterval);

                    Message message = new Message();
                    message.what = PlayerActivity.VIDEO_PAUSE_EVENT;
                    PlayerFreezeHandler.this.sendMessage(message);
                }
            }, freezeInterval);
        }
        super.handleMessage(msg);
    }
}
