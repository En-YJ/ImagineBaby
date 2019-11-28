package com.example.imaginebaby;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    MealFragment mealFragment = MealFragment.newInstance();
    SleepFragment sleepFragment = SleepFragment.newInstance();
    DiaperFragment diaperFragment = DiaperFragment.newInstance();
    GrowthFragment growthFragment = GrowthFragment.newInstance();

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

                return mealFragment;
            case 1:

                return sleepFragment;
            case 2:

                return diaperFragment;
            case 3:

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
