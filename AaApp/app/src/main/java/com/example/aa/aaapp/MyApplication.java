package com.example.aa.aaapp;

import android.app.Application;
import android.content.Context;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.example.aa.aaapp.greendao.model.DaoMaster;
import com.example.aa.aaapp.greendao.model.DaoSession;

/**
 * Created by eqruvvz on 5/19/2016.
 */
public class MyApplication extends Application {
    private static MyApplication _instance;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        _instance = this;//
    }
    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, Constants.DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {

        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * toast
     */
    public static void toast(@NonNull CharSequence text) {
        Toast.makeText(_instance, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * toast
     */
    public static void toast(@StringRes int stringRes) {
        Toast.makeText(_instance, stringRes, Toast.LENGTH_SHORT).show();
    }
}
