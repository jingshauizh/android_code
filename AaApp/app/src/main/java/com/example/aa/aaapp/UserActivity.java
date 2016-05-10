package com.example.aa.aaapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aa.aaapp.Util.RegexTools;
import com.example.aa.aaapp.activity.ActivityFrame;
import com.example.aa.aaapp.adapter.AdapterUser;
import com.example.aa.aaapp.adapter.BodyAdapter;
import com.example.aa.aaapp.business.Business_User;
import com.example.aa.aaapp.controls.SliderMenuItem;
import com.example.aa.aaapp.controls.SliderMenuView;
import com.example.aa.aaapp.model.Model_User;

public class UserActivity extends ActivityFrame implements SliderMenuView.OnSliderMenuListenerIF {


    private ListView lvUserList;
    private AdapterUser _AdapterUser;
    private Business_User mBusinessUser;
    private Model_User mSelectModlUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        appendMainBody(R.layout.user_layout);

        initVariable();
        initView();
        initListeners();
        bindData();
        this.createSliderMenu(R.array.SlideMenuUser);
    }

    public void initVariable(){
        this.intItem();
        _AdapterUser = new AdapterUser(this);
        mBusinessUser = new Business_User(this);
    }

    public void initView(){
        lvUserList = (ListView)findViewById(R.id.lvUserList);
    }
    public void initListeners(){
        registerForContextMenu(lvUserList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo _AdapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        ListAdapter _ListAdapter = lvUserList.getAdapter();

        mSelectModlUser = (Model_User)_ListAdapter.getItem(_AdapterContextMenuInfo.position);

        menu.setHeaderIcon(R.drawable.user_small_icon);
        menu.setHeaderTitle(mSelectModlUser.getName());

        createMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                ShowUserAddOrEditDialog(mSelectModlUser);
                break;
            case 2:
                Delete();
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }
    public void bindData(){
        lvUserList.setAdapter(_AdapterUser);
        SetTitle();
    }

    @Override
    public void onSliderMenuItemClick(View p_View, SliderMenuItem p_SliderMenuItem) {
        this.showMsg("p_SliderMenuItem.getmId()=" + p_SliderMenuItem.getmId());
        slideMenuToggle();
        if (p_SliderMenuItem.getmId() == 0) {
            ShowUserAddOrEditDialog(null);
        }
    }



    private void SetTitle() {
        int _Count = _AdapterUser.getCount();
        String _Titel = getString(R.string.ActivityTitleUser, new Object[]{_Count});
        setTopBarTitle(_Titel);
    }

    private void ShowUserAddOrEditDialog(Model_User pModelUser)
    {
        View _View = getLayouInflater().inflate(R.layout.user_add_or_edit, null);

        EditText _etUserName = (EditText) _View.findViewById(R.id.etUserName);

        if (pModelUser != null) {
            _etUserName.setText(pModelUser.getName());
        }

        String _Title;

        if(pModelUser == null)
        {
            _Title = getString(R.string.DialogTitleUser,new Object[]{getString(R.string.TitleAdd)});
        }
        else {
            _Title = getString(R.string.DialogTitleUser,new Object[]{getString(R.string.TitleEdit)});
        }

        AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
        _Builder.setTitle(_Title)
                .setView(_View)
                .setIcon(R.drawable.ic_person_black_36dp)
                .setNeutralButton(getString(R.string.ButtonTextSave), new OnAddOrEditUserListener(pModelUser,_etUserName,true))
                .setNegativeButton(getString(R.string.ButtonTextCancel), new OnAddOrEditUserListener(null,null,false))
                .show();
    }

    private class OnAddOrEditUserListener implements DialogInterface.OnClickListener
    {
        private Model_User mModelUser;
        private EditText etUserName;
        private boolean mIsSaveButton;

        public OnAddOrEditUserListener(Model_User pModelUser,EditText petUserName,Boolean pIsSaveButton)
        {
            mModelUser = pModelUser;
            etUserName = petUserName;
            mIsSaveButton = pIsSaveButton;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(mIsSaveButton == false)
            {
                setAlertDialogIsClose(dialog, true);
                return;
            }

            if (mModelUser == null) {
                mModelUser = new Model_User();
            }

            String _UserName = etUserName.getText().toString().trim();

            boolean _CheckResult = RegexTools.IsChineseEnglishNum(_UserName);

            if (!_CheckResult) {
                Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextChineseEnglishNum, new Object[]{etUserName.getHint()}), SHOW_TIME).show();
                setAlertDialogIsClose(dialog, false);
                return;
            }
            else {
                setAlertDialogIsClose(dialog, true);
            }

            _CheckResult = mBusinessUser.isExistUserByUserName(_UserName, mModelUser.getUserId());

            if (_CheckResult) {
                Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextUserExist), SHOW_TIME).show();

                setAlertDialogIsClose(dialog, false);
                return;
            }
            else {
                setAlertDialogIsClose(dialog, true);
            }

            mModelUser.setName(etUserName.getText().toString());

            boolean _Result = false;

            if (mModelUser.getUserId() == 0) {
                _Result = mBusinessUser.insertUser(mModelUser);
            }
            else {
                _Result = mBusinessUser.updateUserById(mModelUser);
            }

            if (_Result) {
                bindData();
            }
            else {
                Toast.makeText(UserActivity.this, getString(R.string.TipsAddFail), 1).show();
            }
        }

    }

    private void Delete() {
        String _Message = getString(R.string.DialogMessageUserDelete,new Object[]{mSelectModlUser.getName()});
        showAlertDialog(R.string.DialogTitleDelete, _Message,new OnDeleteClickListener());
    }

    private class OnDeleteClickListener implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            boolean _Result = mBusinessUser.hideUserByUserID(mSelectModlUser.getUserId());

            if (_Result == true) {
                bindData();
            }
        }
    }
}
