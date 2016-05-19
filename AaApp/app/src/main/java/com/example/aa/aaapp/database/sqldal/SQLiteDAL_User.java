package com.example.aa.aaapp.database.sqldal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.AvoidXfermode;

import com.example.aa.aaapp.R;
import com.example.aa.aaapp.database.SQLiteDALBase;
import com.example.aa.aaapp.database.interfaces.SQLiteDAL_UserIF;
import com.example.aa.aaapp.model.Model_User;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 16-4-17.
 */
public class SQLiteDAL_User extends SQLiteDALBase implements SQLiteDAL_UserIF {

    public SQLiteDAL_User(Context p_context) {
        super(p_context);
    }

    public Boolean insertUser(Model_User p_user){
        ContentValues _ContentValues =createParas(p_user);
        long _newId = getDataBase().insert(getTableNameAndPK()[0],null,_ContentValues);
        p_user.setUserId((int) _newId);
        return _newId >0;
    }
    public Boolean deleteUser(String p_condition){
        return delete(getTableNameAndPK()[0],p_condition);
    }

    public Boolean updateUser(Model_User p_user, String p_condition){
        ContentValues _ContentValues =createParas(p_user);
       return getDataBase().update(getTableNameAndPK()[0], _ContentValues, p_condition,null) > 0;
    }

    public Boolean updateUser(String p_Condition,ContentValues pContentValues)
    {
        return getDataBase().update("User", pContentValues, p_Condition, null) > 0;
    }


    public List<Model_User> getUsers(String p_condition){
        String _sql= "  select * from User where 1=1 "+p_condition;
        return getList(_sql);
    }

    public List<Model_User> getUser(String pCondition)
    {
        String _SqlText = "  select * from User where 1=1 " + pCondition;
        return getList(_SqlText);
    }

    @Override
    protected String[] getTableNameAndPK() {
        return new String[]{"User","user_id" };
    }

    @Override
    protected Object FindModel(Cursor p_Cursor) {
        Model_User _modelUser = new Model_User();
        _modelUser.setUserId(p_Cursor.getInt(p_Cursor.getColumnIndex("user_id")));
        _modelUser.setName(p_Cursor.getString(p_Cursor.getColumnIndex("user_name")));
        Calendar cal = Calendar.getInstance();
        long createTime = p_Cursor.getLong(p_Cursor.getColumnIndex("create_date"));
        cal.setTimeInMillis(createTime);
        Date _date = cal.getTime();
        _modelUser.setCreateDate(_date);
        _modelUser.setStatus(Model_User.UserStatus.valueOf(p_Cursor.getString(p_Cursor.getColumnIndex("state"))));
        return _modelUser;
    }

    public void initDefaultData(SQLiteDatabase p_Database){
        Model_User model_user = new Model_User();
        String []  _userStr = getContext().getResources().getStringArray(R.array.initDefaultUsername);
        for (int i=0; i<_userStr.length;i++){
            model_user.setName(_userStr[i]);
            ContentValues _contentValues = createParas(model_user);
            p_Database.insert(getTableNameAndPK()[0],null,_contentValues);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase p_Database) {


        StringBuilder _createBuilder = new StringBuilder();
        _createBuilder.append("     Create  TABLE User( ");
        _createBuilder.append("      [user_id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,");
        _createBuilder.append("      [user_name] varchar(20) NOT NULL ,");
        _createBuilder.append("      [create_date] bigint NOT NULL,");
        _createBuilder.append("      [state] varchar(20) NOT NULL");
        _createBuilder.append("     ) ");
        p_Database.execSQL(_createBuilder.toString());
        initDefaultData(p_Database);

    }

    @Override
    public void onUpdate(SQLiteDatabase p_Database) {

    }


    public ContentValues createParas(Model_User p_User){
        ContentValues _ContentValues = new ContentValues();
        _ContentValues.put("user_name",p_User.getName() );
        _ContentValues.put("create_date",p_User.getCreateDate().getTime() );
        _ContentValues.put("state",p_User.getStatus().toString());
        return _ContentValues;
    }
}
