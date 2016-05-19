package com.example.aa.aaapp.database.interfaces;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.aa.aaapp.model.ModelAccountBook;
import com.example.aa.aaapp.model.ModelPayout;

import java.util.List;

/**
 * Created by eqruvvz on 5/18/2016.
 */
public interface SQLiteDALAccountBookIF {
    Boolean InsertAccountBook(ModelAccountBook p_Info);

    Boolean DeleteAccountBook(String p_Condition);

    Boolean UpdateAccountBook(String p_Condition, ModelAccountBook p_Info);

    Boolean UpdateAccountBook(String p_Condition, ContentValues p_ContentValues);

    List<ModelAccountBook> GetAccountBook(String p_Condition);

    int getCount(String p_Condition);

    Cursor ExecSql(String p_SqlText);


}
