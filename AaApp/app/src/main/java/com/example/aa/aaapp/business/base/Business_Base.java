package com.example.aa.aaapp.business.base;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by Administrator on 16-4-19.
 */
public class Business_Base {
    private Context m_Context;
    protected Business_Base(Context p_Context){
        this.m_Context = p_Context;
    }
    protected String getString(int resId){
        return m_Context.getString(resId);
    }

    protected String getString(int resId, Object[] p_Objects){
        return m_Context.getString(resId,p_Objects);
    }
}
