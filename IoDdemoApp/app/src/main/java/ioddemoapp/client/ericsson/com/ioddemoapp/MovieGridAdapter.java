package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by eshixia on 11/24/2015.
 */
public class MovieGridAdapter extends BaseAdapter {
    private Context mContext;
    private View mViewArray[];
    private Movie[] mMovieItems;

    public MovieGridAdapter(Context context, Movie[] items) {
        mContext = context;
        mMovieItems = items;

        if(mMovieItems == null) {
            return;
        }

        mViewArray = new View[mMovieItems.length];

        for(int i= 0; i < mViewArray.length; i++) {
            mViewArray[i] = View.inflate(mContext, R.layout.adapter_movie, null);
            ImageView imageView = (ImageView)mViewArray[i].findViewById(R.id.imageview_poster);
            ImageView downloadView = (ImageView)mViewArray[i].findViewById(R.id.imageview_download);
            TextView nameView = (TextView)mViewArray[i].findViewById(R.id.text_moviename);

            if(Config.instance().isPodcastEnabled() && items[i].isDownloaded()) {
                downloadView.setVisibility(View.VISIBLE);
            } else {
                downloadView.setVisibility(View.INVISIBLE);
            }

            nameView.setText(items[i].getName());
            imageView.setImageResource(items[i].getIcon());
        }
    }


    public int getCount() {
        return mViewArray.length;
    }

    public View getItem(int position) {
        return mViewArray[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (position < mViewArray.length) {
            return mViewArray[position];
        } else {
            return null;
        }
    }
}
