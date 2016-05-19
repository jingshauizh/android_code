package com.example.aa.aaapp.database.interfaces;

import android.database.Cursor;

/**
 * Created by eqruvvz on 5/19/2016.
 */
public interface DQLiteBaseIF {
    int getCount(String p_Condition);

    Cursor ExecSql(String p_SqlText);
}
