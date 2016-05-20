package com.example.aa.aaapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aa.aaapp.activity.ActivityFrame;
import com.example.aa.aaapp.adapter.AdapterAccountBookSelect;
import com.example.aa.aaapp.adapter.AdapterPayout;
import com.example.aa.aaapp.business.BusinessAccountBook;
import com.example.aa.aaapp.business.BusinessPayout;
import com.example.aa.aaapp.controls.SliderMenuItem;
import com.example.aa.aaapp.controls.SliderMenuView;
import com.example.aa.aaapp.greendao.model.AccountBookEntity;
import com.example.aa.aaapp.model.ModelAccountBook;
import com.example.aa.aaapp.model.ModelPayout;

public class ActivityPayout extends ActivityFrame  implements SliderMenuView.OnSliderMenuListenerIF {

	private ListView lvPayoutList;
	private ModelPayout mSelectModelPayout;
	private BusinessPayout mBusinessPayout;
	private AdapterPayout mAdapterPayout;
	private AccountBookEntity mAccountBook;
	private BusinessAccountBook mBusinessAccountBook;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		appendMainBody(R.layout.payout_list);
		InitVariable();
		InitView();
		InitListeners();
		BindData();
		createSliderMenu(R.array.SlideMenuPayout);
	}
	
	private void SetTitle() {
		int _Count = lvPayoutList.getCount();
		String _Titel = getString(R.string.ActivityTitlePayout, new Object[]{mAccountBook.getAccountBookName(),_Count});
		setTopBarTitle(_Titel);
	}

	protected void InitView() {
		lvPayoutList = (ListView) findViewById(R.id.lvPayoutList);
	}

	protected void InitListeners() {
		registerForContextMenu(lvPayoutList);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu p_ContextMenu, View p_View, ContextMenuInfo p_MenuInfo) {
		//��ȡ��ǰ��ѡ��Ĳ˵������Ϣ
		AdapterContextMenuInfo _AdapterContextMenuInfo = (AdapterContextMenuInfo)p_MenuInfo;
		ListAdapter _ListAdapter = lvPayoutList.getAdapter();
		mSelectModelPayout = (ModelPayout)_ListAdapter.getItem(_AdapterContextMenuInfo.position);
		p_ContextMenu.setHeaderIcon(R.drawable.payout_small_icon);
		p_ContextMenu.setHeaderTitle(mSelectModelPayout.GetCategoryName());
		int _ItemID[] = {1,2};
		createMenu(p_ContextMenu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem p_Item) {
		switch (p_Item.getItemId()) {
		case 1:
			/*Intent _Intent = new Intent(this, ActivityPayoutAddOrEdit.class);
			_Intent.putExtra("ModelPayout", mSelectModelPayout);
			this.startActivityForResult(_Intent, 1);*/
			break;
		case 2:
			Delete(mSelectModelPayout);
			break;
		default:
			break;
		}
		
		return super.onContextItemSelected(p_Item);
	}

	protected void InitVariable() {
		mBusinessPayout = new BusinessPayout(ActivityPayout.this);
		mBusinessAccountBook = new BusinessAccountBook(ActivityPayout.this);
		mAccountBook = mBusinessAccountBook.getDefaultAccountBookEntity();
		mAdapterPayout = new AdapterPayout(this,mAccountBook.getAccountBookId());
	}

	protected void BindData()
	{
		AdapterPayout _AdapterPayout = new AdapterPayout(this,mAccountBook.getAccountBookId());
		lvPayoutList.setAdapter(_AdapterPayout);
		SetTitle();
	}

	@Override
	public void onSliderMenuItemClick(View p_View, SliderMenuItem p_SlideMenuItem) {
		slideMenuToggle();
		if (p_SlideMenuItem.getmId() == 0) {
			ShowAccountBookSelectDialog();
		}
	}
	
	private void ShowAccountBookSelectDialog()
	{
		AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
		View _View = LayoutInflater.from(this).inflate(R.layout.dialog_list, null);
		ListView _ListView = (ListView)_View.findViewById(R.id.ListViewSelect);
		AdapterAccountBookSelect _AdapterAccountBookSelect = new AdapterAccountBookSelect(this);
		_ListView.setAdapter(_AdapterAccountBookSelect);

		_Builder.setTitle(R.string.ButtonTextSelectAccountBook);
		_Builder.setNegativeButton(R.string.ButtonTextBack, null);
		_Builder.setView(_View);
		AlertDialog _AlertDialog = _Builder.create();
		_AlertDialog.show();
		_ListView.setOnItemClickListener(new OnAccountBookItemClickListener(_AlertDialog));
	}
	
	private class OnAccountBookItemClickListener implements AdapterView.OnItemClickListener
	{
		private AlertDialog m_AlertDialog;
		public OnAccountBookItemClickListener(AlertDialog p_AlertDialog)
		{
			m_AlertDialog = p_AlertDialog;
		}
		@Override
		public void onItemClick(AdapterView p_AdapterView, View arg1, int p_Position,
				long arg3) {
			mAccountBook = (AccountBookEntity)((Adapter)p_AdapterView.getAdapter()).getItem(p_Position);
			BindData();
			m_AlertDialog.dismiss();
		}
	}
	
	public void Delete(ModelPayout p_ModelPayout)
	{
		Object _Object[] = {p_ModelPayout.GetCategoryName()}; 	
		String _Message = getString(R.string.DialogMessagePayoutDelete,_Object);
		showAlertDialog(R.string.DialogTitleDelete, _Message, new OnDeleteClickListener());
	}
	
	private class OnDeleteClickListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			
			Boolean _Result = mBusinessPayout.DeletePayoutByPayoutID(mSelectModelPayout.GetPayoutID());
			if(_Result == true)
			{
//				mAdapterPayout.GetList().remove(mListViewPosition);
//				mAdapterPayout.notifyDataSetChanged();
				BindData();
			}
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		BindData();
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
