package com.example.imaginebaby;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {


    private int tabCount;


    public TabPagerAdapter(FragmentManager fragmentManager, int tabCount)
    {
        super(fragmentManager);
        this.tabCount = tabCount;
    }


    // 통계 상위 탭바를 바꿔주는 곳
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                MealFragment mealFragment = new MealFragment();
                return mealFragment;
            case 1:
                SleepFragment sleepFragment = new SleepFragment();
                return sleepFragment;
            case 2:
                DiaperFragment diaperFragment = new DiaperFragment();
                return diaperFragment;
            case 3:
                GrowthFragment growthFragment = new GrowthFragment();
                return growthFragment;

                default :
                    return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
