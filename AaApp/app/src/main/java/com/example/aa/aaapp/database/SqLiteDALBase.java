package com.example.aa.aaapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-4-17.
 */
public abstract class SQLiteDALBase implements SQLiteHelper.SQLiteDataTable {
    private Context m_context;
    private SQLiteDatabase m_DataBase;

    public SQLiteDALBase(Context p_context){
        this.m_context =p_context;
    }
    protected Context getContext(){
        return this.m_context;
    }

    public SQLiteDatabase getDataBase(){
        if(m_DataBase == null){
            m_DataBase = SQLiteHelper.getInstance(this.m_context).getWritableDatabase();
        }
        return m_DataBase;
    }

    public void beginTransaction(){
        m_DataBase.beginTransaction();
    }

    public void setTransactionSuccessful(){
        m_DataBase.setTransactionSuccessful();
    }

    public void endTransaction(){
        this.m_DataBase.endTransaction();
    }

    protected abstract String[] getTableNameAndPK();

    protected abstract Object FindModel(Cursor p_Cursor);

    public int getCount(String p_Condition){
        String _String[] = getTableNameAndPK();
        Cursor _cursor = ExecSql("Select "+_String[1] +" from "+_String[0] + " where 1=1 "+ p_Condition);
        int _count = _cursor.getCount();
        _cursor.close();
        return _count;
    }

    public int getCount(String p_PK, String p_Tablename, String p_Condition){
        String _String[] ;
        Cursor _cursor = ExecSql("Select "+ p_PK +" from "+p_Tablename + " where 1=1 "+ p_Condition);
        int _count = _cursor.getCount();
        _cursor.close();
        return _count;
    }

    protected List getList(String p_SqlTxt){
        Cursor _cursor = ExecSql(p_SqlTxt);
        return cursorToList(_cursor);
    }

    protected Boolean delete(String p_Tablename, String p_Condition){
        return getDataBase().delete(p_Tablename, " 1=1 "+p_Condition,null) >=0;
    }

    protected List cursorToList(Cursor p_Cursor){
        List _list = new ArrayList();
        while(p_Cursor.moveToNext()){
            Object _Object = FindModel(p_Cursor);
            _list.add(_Object);
        }
        p_Cursor.close();
        return _list;
    }

    public Cursor ExecSql(String p_SqlText){
        return getDataBase().rawQuery(p_SqlText,null);
    }


}
