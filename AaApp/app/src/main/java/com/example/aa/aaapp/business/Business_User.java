package com.example.aa.aaapp.business;


import android.content.Context;
import com.example.aa.aaapp.business.base.Business_Base;
import com.example.aa.aaapp.greendao.UserStatus;
import com.example.aa.aaapp.greendao.dbdal.GreenDaoDALUser;
import com.example.aa.aaapp.greendao.model.UserEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-4-19.
 */
public class Business_User extends Business_Base {
    private GreenDaoDALUser m_SQLiteDAL_User;


    public Business_User(Context p_Context) {
        super(p_Context);
       
        m_SQLiteDAL_User = new GreenDaoDALUser(p_Context);

    }

    public boolean insertUser(UserEntity p_modelUser){
        boolean _result =m_SQLiteDAL_User.insertUser(p_modelUser);
        return _result;
    }

    public boolean deleteUserById(int userId){
        List<UserEntity>  userEntityList= m_SQLiteDAL_User.getUser(userId);
        boolean _result =m_SQLiteDAL_User.deleteUser( userEntityList.get(0));
        return _result;
    }

    public boolean updateUserById(UserEntity p_modelUser){
        String _Condition = "  user_id="+p_modelUser.getUserId();
        boolean _result =m_SQLiteDAL_User.updateUser(p_modelUser);
        return _result;
    }



    public List<UserEntity> getNotHideUsers(){

        return m_SQLiteDAL_User.getUsers();
    }
    public List<UserEntity> getUsers(){
        return m_SQLiteDAL_User.getUsers();
    }


    public UserEntity getUserByUserName(String pUserName)
    {

        List<UserEntity> _List = m_SQLiteDAL_User.getUserByName(pUserName);
        if (_List.size() > 0) {
            return _List.get(0);
        } else {
            return null;
        }
    }

    public boolean isExistUserByUserName(String pUserName)
    {

        List _List = m_SQLiteDAL_User.getUserByName(pUserName);
        if (_List.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


    public Boolean hideUserByUserEntity(UserEntity pUserEntity)
    {
        pUserEntity.setUserStatus(UserStatus.DET.toString());
        Boolean _Result = m_SQLiteDAL_User.updateUser(pUserEntity);

        if(_Result)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public UserEntity getModelUserByUserID(int pUserID) {
        List<UserEntity> _List = m_SQLiteDAL_User.getUser(pUserID);
        if (_List.size() == 1) {
            return _List.get(0);
        }
        else {
            return null;
        }
    }

    public List<UserEntity> getUserListByUserID(String pUserID[]) {
        List<UserEntity> _List = new ArrayList<UserEntity>();
        for (int i = 0; i < pUserID.length; i++) {
            _List.add(getModelUserByUserID(Integer.valueOf(pUserID[i])));
        }

        return _List;
    }

    public String getUserNameByUserID(String p_UserID)
    {
        List<UserEntity> _List = getUserListByUserID(p_UserID.split(","));
        String _Name = "";

        for(int i=0;i<_List.size();i++)
        {
            _Name += _List.get(i).getUserName() + ",";
        }
        return _Name;
    }
}