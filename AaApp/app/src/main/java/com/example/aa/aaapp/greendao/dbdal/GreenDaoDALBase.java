package com.example.aa.aaapp.greendao.dbdal;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eqruvvz on 5/19/2016.
 */
public class GreenDaoDALBase {
    private Context m_context;


    public GreenDaoDALBase(Context p_context){
        this.m_context =p_context;
    }
    protected Context getContext(){
        return this.m_context;
    }




}
