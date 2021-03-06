package com.example.imaginebaby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

// 육아 통계
public class ParentingStatisticsFragment extends Fragment {

    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabPagerAdapter tabPagerAdapter;

    public static ParentingStatisticsFragment newInstance(){
        ParentingStatisticsFragment fragment = new ParentingStatisticsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.parenting_statistics_fragment, container, false);

        //탭
        tabLayout = view.findViewById(R.id.parenting_tabs);
        viewPager = view.findViewById(R.id.container_statistics);

        tabPagerAdapter = new TabPagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        /*tabLayout.post(new Runnable() {
            @Override
            public void run() {

            }
        });*/

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}
