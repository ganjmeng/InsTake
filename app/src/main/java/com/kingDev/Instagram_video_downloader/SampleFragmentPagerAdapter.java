package com.kingDev.Instagram_video_downloader;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> mTabHeader;
    public SampleFragmentPagerAdapter(FragmentManager fm, ArrayList<String> tabheader) {
        super(fm);
        this.mTabHeader = tabheader;
    }

    @Override
    public int getCount() {
        return mTabHeader.size();
    }

    @Override
    public Fragment getItem(int position) {
        // TODO Auto-generated method stub

        switch (position) {
            case 0:
                // tabs home
                return new home();
            case 1:
                // how to use
                //return new howToUse();
                return new recyclerview();
            case 2:
                return new guide();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return mTabHeader.get(position);
    }
}
