package com.example.aa.aaapp.business;

import android.content.Context;

import com.example.aa.aaapp.business.base.Business_Base;
import com.example.aa.aaapp.greendao.dbdal.GreenDaoDALAccountBook;
import com.example.aa.aaapp.greendao.model.AccountBookEntity;

import java.util.List;

public class BusinessAccountBook extends Business_Base {

	private GreenDaoDALAccountBook mGreenDaoDALAccountBook;

	public BusinessAccountBook(Context p_Context) {
		super(p_Context);
		mGreenDaoDALAccountBook = new GreenDaoDALAccountBook(p_Context);
	}

	public Boolean insertAccountBook(AccountBookEntity pAccountBookEntity) {
		mGreenDaoDALAccountBook.getDataBase().beginTransaction();
		try {
			Boolean _Result = mGreenDaoDALAccountBook.insertAccountBook(pAccountBookEntity);
			Boolean _Result2 = Boolean.TRUE;
			if (pAccountBookEntity.getIsDefault() == 1 && _Result) {
				_Result2 = setIsDefault(pAccountBookEntity);
			}

			if (_Result && _Result2) {
				mGreenDaoDALAccountBook.getDataBase().setTransactionSuccessful();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			mGreenDaoDALAccountBook.getDataBase().endTransaction();
		}
	}



	public Boolean deleteAccountBookByAccountBookID(long p_AccountBookID) {
		mGreenDaoDALAccountBook.getDataBase().beginTransaction();
		try {
			AccountBookEntity _AccountBookEntity = mGreenDaoDALAccountBook.getAccountBookById(p_AccountBookID);
			Boolean _Result = mGreenDaoDALAccountBook.deleteAccountBook(_AccountBookEntity);
			Boolean _Result2 = true;
//			if (_Result) {
//				BusinessPayout _BusinessPayout = new BusinessPayout(
//						getContext());
//				_Result2 = _BusinessPayout
//						.DeletePayoutByAccountBookID(p_AccountBookID);
//			}

			if (_Result && _Result2) {
				mGreenDaoDALAccountBook.getDataBase().setTransactionSuccessful();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			mGreenDaoDALAccountBook.getDataBase().endTransaction();
		}
	}

	public Boolean updateAccountBookByAccountBookID(AccountBookEntity pAccountBookEntity) {
		mGreenDaoDALAccountBook.getDataBase().beginTransaction();
		try {
			Boolean _Result = mGreenDaoDALAccountBook.updateAccountBook(pAccountBookEntity);
			Boolean _Result2 = true;
			if (pAccountBookEntity.getIsDefault() == 1 && _Result) {
				_Result2 = setIsDefault(pAccountBookEntity);
			}

			if (_Result && _Result2) {
				mGreenDaoDALAccountBook.getDataBase().setTransactionSuccessful();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			mGreenDaoDALAccountBook.getDataBase().endTransaction();
		}
	}



	public boolean isExistAccountBookByAccountBookName(String p_AccountBookName) {
		AccountBookEntity _AccountBookEntity = mGreenDaoDALAccountBook.getAccountBookByName(p_AccountBookName);
		if (_AccountBookEntity !=null) {
			return true;
		} else {
			return false;
		}
	}

	public AccountBookEntity getDefaultAccountBookEntity() {
		return mGreenDaoDALAccountBook.getDefaultAccountBookEntity();
	}


	public Boolean setIsDefault(AccountBookEntity pAccountBookEntity) {
		return mGreenDaoDALAccountBook.setAccountBookDefault(pAccountBookEntity);
	}

	public List<AccountBookEntity> getAccountBooks(){
		return mGreenDaoDALAccountBook.getAccountBooks();
	}
	
	public String getAccountBookNameByAccountId(long p_BookId) {
		return mGreenDaoDALAccountBook.getAccountBookById(p_BookId).getAccountBookName();
	}
}
