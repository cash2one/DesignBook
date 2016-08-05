package com.m520it.designbook.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dragon on 2016/8/3.
 */
public class GalleryDetailAdapter extends FragmentStatePagerAdapter{
    private ArrayList<Fragment> frags ;
    private ArrayList<String> titles ;

    public GalleryDetailAdapter(FragmentManager fm, List<Fragment> frags , List<String> titles ) {
        super(fm);
        this.frags  = (ArrayList<Fragment>) frags;
        this.titles = (ArrayList<String>) titles;
    }

    @Override
    public Fragment getItem(int position) {
        return frags.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
