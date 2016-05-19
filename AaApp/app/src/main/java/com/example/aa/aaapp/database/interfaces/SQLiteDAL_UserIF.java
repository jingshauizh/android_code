package com.example.aa.aaapp.database.interfaces;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.aa.aaapp.model.Model_User;

import java.util.List;

/**
 * Created by eqruvvz on 5/18/2016.
 */
public interface SQLiteDAL_UserIF {
    Boolean insertUser(Model_User p_user);

    Boolean deleteUser(String p_condition);

    Boolean updateUser(Model_User p_user, String p_condition);

    Boolean updateUser(String p_Condition, ContentValues pContentValues);

    List<Model_User> getUsers(String p_condition);

    List<Model_User> getUser(String pCondition);

    void initDefaultData(SQLiteDatabase p_Database);
}
