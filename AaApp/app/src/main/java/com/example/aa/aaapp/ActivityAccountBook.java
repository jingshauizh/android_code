package com.example.aa.aaapp;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.aa.aaapp.Util.RegexTools;
import com.example.aa.aaapp.activity.ActivityFrame;
import com.example.aa.aaapp.adapter.AdapterAccountBook;
import com.example.aa.aaapp.business.BusinessAccountBook;
import com.example.aa.aaapp.controls.SliderMenuItem;
import com.example.aa.aaapp.controls.SliderMenuView;
import com.example.aa.aaapp.greendao.UserStatus;
import com.example.aa.aaapp.greendao.model.AccountBookEntity;

import java.util.Date;

public class ActivityAccountBook extends ActivityFrame implements SliderMenuView.OnSliderMenuListenerIF {
	
	private ListView lvAccountBookList;
	
	private AdapterAccountBook mAdapterAccountBook;
	private BusinessAccountBook mBusinessAccountBook;
	private AccountBookEntity mSelectModlAccountBook;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(R.layout.account_book);
        InitVariable();
        InitView();
        InitListeners();
        BindData();
        this.createSliderMenu(R.array.SlideMenuAccountBook);
    }
    
    public void InitVariable()
    {
    	mBusinessAccountBook = new BusinessAccountBook(this);
    }
    
    public void InitView()
    {
    	lvAccountBookList = (ListView) findViewById(R.id.lvAccountBookList);
    }
    
    public void InitListeners()
    {
    	registerForContextMenu(lvAccountBookList);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	AdapterContextMenuInfo _AdapterContextMenuInfo = (AdapterContextMenuInfo) menuInfo;
    	ListAdapter _ListAdapter = lvAccountBookList.getAdapter();
    	
    	mSelectModlAccountBook = (AccountBookEntity)_ListAdapter.getItem(_AdapterContextMenuInfo.position);
    	
    	menu.setHeaderIcon(R.drawable.account_book_small_icon);
    	menu.setHeaderTitle(mSelectModlAccountBook.getAccountBookName());
    	
    	createMenu(menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {

    	switch (item.getItemId()) {
		case 1:
			ShowAccountBookAddOrEditDialog(mSelectModlAccountBook);
			break;
		case 2:
			Delete();
			break;
		default:
			break;
		}

    	return super.onContextItemSelected(item);
    }
    
    public void BindData()
    {
    	mAdapterAccountBook = new AdapterAccountBook(this);
    	lvAccountBookList.setAdapter(mAdapterAccountBook);
    	SetTitle();
    }


	@Override
	public void onSliderMenuItemClick(View p_View, SliderMenuItem p_SliderMenuItem) {
		this.showMsg("p_SliderMenuItem.getmId()=" + p_SliderMenuItem.getmId());
		slideMenuToggle();
		if (p_SliderMenuItem.getmId() == 0) {
			ShowAccountBookAddOrEditDialog(null);
		}
	}
	
	private void SetTitle() {
		int _Count = mAdapterAccountBook.getCount();
		String _Titel = getString(R.string.ActivityTitleAccountBook);
		setTopBarTitle(_Titel);
	}
	
	private void ShowAccountBookAddOrEditDialog(AccountBookEntity pModelAccountBook)
	{
		View _View = getLayouInflater().inflate(R.layout.account_book_add_or_edit, null);
		
		EditText _etAccountBookName = (EditText) _View.findViewById(R.id.etAccountBookName);
		CheckBox _chkIsDefault = (CheckBox)_View.findViewById(R.id.chkIsDefault);
		
		if (pModelAccountBook != null) {
			_etAccountBookName.setText(pModelAccountBook.getAccountBookName());
		}
		
		String _Title;
		
		if(pModelAccountBook == null)
		{
			_Title = getString(R.string.DialogTitleAccountBook,new Object[]{getString(R.string.TitleAdd)});
		}
		else {
			_Title = getString(R.string.DialogTitleAccountBook,new Object[]{getString(R.string.TitleEdit)});
		}
		
		AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
		_Builder.setTitle(_Title)
				.setView(_View)
				.setIcon(R.drawable.account_book_big_icon)
				.setNeutralButton(getString(R.string.ButtonTextSave), new OnAddOrEditAccountBookListener(pModelAccountBook,_etAccountBookName,_chkIsDefault,true))
				.setNegativeButton(getString(R.string.ButtonTextCancel), new OnAddOrEditAccountBookListener(null,null,null,false))
				.show();
	}
	
	private class OnAddOrEditAccountBookListener implements DialogInterface.OnClickListener
	{
		private AccountBookEntity mModelAccountBook;
		private EditText etAccountBookName;
		private boolean mIsSaveButton;
		private CheckBox chkIsDefault;
		
		public OnAddOrEditAccountBookListener(AccountBookEntity pModelAccountBook,EditText petAccountBookName,CheckBox pchkIsDefault,Boolean pIsSaveButton)
		{
			mModelAccountBook = pModelAccountBook;
			etAccountBookName = petAccountBookName;
			mIsSaveButton = pIsSaveButton;
			chkIsDefault = pchkIsDefault;
		}
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			if(mIsSaveButton == false)
			{
				setAlertDialogIsClose(dialog, true);
				return;
			}
			
			if (mModelAccountBook == null) {
				mModelAccountBook = new AccountBookEntity();
				mModelAccountBook.setCreateDate(new Date());
				mModelAccountBook.setState(UserStatus.USE.toString());
			}
			
			String _AccountBookName = etAccountBookName.getText().toString().trim();
			
			boolean _CheckResult = RegexTools.IsChineseEnglishNum(_AccountBookName);
			
			if (!_CheckResult) {
				Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextChineseEnglishNum,new Object[]{etAccountBookName.getHint()}), Toast.LENGTH_SHORT).show();
				setAlertDialogIsClose(dialog, false);
				return;
			}
			else {
				setAlertDialogIsClose(dialog, true);
			}
			
			_CheckResult = mBusinessAccountBook.isExistAccountBookByAccountBookName(_AccountBookName);
			
			if (_CheckResult) {
				Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextAccountBookExist), Toast.LENGTH_SHORT).show();
				
				setAlertDialogIsClose(dialog, false);
				return;
			}
			else {
				setAlertDialogIsClose(dialog, true);
			}
			
			mModelAccountBook.setAccountBookName(etAccountBookName.getText().toString());
			
			mModelAccountBook.setAccountBookName(String.valueOf(etAccountBookName.getText().toString().trim()));
			if(chkIsDefault.isChecked())
			{
				mModelAccountBook.setIsDefault(1);
			}
			else {
				mModelAccountBook.setIsDefault(0);
			}
			
			if(mModelAccountBook.getAccountBookId() != null && mModelAccountBook.getAccountBookId() > 0)
			{
				mModelAccountBook.setIsDefault(1);
			}
			
			boolean _Result = false;
			
			if (mModelAccountBook.getAccountBookId() == null || mModelAccountBook.getAccountBookId() == 0) {
				_Result = mBusinessAccountBook.insertAccountBook(mModelAccountBook);
			}
			else {
				_Result = mBusinessAccountBook.updateAccountBookByAccountBookID(mModelAccountBook);
			}
			
			if (_Result) {
				BindData();
			}
			else {
				Toast.makeText(ActivityAccountBook.this, getString(R.string.TipsAddFail), Toast.LENGTH_SHORT).show();
			}
		}
		
	}

	private void Delete() {
		String _Message = getString(R.string.DialogMessageAccountBookDelete,new Object[]{mSelectModlAccountBook.getAccountBookName()});
		showAlertDialog(R.string.DialogTitleDelete, _Message, new OnDeleteClickListener());
	}
	
	private class OnDeleteClickListener implements DialogInterface.OnClickListener
	{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			boolean _Result = mBusinessAccountBook.deleteAccountBookByAccountBookID(mSelectModlAccountBook.getAccountBookId());
			
			if (_Result == true) {
				BindData();
			}
		}
	}
}