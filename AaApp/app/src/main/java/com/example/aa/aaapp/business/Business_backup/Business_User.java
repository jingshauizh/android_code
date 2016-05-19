package com.example.aa.aaapp.business.Business_backup;

import android.content.ContentValues;
import android.content.Context;

import com.example.aa.aaapp.business.base.Business_Base;
import com.example.aa.aaapp.database.interfaces.SQLiteDAL_UserIF;
import com.example.aa.aaapp.database.sqldal.SQLiteDAL_User;
import com.example.aa.aaapp.model.Model_User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-4-19.
 */
public class Business_User extends Business_Base {
    private SQLiteDAL_UserIF m_SQLiteDAL_User;
    public Business_User(Context p_Context) {
        super(p_Context);
        m_SQLiteDAL_User = new SQLiteDAL_User(p_Context);
    }

    public boolean insertUser(Model_User p_modelUser){
        boolean _result =m_SQLiteDAL_User.insertUser(p_modelUser);
        return _result;
    }

    public boolean deleteUserById(String p_userid){
        String _Condition = "  user_id="+p_userid;
        boolean _result =m_SQLiteDAL_User.deleteUser(_Condition);
        return _result;
    }

    public boolean updateUserById(Model_User p_modelUser){
        String _Condition = "  user_id="+p_modelUser.getUserId();
        boolean _result =m_SQLiteDAL_User.updateUser(p_modelUser, _Condition);
        return _result;
    }



    public List<Model_User> getNotHideUsers(){

        return m_SQLiteDAL_User.getUsers("");
    }
    public List<Model_User> getUsers(String p_Condition){
        return m_SQLiteDAL_User.getUsers(p_Condition);
    }

    public Model_User getUserById(int p_UserId){
        String _Condition = " and user_id="+p_UserId;
        List<Model_User> userListr=  m_SQLiteDAL_User.getUsers(_Condition);
        if(userListr.size() == 1){
            return userListr.get(0);
        }
        return null;
    }

    public boolean isExistUserByUserName(String pUserName,Integer pUserID)
    {
        String _Condition = " And user_name = '" + pUserName + "'";
        if(pUserID != null)
        {
            _Condition += " And user_id <> " + pUserID;
        }
        List _List = m_SQLiteDAL_User.getUser(_Condition);
        if (_List.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean hideUserByUserID(int p_UserID)
    {
        String _Condition = " user_id = " + p_UserID;
        ContentValues _ContentValues = new ContentValues();
        _ContentValues.put("state", Model_User.UserStatus.DET.toString());
        Boolean _Result = m_SQLiteDAL_User.updateUser(_Condition, _ContentValues);

        if(_Result)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public Model_User getModelUserByUserID(int pUserID) {
        List<Model_User> _List = m_SQLiteDAL_User.getUser(" And user_id = " + pUserID);
        if (_List.size() == 1) {
            return _List.get(0);
        }
        else {
            return null;
        }
    }

    public List<Model_User> getUserListByUserID(String pUserID[]) {
        List<Model_User> _List = new ArrayList<Model_User>();
        for (int i = 0; i < pUserID.length; i++) {
            _List.add(getModelUserByUserID(Integer.valueOf(pUserID[i])));
        }

        return _List;
    }

    public String getUserNameByUserID(String p_UserID)
    {
        List<Model_User> _List = getUserListByUserID(p_UserID.split(","));
        String _Name = "";

        for(int i=0;i<_List.size();i++)
        {
            _Name += _List.get(i).getName() + ",";
        }
        return _Name;
    }
}
