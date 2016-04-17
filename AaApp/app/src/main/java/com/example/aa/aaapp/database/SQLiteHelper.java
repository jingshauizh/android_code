package com.example.aa.aaapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Administrator on 16-4-17.
 */
public class SQLiteHelper extends SQLiteOpenHelper{
    private Context mContext;
    private static SQLiteHelper sqh_instance;

    private SQLiteHelper(Context p_context) {
        super(p_context, SQLiteDataBaseConfig.getmDatabaseName(), null, SQLiteDataBaseConfig.getDBVersion());
        this.mContext = p_context;
    }

    public static SQLiteHelper getInstance(Context pContext){
        if(sqh_instance== null){
            synchronized (SQLiteDataBaseConfig.class){
                if(sqh_instance== null){
                    sqh_instance = new SQLiteHelper(pContext);
                }
            }
        }
        return sqh_instance;
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        List<String> tableList = SQLiteDataBaseConfig.getTables();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
