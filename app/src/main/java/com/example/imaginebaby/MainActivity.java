package com.example.imaginebaby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; //하단 네비 바
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    /*private MealFragment mealFragment;
    private GrowthFragment growthFragment;
    private DiaperFragment diaperFragment;
    private SleepFragment sleepFragment;*/

    private ParentingRecordsFragment recordsFragment;
    private ParentingStatisticsFragment statisticsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                changeFragment(menuItem.getItemId());
                return true;
            }
        });

       /* mealFragment = new MealFragment();
        growthFragment = new GrowthFragment();
        diaperFragment = new DiaperFragment();
        sleepFragment = new SleepFragment();*/

       recordsFragment = new ParentingRecordsFragment();
       statisticsFragment = new ParentingStatisticsFragment();

        setFragment(0); //홈화면으로 지정
    }


    public void changeFragment(int menu){
        switch (menu){
            case R.id.action_records:
                setFragment(0);
                break;
            case R.id.action_statistics:
                setFragment(1);
                break;
        }
    }

    //프래크먼트 교체가 일어나는 곳
    private void setFragment(int menu){

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (menu){
            case 0:
                fragmentTransaction.replace(R.id.main_frame, recordsFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.main_frame, statisticsFragment);
                fragmentTransaction.commit();
                break;

        }

    }


    // 뒤로가기 버튼을 눌렀을 때의 오버라이드 메소드
    @Override
    public void onBackPressed() {

        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("Do you wanna quit, bro?");

        // "예" 버튼을 누르면 실행되는 리스너
        alBuilder.setPositiveButton("Yeah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // 현재 액티비티를 종료한다. (MainActivity에서 작동하기 때문에 애플리케이션을 종료한다.)
            }
        });
        // "아니오" 버튼을 누르면 실행되는 리스너
        alBuilder.setNegativeButton("Nah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return; // 아무런 작업도 하지 않고 돌아간다
            }
        });

        alBuilder.setTitle(getString(R.string.app_name));
        alBuilder.setIcon(R.mipmap.ic_launcher); //아이콘 설정
        alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.

    }


}
