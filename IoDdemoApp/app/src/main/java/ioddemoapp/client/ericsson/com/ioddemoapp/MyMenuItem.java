package ioddemoapp.client.ericsson.com.ioddemoapp;

/**
 * Created by eshixia on 11/23/2015.
 */
public class MyMenuItem {
    protected int mIcon;
    protected String mName;

    public MyMenuItem(int icon, String name) {
        this.mIcon = icon;
        this.mName = name;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
