package ioddemoapp.client.ericsson.com.ioddemoapp;

/**
 * Created by eshixia on 11/24/2015.
 */
public class Movie {
    public final static int PG_MA = 0; // MA 15+
    public final static int PG_M = 1;

    public final static int[] PG_IMAGE_ARRAY = {
            R.drawable.m,
            R.drawable.ma,
    };

    public final static String[] PG_TEXT_ARRAY = {
            "Recommended for mature audience",
            "Not suitable for people under 15. Under 15s must be accompanied by a parent or adult guardian",
    };

    protected int mIcon;
    protected String mName;
    protected String mIntroduction;
    protected String mActors;
    protected boolean mDownloaded;
    protected  int mMark;
    protected int mPortraitPoster;
    protected int mLandscapePoster;
    protected String mAdaptiveFilename;
    protected String mPrepositionFilename;
    protected int mPG;
    protected String[] mAds;
    protected String[] mAdLinks;
    protected boolean mIsADEnabled;

    public int[] getAdLinkedPages() {
        return mAdLinkedPages;
    }

    public void setAdLinkedPages(int[] adLinkedPages) {
        this.mAdLinkedPages = adLinkedPages;
    }

    protected int[] mAdLinkedPages;

    public Movie(int icon, String name, String introduction, String actors, boolean downloaded, int mark,
                 int landscapePoster, int portraitPoster, String adapvieFilename, String prepositionFilename, int pg, boolean isAdEnabled) {
        mIcon = icon;
        mName = name;
        mIntroduction = introduction;
        mActors = actors;
        mDownloaded = downloaded;
        mMark = mark;
        mLandscapePoster = landscapePoster;
        mPortraitPoster = portraitPoster;
        mAdaptiveFilename = adapvieFilename;
        mPrepositionFilename = prepositionFilename;
        mPG = pg;
        mIsADEnabled = isAdEnabled;
    }

    public boolean isADEnabled(){
        return mIsADEnabled;
    }
    public void setAds(String[] ads) {
        mAds = ads;
    }

    public String[] getAds() {
        return mAds;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int mPoster) {
        this.mIcon = mPoster;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getIntroduction() {
        return mIntroduction;
    }

    public void setIntroduction(String mIntroduction) {
        this.mIntroduction = mIntroduction;
    }

    public String getActors() {
        return mActors;
    }

    public void setActors(String actors) {
        this.mActors = actors;
    }

    public boolean isDownloaded() {
        return mDownloaded;
    }

    public void setDownloaded(boolean mDownloaded) {
        this.mDownloaded = mDownloaded;
    }

    public int getMark() {
        return mMark;
    }

    public void setMark(int mMark) {
        this.mMark = mMark;
    }

    public int getPortraitPoster() {
        return mPortraitPoster;
    }

    public void setPortraitPoster(int mPortraitPoster) {
        this.mPortraitPoster = mPortraitPoster;
    }

    public int getLandscapePoster() {
        return mLandscapePoster;
    }

    public void setLandscapePoster(int mLandscapePoster) {
        this.mLandscapePoster = mLandscapePoster;
    }

    public String getAdaptiveFilename() {
        return mAdaptiveFilename;
    }

    public void setAdaptiveFilename(String mFilename) {
        this.mAdaptiveFilename = mFilename;
    }

    public String getPrepositionFilename() {
        return mPrepositionFilename;
    }

    public void setPrepositionFilename(String prepositionFilename) {
        this.mPrepositionFilename = prepositionFilename;
    }

    public int getPG() {
        return mPG;
    }

    public void setPG(int pg) {
        this.mPG = pg;
    }

    public String[] getAdLinks() {
        return mAdLinks;
    }

    public void setAdLinks(String[] adLinks) {
        this.mAdLinks = adLinks;
    }
}
