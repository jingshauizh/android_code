package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by eyngzui on 2/25/2016.
 */
public class SettingsFragment extends Fragment {
    private TextView mTextViewMore;
    private Boolean mIsMoreSettingShow = false;
    private Fragment fragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, null);
        mTextViewMore = (TextView)view.findViewById(R.id.textview_more);
        if(mTextViewMore != null) {
            mTextViewMore.setOnClickListener(mMoreListener);
        }
        return view;
    }

    private View.OnClickListener mMoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getChildFragmentManager();
            if(fragment == null){
                fragment = new MoreSettingsFragment();

            }
            if(!mIsMoreSettingShow) {
                //fragmentManager.beginTransaction().show(fragment).commit();
                fragmentManager.beginTransaction()
                        .add(R.id.id_more_setting_fl, fragment).commit();
                mIsMoreSettingShow = true;
            }else {
                fragmentManager.beginTransaction().remove(fragment).commit();
                //fragmentManager.beginTransaction().hide(fragment).commit();
                mIsMoreSettingShow = false;
            }
        }
    };
}
