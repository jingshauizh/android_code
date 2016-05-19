package com.example.aa.aaapp.database.interfaces;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.aa.aaapp.model.ModelCategory;

import java.util.List;

/**
 * Created by eqruvvz on 5/18/2016.
 */
public interface SQLiteDALCategoryIF {
    Boolean InsertCategory(ModelCategory p_Info);

    Boolean DeleteCategory(String p_Condition);

    Boolean UpdateCategory(String p_Condition, ModelCategory p_Info);

    Boolean UpdateCategory(String p_Condition, ContentValues p_ContentValues);

    List<ModelCategory> GetCategory(String p_Condition);
    int getCount(String p_Condition);

    Cursor ExecSql(String p_SqlText);
}
