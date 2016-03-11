package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

/**
 * Created by eyngzui on 2/25/2016.
 */
public class MoreSettingsFragment extends Fragment {

    private Switch mPodcastSwitch;
    private EditText mServer;
    private EditText mMaxFreezeInterval;
    private EditText mMinFreezeInterval;
    private EditText mMaxFreezeTime;
    private EditText mMinFreezeTime;
    private Switch mRemoteContentSwitch;
    private EditText mContentServer;
    private EditText mDeviceName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.moresettings_fragment, null);
        Config.instance().readConfig(getActivity());

        mPodcastSwitch = (Switch)view.findViewById(R.id.switch_podcast);
        if(mPodcastSwitch != null) {
            mPodcastSwitch.setChecked(Config.instance().isPodcastEnabled());
            mPodcastSwitch.setOnCheckedChangeListener(mPodcastSwitchListener);
        }

        mRemoteContentSwitch = (Switch)view.findViewById(R.id.switch_remote_content);
        if(mRemoteContentSwitch != null) {
            mRemoteContentSwitch.setChecked(Config.instance().isRemoteContentEnabled());
            mRemoteContentSwitch.setOnCheckedChangeListener(mRemoteContentSwitchListener);
        }

        mServer = (EditText)view.findViewById(R.id.edittext_server);
        if(mServer != null) {
            mServer.setText(Config.instance().getServer());
        }

        mContentServer = (EditText)view.findViewById(R.id.edittext_content_server);
        if(mContentServer != null) {
            mContentServer.setText(Config.instance().getContentServer());
        }

        mMinFreezeInterval = (EditText)view.findViewById(R.id.edittext_min_freeze_interval);
        if(mMinFreezeInterval != null) {
            mMinFreezeInterval.setText("" + Config.instance().getMinFreezeInterval());
        }

        mMaxFreezeInterval = (EditText)view.findViewById(R.id.edittext_max_freeze_interval);
        if(mMaxFreezeInterval != null) {
            mMaxFreezeInterval.setText("" + Config.instance().getMaxFreezeInterval());
        }

        mMinFreezeTime = (EditText)view.findViewById(R.id.edittext_min_freeze_time);
        if(mMinFreezeTime != null) {
            mMinFreezeTime.setText("" + Config.instance().getMinFreezeTime());
        }

        mMaxFreezeTime = (EditText)view.findViewById(R.id.edittext_max_freeze_time);
        if(mMaxFreezeTime != null) {
            mMaxFreezeTime.setText("" + Config.instance().getMaxFreezeTime());
        }

        mDeviceName = (EditText)view.findViewById(R.id.edittext_devicename);
        if(mDeviceName != null) {
            mDeviceName.setText("" + Config.instance().getDeviceName());
        }
        return view;
    }

    private CompoundButton.OnCheckedChangeListener mPodcastSwitchListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton button, boolean checked) {
            Log.d("podcast", "PodcastSwitchListener onCheckedChanged is called");

            Config.instance().setPodcastEnabled(mPodcastSwitch.isChecked());

            Config.instance().saveConfig(getActivity());
        }
    };

    private CompoundButton.OnCheckedChangeListener mRemoteContentSwitchListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton button, boolean checked) {
            Log.d("podcast", "RemoteContentSwitchListener onCheckedChanged is called");

            Config.instance().setRemoteContentEnabled(mRemoteContentSwitch.isChecked());
            Config.instance().saveConfig(getActivity());
        }
    };

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        Log.d("podcast", "onResume is called");

        Config.instance().readConfig(getActivity());
        if(mPodcastSwitch != null) {
            mPodcastSwitch.setChecked(Config.instance().isPodcastEnabled());
        }
        if(mServer != null) {
            mServer.setText(Config.instance().getServer());
        }

        if(mContentServer != null) {
            mContentServer.setText(Config.instance().getContentServer());
        }

        if(mDeviceName != null) {
            mDeviceName.setText("" + Config.instance().getDeviceName());
        }

        if(mMinFreezeInterval != null) {
            mMinFreezeInterval.setText("" + Config.instance().getMinFreezeInterval());
        }

        if(mMaxFreezeTime != null) {
            mMaxFreezeTime.setText("" + Config.instance().getMaxFreezeTime());
        }

        if(mMaxFreezeTime != null) {
            mMaxFreezeTime.setText("" + Config.instance().getMaxFreezeTime());
        }

        if(mMinFreezeTime != null) {
            mMinFreezeTime.setText("" + Config.instance().getMinFreezeTime());
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d("podcast", "onPause is called");

        String server = mServer.getText().toString();
        Log.d("podcast", "server is " + server);
        Config.instance().setServer(server);

        String contentServer = mContentServer.getText().toString();
        Log.d("podcast", "content server is " + contentServer);
        Config.instance().setContentServer(contentServer);

        String deviceName = mDeviceName.getText().toString();
        Log.d("podcast", "device name is " + deviceName);
        Config.instance().setDeviceName(deviceName);

        int maxFreezeInterval = Integer.parseInt(mMaxFreezeInterval.getText().toString());
        Config.instance().setMaxFreezeInterval(maxFreezeInterval);

        int minFreezeInterval = Integer.parseInt(mMinFreezeInterval.getText().toString());
        Config.instance().setMinFreezeInterval(minFreezeInterval);

        int maxFreezeTime = Integer.parseInt(mMaxFreezeTime.getText().toString());
        Config.instance().setMaxFreezeTime(maxFreezeTime);

        int minFreezeTime = Integer.parseInt(mMinFreezeTime.getText().toString());
        Config.instance().setMinFreezeTime(minFreezeTime);

        Config.instance().saveConfig(getActivity());
    }

}
