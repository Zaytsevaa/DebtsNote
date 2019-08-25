package com.example.debtsnote.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.debtsnote.DetailOperationsFragment;
import com.example.debtsnote.DetailUserFragment;

public class DetailPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public DetailPagerAdapter(@NonNull FragmentManager fm, int behavior, int numOfTabs) {
        super(fm, behavior);
        this.mNumOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DetailOperationsFragment.newInstance();
            case 1:
                return DetailUserFragment.newInstance();
            default:
                return DetailOperationsFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
