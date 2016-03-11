package ioddemoapp.client.ericsson.com.ioddemoapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.MenuItem;

import java.util.Random;

/**
 * Created by eshixia on 8/12/2015.
 */
public class Config {
    public static final String PREFS_NAME = "Podcast";
    public static final String PROP_PODCAST = "podcast";
    public static final String PROP_SERVER = "server";
    public static final String PROP_MAX_FREEZE_INTERVAL = "max_freeze_interval";
    public static final String PROP_MIN_FREEZE_INTERVAL = "min_freeze_interval";
    public static final String PROP_MAX_FREEZE_TIME = "max_freeze_time";
    public static final String PROP_MIN_FREEZE_TIME = "min_freeze_time";
    public static final String PROP_REMOTE_CONTENT_SWITCH = "remote_content_switch";
    public static final String PROP_CONTENT_SERVER = "content_server";
    public static final String PROP_DEVICE_NAME = "device_name";

    private static Config mInstance = new Config();
    private MenuItem[] mMenuItems = new MenuItem[6];
    private Movie[] mMovies = new Movie[6];
    private int[] mPosters = new int[6];
    private boolean mPodcastEnabled = true;
    private String mServer = "";
    private int mMaxFreezeInterval = 20000;
    private int mMinFreezeInterval = 10000;
    private int mMaxFreezeTime = 5000;
    private int mMinFreezeTime = 2000;
    private boolean mRemoteContentEnabled = false;
    private String mContentServer = "";
    private String mDeviceName = "device1";

    private Config() {

        initMovies();

        mPosters[0] = R.drawable.poster1;
        mPosters[1] = R.drawable.poster2;
        mPosters[2] = R.drawable.poster3;
        mPosters[3] = R.drawable.poster4;
        mPosters[4] = R.drawable.poster5;
        mPosters[5] = R.drawable.poster6;
    }

    private void initMovies() {
        mMovies[0] = new Movie(R.drawable.movie1, "Ex Machina",
                "Caleb, a 26 year old coder at the world's largest internet company, wins a competition to spend a week at a private mountain retreat belonging to Nathan, the reclusive CEO of the company. But when Caleb arrives at the remote location he finds that he will have to participate in a strange and fascinating experiment in which he must interact with the world's first true artificial intelligence, housed in the body of a beautiful robot girl.",
                "Domhnall Gleeson; Oscar Isaac; Alicia Vikander; Corey Johnson",
                true, 5,
                R.drawable.movie1_poster, R.drawable.movie1_poster_portrait, "movie1_adaptive.mp4", "movie1_preposition.mp4",
                Movie.PG_M, false);

        mMovies[1] = new Movie(R.drawable.movie2, "Fast & Furious 7",
                "Dominic's crew thought they'd left the criminal mercenary life behind. They'd defeated international terrorist Owen Shaw. But now, Shaw's brother Deckard is out killing the crew for revenge. Worse, a terrorist, Jakarde, and a shady government official are both competing to steal a computer terrorism program called \"God's Eye\" that can technological devices into a weapon. Torretto must reconvene with his team to stop Shaw and retrieve the God's Eye program.",
                "Vin Diesel; Paul Walker; Jason Statham; Michelle Rodriguez",
                false, 5,
                R.drawable.movie2_poster, R.drawable.movie2_poster_portrait, "movie2_adaptive.mp4", "movie2_preposition.mp4",
                Movie.PG_MA, true);

        // only movie 1 has ad
        String[] ads = {"ad1.mp4", "ad2.mp4"};
        mMovies[1].setAds(ads);
        String[] adlinks ={"https://www.telstra.com.au/coverage-networks/telstra-4gx", "https://www.telstra.com.au/coverage-networks/our-network"};
        mMovies[1].setAdLinks(adlinks);
        int[] adLinkedPages = {R.drawable.web1, R.drawable.web2};
        mMovies[1].setAdLinkedPages(adLinkedPages);

        mMovies[2] = new Movie(R.drawable.movie3, "Ant-Man", "Ant-Man", "", false, 5,
                R.drawable.movie3_poster, R.drawable.movie3_poster_portrait, "", "",
                Movie.PG_M, false);
        mMovies[3] = new Movie(R.drawable.movie4, "Fantastic 4", "Fantastic 4", "", false, 5,
                R.drawable.movie4_poster, R.drawable.movie4_poster_portrait, "", "",
                Movie.PG_M, false);
        mMovies[4] = new Movie(R.drawable.movie5, "Inside Out", "Inside Out", "", false, 5,
                R.drawable.movie5_poster, R.drawable.movie5_poster_portrait, "", "",
                Movie.PG_M, false);
        mMovies[5] = new Movie(R.drawable.movie6, "Ted 2", "Ted 2", "", false, 5,
                R.drawable.movie6_poster, R.drawable.movie6_poster_portrait, "", "",
                Movie.PG_M, false);
    }

    public static Config instance() {
        return mInstance;
    }

    public MenuItem[] getMenuItems() {
        return mMenuItems;
    }

    public Movie[] getMovies()
    {
        return mMovies;
    }

    public int[] getPosters()
    {
        return mPosters;
    }

    public boolean isPodcastEnabled() {
        return mPodcastEnabled;
    }

    public void setPodcastEnabled(boolean mPodcastEnabled) {
        this.mPodcastEnabled = mPodcastEnabled;
    }

    public String getServer() {
        return mServer;
    }

    public void setServer(String mServer) {
        this.mServer = mServer;
    }

    public int getMinFreezeInterval() {
        return mMinFreezeInterval;
    }

    public void setMinFreezeInterval(int mFreezeInterval) {
        this.mMinFreezeInterval = mFreezeInterval;
    }

    public int getMaxFreezeInterval() {
        return mMaxFreezeInterval;
    }

    public void setMaxFreezeInterval(int mFreezeInterval) {
        this.mMaxFreezeInterval = mFreezeInterval;
    }

    public int getMinFreezeTime() {
        return mMinFreezeTime;
    }

    public void setMinFreezeTime(int freezeTime) {
        this.mMinFreezeTime = freezeTime;
    }

    public int getMaxFreezeTime() {
        return mMaxFreezeTime;
    }

    public void setMaxFreezeTime(int freezeTime) {
        this.mMaxFreezeTime = freezeTime;
    }

    public String getContentServer() {
        return mContentServer;
    }

    public void setContentServer(String contentServer) {
        this.mContentServer = contentServer;
    }

    public boolean isRemoteContentEnabled() {
        return mRemoteContentEnabled;
    }

    public void setRemoteContentEnabled(boolean remoteContentEnabled) {
        this.mRemoteContentEnabled = remoteContentEnabled;
    }

    public String getDeviceName() {
        return mDeviceName;
    }

    public void setDeviceName(String deviceName) {
        this.mDeviceName = deviceName;
    }

    public boolean readConfig(Activity activity) {
        SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, 0);
        mPodcastEnabled = settings.getBoolean(PROP_PODCAST, true);
        mServer = settings.getString(PROP_SERVER, "");
        mMaxFreezeInterval = settings.getInt(PROP_MAX_FREEZE_INTERVAL, 20000);
        mMinFreezeInterval = settings.getInt(PROP_MIN_FREEZE_INTERVAL, 10000);
        mMaxFreezeTime = settings.getInt(PROP_MAX_FREEZE_TIME, 5000);
        mMinFreezeTime = settings.getInt(PROP_MIN_FREEZE_TIME, 2000);
        mRemoteContentEnabled = settings.getBoolean(PROP_REMOTE_CONTENT_SWITCH, false);
        mContentServer = settings.getString(PROP_CONTENT_SERVER, "");
        mDeviceName = settings.getString(PROP_DEVICE_NAME, "device1");

        return true;
    }

    public int getRandomFreezeInterval() {
        Random r = new Random();
        int random = r.nextInt(100000);
        int freezeInterval = random%(Config.instance().getMaxFreezeInterval() - Config.instance().getMinFreezeInterval())
                + Config.instance().getMinFreezeInterval();
        return freezeInterval;
    }

    public int getRandomFreezeTime() {
        Random r = new Random();
        int random = r.nextInt(100000);
        int freezeTIme = random%(Config.instance().getMaxFreezeTime() - Config.instance().getMinFreezeTime())
                + Config.instance().getMinFreezeTime();
        return freezeTIme;
    }

    public boolean saveConfig(Activity activity ) {
        SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(PROP_PODCAST, mPodcastEnabled);
        editor.putString(PROP_SERVER, mServer);
        editor.putInt(PROP_MAX_FREEZE_INTERVAL, mMaxFreezeInterval);
        editor.putInt(PROP_MIN_FREEZE_INTERVAL, mMinFreezeInterval);
        editor.putInt(PROP_MAX_FREEZE_TIME, mMaxFreezeTime);
        editor.putInt(PROP_MIN_FREEZE_TIME, mMinFreezeTime);
        editor.putBoolean(PROP_REMOTE_CONTENT_SWITCH, mRemoteContentEnabled);
        editor.putString(PROP_CONTENT_SERVER, mContentServer);
        editor.putString(PROP_DEVICE_NAME, mDeviceName);
        editor.commit();

        return true;
    }
}

