package com.example.aa.aaapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aa.aaapp.Util.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Administrator on 16-4-17.
 */
public class SQLiteHelper extends SQLiteOpenHelper{
    private Context mContext;
    private static SQLiteHelper sqh_instance;
    private static SQLiteDataBaseConfig SQLite_DataBase_Config;
    private static Reflection m_Reflection;

    public interface SQLiteDataTable{
        public void onCreate(SQLiteDatabase p_Database);
        public void onUpdate(SQLiteDatabase p_Database);
    }

    private SQLiteHelper(Context p_context) {
        super(p_context, SQLite_DataBase_Config.getmDatabaseName(), null, SQLite_DataBase_Config.getDBVersion());
        this.mContext = p_context;
    }



    public static SQLiteHelper getInstance(Context pContext){
        if(sqh_instance== null){


                    SQLite_DataBase_Config = SQLiteDataBaseConfig.getDb_instance(pContext);
                    sqh_instance = new SQLiteHelper(pContext);



        }
        return sqh_instance;
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("SQLiteHelper.class","1111111111111111111111 onCreate DB ");
        List<String> tableList = SQLite_DataBase_Config.getTables();
        m_Reflection = new Reflection();
        /*for (int i=0;i<tableList.size();i++) {
            try {

                    SQLiteDataTable _SQLiteDataTable =(SQLiteDataTable) m_Reflection.newInstance(
                            tableList.get(i),
                            new Object[] {mContext},
                            new Class[]{Context.class}
                    );
                _SQLiteDataTable.onCreate(db);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

        for(int i=0;i<tableList.size();i++)
        {
            try {
                ((SQLiteDataTable)m_Reflection.newInstance(tableList.get(i), new Object[]{mContext}, new Class[]{Context.class})).onCreate(db);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
