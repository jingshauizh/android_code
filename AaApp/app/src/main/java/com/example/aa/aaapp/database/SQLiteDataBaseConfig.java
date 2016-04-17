package com.example.aa.aaapp.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-4-17.
 */
public class SQLiteDataBaseConfig {
    private static final  String M_DATABASE_NAME="";
    private static final  int DB_VERSION = 1;
    private static SQLiteDataBaseConfig db_instance;
    private SQLiteDataBaseConfig(){

    }

    public static SQLiteDataBaseConfig getDb_instance(){
        if(db_instance== null){
            synchronized (SQLiteDataBaseConfig.class){
                if(db_instance== null){
                    db_instance = new SQLiteDataBaseConfig();
                }
            }
        }
        return db_instance;
    }

    public static String getmDatabaseName(){
        return M_DATABASE_NAME;
    }

    public static  int getDBVersion(){
        return DB_VERSION;
    }

    public static List<String> getTables(){
        List<String> _tableList = new ArrayList<String>();
        return _tableList;
    }
}
