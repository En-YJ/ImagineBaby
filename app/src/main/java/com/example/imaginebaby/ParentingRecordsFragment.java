package com.example.imaginebaby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// 육아 기록
public class ParentingRecordsFragment extends Fragment {

    //private RecordsListViewAdapter recordsListViewAdapter;  //기록 리스트뷰 어댑터
    private ListView recordsList;         // 기록 리스트
    private ArrayList<RecordsListItem> recordsData;   // 기록 데이터
    private View view;

    private RecordsListItem[] items = new RecordsListItem[10];


    private ListAdapter adapter; //기록 리스트뷰 어댑터

    public static ParentingRecordsFragment newInstance(){
        ParentingRecordsFragment fragment = new ParentingRecordsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.parenting_records_fragment, container, false);
        recordsList = (ListView) view.findViewById(R.id.records_listView);

        //헤더
        View header = getLayoutInflater().inflate(R.layout.reocords_listview_header, null, false) ;
        TextView textView = header.findViewById(R.id.records_list_header);
        textView.setText(getDT());
        recordsList.addHeaderView(header);

        //서버 연결
        NetworkTask networkTask = new NetworkTask(getContext(), 1);
        networkTask.execute();

        //azure sql 테스트용 플로팅버튼
        FloatingActionButton testButton = view.findViewById(R.id.testButton);
        testButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                //서버 연결
                NetworkTask networkTask = new NetworkTask(getContext(), 2);
                networkTask.execute();

                return;
            }
        });

        return view;
    }



    /*// 기록 페이지 설정하는 메소드
    public void setRecordsPage() {
        // 리스트 설정
        recordsList = (ListView) view.findViewById(R.id.records_listView);

        // 데이터 설정
        recordsData = new ArrayList<>();

        //테스팅
        //RecordsListItem[] items = new RecordsListItem[10];
        items[0] = new RecordsListItem(1,"EAT","Powdered milk one more rollie 100ml","am 13:22");
        items[1] = new RecordsListItem(2,"DIAPER","pee","am 07:00");
        items[2] = new RecordsListItem(3,"GROWTH","Head circumference 5cm","pm 13:22");
        items[3] = new RecordsListItem(4,"SLEEP","Sleep end 01:00","pm 07:00");
        items[4] = new RecordsListItem(1,"EAT","Breast milk 10min","pm 13:22");
        items[5] = new RecordsListItem(2,"DIAPER","poo","pm 07:00");
        items[6] = new RecordsListItem(3,"GROWTH","Weight 5kg","am 13:22");
        items[7] = new RecordsListItem(4,"SLEEP","Sleep start 02:00","am 07:00");
        items[8] = new RecordsListItem(1,"EAT","Baby food one","am 13:22");
        items[9] = new RecordsListItem(2,"DIAPER","pee + poo","am 07:00");
        for (int i = 0; i < 10; i++) {
            recordsData.add(items[i]);
        }
        // 어댑터로 리스트에 아이템 뿌려주기
        adapter = new ListAdapter(getContext(), recordsData);
        recordsList.setAdapter(adapter);

    }*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Only if you need to restore open/close state when
        // the orientation is changed
        if (adapter != null) {
            adapter.saveStates(outState);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        // Only if you need to restore open/close state when
        // the orientation is changed
        super.onViewStateRestored(savedInstanceState);
        if (adapter != null) {
            adapter.restoreStates(savedInstanceState);
        }
    }


    public String getDT() {

        String timeNow;

        Calendar cal = Calendar.getInstance();
        int y=0, m=0, d=0, h=0, mi=0, s=0;

        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH) +1;
        d = cal.get(Calendar.DAY_OF_MONTH);
        //h = cal.get(Calendar.HOUR);
        //mi = cal.get(Calendar.MINUTE);

        timeNow = y+"/"+m+"/"+d ;

        return timeNow;
    }





}
