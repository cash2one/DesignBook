package com.m520it.designbook.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.m520it.designbook.fragment.GalleryDetailFragment;

import java.util.ArrayList;

/**
 * Created by dragon on 2016/7/31.
 */
public class GalleryDetailFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<GalleryDetailFragment> frags ;

    public GalleryDetailFragmentAdapter(FragmentManager fm,ArrayList<GalleryDetailFragment> fragments) {
        super(fm);
        frags= fragments;
    }
    //TODO 应该要对position处理一下的
    @Override
    public Fragment getItem(int position) {
        if(frags!=null) {
            return frags.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return frags!=null?frags.size():0;
    }
}
