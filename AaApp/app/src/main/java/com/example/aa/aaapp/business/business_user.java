package com.example.aa.aaapp.business;

import android.content.Context;

import com.example.aa.aaapp.business.base.Business_Base;
import com.example.aa.aaapp.database.sqldal.SQLiteDAL_User;
import com.example.aa.aaapp.model.Model_User;

import java.util.List;

/**
 * Created by Administrator on 16-4-19.
 */
public class Business_User extends Business_Base {
    private SQLiteDAL_User m_SQLiteDAL_User;
    public Business_User(Context p_Context) {
        super(p_Context);
        m_SQLiteDAL_User = new SQLiteDAL_User(p_Context);
    }

    public boolean insertUser(Model_User p_modelUser){
        boolean _result =m_SQLiteDAL_User.insertUser(p_modelUser);
        return _result;
    }

    public boolean deleteUserById(String p_userid){
        String _Condition = " and user_id="+p_userid;
        boolean _result =m_SQLiteDAL_User.deleteUser(_Condition);
        return _result;
    }

    public boolean updateUserById(Model_User p_modelUser){
        String _Condition = " and user_id="+p_modelUser.getUserId();
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
}
