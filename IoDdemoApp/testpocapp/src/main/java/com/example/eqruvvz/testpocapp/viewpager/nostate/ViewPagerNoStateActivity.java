package com.example.eqruvvz.testpocapp.viewpager.nostate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eqruvvz.testpocapp.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerNoStateActivity extends AppCompatActivity {

    //这个是有多少个 fragment页面
    private MyAdapter    mAdapter;
    private ViewPager mPager;
    private List<Entity> list =  new ArrayList<Entity>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_layout_dync);

        for (int i = 0; i < 7 ; i++) {
            Entity ee = new Entity();
            ee.name = "ll"+ i;
            ee.age  = ""+ i;
            list.add(ee);
        }
        mAdapter = new MyAdapter(getSupportFragmentManager(),list);
        mPager = (ViewPager)findViewById(R.id.welcome_viewpager);
        mPager.setAdapter(mAdapter);
    }



    private class Entity{
        public String name;
        public String age;
    }


    // 在这里你可以传 list<Fragment>  也可以传递  list<Object>数据
    public class MyAdapter extends FragmentStatePagerAdapter{
        List<Entity> list_ee;

        public MyAdapter(FragmentManager fm, List<Entity> ee) {
            super(fm);
            this.list_ee = ee ;
        }

        @Override
        public int getCount() {
            return list_ee.size();
        }

        // 初始化每个页卡选项
        @Override
        public Object instantiateItem(ViewGroup arg0, int position) {

            ArrayFragment ff  = (ArrayFragment)super.instantiateItem(arg0, position);
            ff.setThings(list_ee.get(position),position);
            return  ff;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            System.out.println( "position Destory" + position);
            super.destroyItem(container, position, object);
        }


        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return new ArrayFragment();
        }

    }



    /**
     * 所有的  每个Fragment
     */
    public  class ArrayFragment extends Fragment {
        private  Entity ee;
        private int position;

        public void setThings(Entity ee,int position){
            this.ee =ee ;
            this.position = position;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            System.out.println("onCreateView = ");
            //在这里加载每个 fragment的显示的 View
            View  v = inflater.inflate(R.layout.fragment_array, container, false);
            ((TextView)v.findViewById(R.id.textView1)).setText(ee.name+ "= ee.Name -=age"+ ee.age);
            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            System.out.println("onActivityCreated = ");
            super.onActivityCreated(savedInstanceState);
        }

        @Override
        public void onDestroyView(){
            System.out.println("onDestroyView = "+ position);
            super.onDestroyView();
        }

        @Override
        public void onDestroy(){
            System.out.println("onDestroy = "+ position);
            super.onDestroy();
        }
    }
}


