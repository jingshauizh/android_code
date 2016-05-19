package com.example.aa.aaapp.greendao.dbdal;

import android.content.ContentValues;
import android.content.Context;
import com.example.aa.aaapp.MyApplication;
import com.example.aa.aaapp.R;
import com.example.aa.aaapp.greendao.UserStatus;
import com.example.aa.aaapp.greendao.model.UserEntity;
import com.example.aa.aaapp.greendao.model.UserEntityDao;
import java.util.Date;
import java.util.List;
import de.greenrobot.dao.query.Query;

/**
 * Created by eqruvvz on 5/19/2016.
 */
public class GreenDaoDALUser extends GreenDaoDALBase  {

    private UserEntityDao mUserEntityDao;
    public GreenDaoDALUser(Context p_context) {
        super(p_context);
        mUserEntityDao = MyApplication.getDaoSession(p_context).getUserEntityDao();
        initDefaultData();
    }
    
    public Boolean insertUser(UserEntity p_user) {
        long id= mUserEntityDao.insert(p_user);
        if(id > 0){
            return true;
        }
        return false;
    }


    public Boolean deleteUser(UserEntity p_user) {
        mUserEntityDao.delete(p_user);
        return true;
    }


    public Boolean updateUser(UserEntity p_user, String p_condition) {
        mUserEntityDao.update(p_user);
        return true;
    }


    public Boolean updateUser(String p_Condition, ContentValues pContentValues) {
        return null;
    }


    public List<UserEntity> getUsers(String p_condition) {

        Query query = mUserEntityDao.queryBuilder().build();
        // 查询结果以 List 返回
        return query.list();
    }


    public List<UserEntity> getUser(int pCondition) {
        Query query = mUserEntityDao.queryBuilder().where(UserEntityDao.Properties.UserId.eq(pCondition)).build();
        return query.list();
    }

    public List<UserEntity> getUserByName(String userName) {
        Query query = mUserEntityDao.queryBuilder().where(UserEntityDao.Properties.UserName.eq(userName)).build();
        return query.list();
    }


    public void initDefaultData( ) {
        UserEntity _UserEntity = new UserEntity();
        String []  _userStr = getContext().getResources().getStringArray(R.array.initDefaultUsername);
        List<UserEntity> userEntityList = getUserByName(_userStr[0]);
        if(userEntityList.size() > 0){
            return;
        }
        for (int i=0; i<_userStr.length;i++){
            _UserEntity.setUserName(_userStr[i]);
            _UserEntity.setUserStatus(UserStatus.USE.toString());
            _UserEntity.setCreateDate(new Date());
            mUserEntityDao.insert(_UserEntity);
        }
    }


}
