package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by eshixia on 8/27/2015.
 */
public class MenuListAdapter extends BaseAdapter {
    private Context mContext;
    private View mViewArray[];
    private MyMenuItem[] mMenuItems;

    public MenuListAdapter(Context context, MyMenuItem[] items) {
        mContext = context;
        mMenuItems = items;

        if(mMenuItems == null) {
            return;
        }

        mViewArray = new View[mMenuItems.length];

        for(int i= 0; i < mViewArray.length; i++) {
            mViewArray[i] = View.inflate(mContext, R.layout.adapter_menu, null);
            ImageView logoView = (ImageView)mViewArray[i].findViewById(R.id.item_icon);
            TextView nameView = (TextView)mViewArray[i].findViewById(R.id.item_name);

            nameView.setText(items[i].getName());
            logoView.setImageResource(items[i].getIcon());
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
