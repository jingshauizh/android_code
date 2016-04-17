package com.example.aa.aaapp.database.sqldal;

import android.content.Context;
import android.database.Cursor;

import com.example.aa.aaapp.database.SQLiteDALBase;

/**
 * Created by Administrator on 16-4-17.
 */
public class SQLiteDAL_User extends SQLiteDALBase {

    public SQLiteDAL_User(Context p_context) {
        super(p_context);
    }

    @Override
    protected String[] getTableNameAndPK() {
        return new String[0];
    }

    @Override
    protected Object FindModel(Cursor p_Cursor) {
        return null;
    }
}
