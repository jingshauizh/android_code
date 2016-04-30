package com.example.aa.aaapp.database;

import android.content.Context;

import com.example.aa.aaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-4-17.
 */
public class SQLiteDataBaseConfig {
    private static final  String M_DATABASE_NAME="GoDutchDataBase";
    private static final  int DB_VERSION = 1;
    private static SQLiteDataBaseConfig db_instance;
    private static Context m_Context;
    private SQLiteDataBaseConfig(){

    }

    public static SQLiteDataBaseConfig getDb_instance(Context p_context){
        if(db_instance== null){
            synchronized (SQLiteDataBaseConfig.class){
                if(db_instance== null){
                    db_instance = new SQLiteDataBaseConfig();
                    m_Context = p_context;
                }
            }
        }
        return db_instance;
    }

    public String getDataBaseName()
    {
        return M_DATABASE_NAME;
    }

    public static String getmDatabaseName(){
        return M_DATABASE_NAME;
    }

    public   int getDBVersion(){
        return DB_VERSION;
    }

    public  List<String> getTables(){
        List<String> _tableList = new ArrayList<String>();
        String [] _SQLiteDALClassName =m_Context.getResources().getStringArray(R.array.SQLiteDALClassName);
        String path_package = m_Context.getPackageName()+".database.sqldal.";
        for (int i=0;i<_SQLiteDALClassName.length;i++){

            _tableList.add(path_package+_SQLiteDALClassName[i]);
        }
        return _tableList;
    }
}
