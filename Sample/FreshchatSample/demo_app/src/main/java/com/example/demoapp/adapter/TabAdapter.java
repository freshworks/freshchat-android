package com.example.demoapp.adapter;

import com.example.demoapp.fragments.ChannelsFragment;
import com.example.demoapp.fragments.FAQFragment;
import com.example.demoapp.fragments.UsersFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public TabAdapter(@NonNull FragmentManager fm, int noOfTabs) {
        super(fm, noOfTabs);
        this.mNumOfTabs = noOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ChannelsFragment();
            case 1:
                return new FAQFragment();
            case 2:
                return new UsersFragment();
            default:
                return new ChannelsFragment();
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
