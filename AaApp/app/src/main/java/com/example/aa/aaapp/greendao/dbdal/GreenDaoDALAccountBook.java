package com.example.aa.aaapp.greendao.dbdal;

import android.content.Context;

import com.example.aa.aaapp.MyApplication;
import com.example.aa.aaapp.R;
import com.example.aa.aaapp.greendao.UserStatus;
import com.example.aa.aaapp.greendao.model.AccountBookEntity;
import com.example.aa.aaapp.greendao.model.AccountBookEntityDao;
import com.example.aa.aaapp.model.Model_User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.Query;

/**
 * Created by eqruvvz on 5/20/2016.
 */
public class GreenDaoDALAccountBook extends GreenDaoDALBase  {

    private AccountBookEntityDao mAccountBookEntityDao;
    public GreenDaoDALAccountBook(Context p_context) {
        super(p_context);
        mAccountBookEntityDao = MyApplication.getDaoSession(p_context).getAccountBookEntityDao();
        initDefaultData();
    }
    @Override
    protected Context getContext() {
        return super.getContext();
    }

    public Boolean insertAccountBook(AccountBookEntity pAccountBookEntity){
        long id =  mAccountBookEntityDao.insert(pAccountBookEntity);
        if(id > 0){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean updateAccountBook(AccountBookEntity pAccountBookEntity){
        mAccountBookEntityDao.update(pAccountBookEntity);
        return Boolean.TRUE;
    }

    public Boolean deleteAccountBook(AccountBookEntity pAccountBookEntity) {
        mAccountBookEntityDao.delete(pAccountBookEntity);
        return true;
    }

    public List<AccountBookEntity> getAccountBooks() {
        Query query = mAccountBookEntityDao.queryBuilder().build();
        return query.list();
    }

    public  AccountBookEntity  getAccountBookById(long accountBookById) {
        Query query = mAccountBookEntityDao.queryBuilder().where(AccountBookEntityDao.Properties.AccountBookId.eq(accountBookById)).build();
        if(query.list().size() > 0){
            return (AccountBookEntity)query.list().get(0);
        }
        return null;

    }


    public AccountBookEntity getAccountBookByName(String accountBookByName) {
        Query query = mAccountBookEntityDao.queryBuilder().where(AccountBookEntityDao.Properties.AccountBookName.eq(accountBookByName)).build();
        if(query.list().size() > 0){
            return (AccountBookEntity)query.list().get(0);
        }
        return null;
    }
    public AccountBookEntity getDefaultAccountBookEntity() {
        Query query = mAccountBookEntityDao.queryBuilder().where(AccountBookEntityDao.Properties.IsDefault.eq(1)).build();
        if(query.list().size() > 0){
            return (AccountBookEntity)query.list().get(0);
        }
        return null;
    }


    public Boolean setAccountBookDefault(AccountBookEntity pAccountBookEntity){
        Query query  = mAccountBookEntityDao.queryBuilder().where(AccountBookEntityDao.Properties.IsDefault.eq(1)).build();
        List pAccountBookEntityList = query.list();
        pAccountBookEntity.setIsDefault(1);
        if(pAccountBookEntityList.size() <=0){
            mAccountBookEntityDao.update(pAccountBookEntity);
            return true;
        }
        else{
            List updateList = new ArrayList<AccountBookEntity>();
            AccountBookEntity preAccountBookEntity = (AccountBookEntity)pAccountBookEntityList.get(0);
            preAccountBookEntity.setIsDefault(0);
            updateList.add(pAccountBookEntity);
            updateList.add(preAccountBookEntity);
            mAccountBookEntityDao.updateInTx(updateList);
            return true;
        }

    }




    public void initDefaultData( ) {
        String _AccountBook[] = getContext().getResources().getStringArray(R.array.InitDefaultDataAccountBookName);
        AccountBookEntity _AccountBookEntityT = this.getAccountBookByName(_AccountBook[0]);
        if(_AccountBookEntityT != null){
            return;
        }
        AccountBookEntity _AccountBookEntity = new AccountBookEntity();
        _AccountBookEntity.setAccountBookName(_AccountBook[0]);
        _AccountBookEntity.setIsDefault(1);
        _AccountBookEntity.setCreateDate(new Date());
        _AccountBookEntity.setState(UserStatus.USE.toString());
        mAccountBookEntityDao.insert(_AccountBookEntity);

    }
}
