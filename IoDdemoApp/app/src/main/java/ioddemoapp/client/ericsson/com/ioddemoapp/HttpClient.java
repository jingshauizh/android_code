package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eshixia on 12/7/2015.
 */
public class HttpClient extends Thread {
    protected String mContentType;
    protected String mMovieName;
    protected MESSAGE_TYPE mMessageType;
    protected String mDeviceName;

    public enum MESSAGE_TYPE {
      MSG_TYPE_MOVIE_START,
      MSG_TYPE_MOVIE_STOP,
      MSG_TYPE_BUFFER_START,
      MSG_TYPE_BUFFER_STOP,
    };

    public HttpClient(String contentType, String movieName, MESSAGE_TYPE messageType, String deviceName) {
        mContentType = contentType;
        mMovieName = movieName;
        mMessageType = messageType;
        mDeviceName = deviceName;
    }

    @Override
    public void run() {
        URL url = null;
        String address = null;

        try {
            switch(mMessageType)
            {
                case MSG_TYPE_MOVIE_START:
                    address = "http://" + Config.instance().getServer()
                            + "/movie/start?name=" + mMovieName + "&device=" + mDeviceName + "&type=" + mContentType;
                    break;
                case MSG_TYPE_MOVIE_STOP:
                    address = "http://" + Config.instance().getServer()
                            + "/movie/stop?name=" + mMovieName + "&device=" + mDeviceName + "&type=" + mContentType;
                    break;
                case MSG_TYPE_BUFFER_START:
                    address = "http://" + Config.instance().getServer() + "/buffering/start?name="
                            + mMovieName + "&device=" + mDeviceName + "&type=" + mContentType;
                    break;
                case MSG_TYPE_BUFFER_STOP:
                    address = "http://" + Config.instance().getServer() + "/buffering/stop?name="
                            + mMovieName + "&device=" + mDeviceName + "&type=" + mContentType;
                    break;
            }

            url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);

            int responseCode = conn.getResponseCode();
            if(responseCode == 200) {
                Log.d("podcast", "send http request OK. " + address);
            } else {
                Log.d("podcast", "send http request failed. " + address);
            }
        } catch (Exception e) {
            Log.d("podcast", "send http request failed. " + address);
            Log.d("podcast", "send http exception " + e.toString());
        }
    }
}
