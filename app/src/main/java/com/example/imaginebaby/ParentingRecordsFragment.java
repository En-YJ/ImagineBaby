package com.example.imaginebaby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

// 육아 기록
public class ParentingRecordsFragment extends Fragment {

    //private RecordsListViewAdapter recordsListViewAdapter;  //기록 리스트뷰 어댑터
    private ListView recordsList;         // 기록 리스트
    private ArrayList<RecordsListItem> recordsData;   // 기록 데이터
    private View view;

    private RecordsListItem[] items = new RecordsListItem[10];


    private ListAdapter adapter; //기록 리스트뷰 어댑터

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.parenting_records_fragment, container, false);

        recordsList = (ListView) view.findViewById(R.id.records_listView);

        setRecordsPage();

        return view;
    }



    // 기록 페이지 설정하는 메소드
    public void setRecordsPage() {
        // 리스트 설정
        recordsList = (ListView) view.findViewById(R.id.records_listView);

        // 데이터 설정
        recordsData = new ArrayList<>();

        //테스팅
        //RecordsListItem[] items = new RecordsListItem[10];
        items[0] = new RecordsListItem(1,"식사","분유 1회 100ml","am 13:22");
        items[1] = new RecordsListItem(2,"기저귀","소변","am 07:00");
        items[2] = new RecordsListItem(3,"성장","머리둘레 5cm","pm 13:22");
        items[3] = new RecordsListItem(4,"수면","수면 01:00","pm 07:00");
        items[4] = new RecordsListItem(1,"식사","모유 10분","pm 13:22");
        items[5] = new RecordsListItem(2,"기저귀","소변 + 대변","pm 07:00");
        items[6] = new RecordsListItem(3,"성장","몸무게 5kg","am 13:22");
        items[7] = new RecordsListItem(4,"수면","수면 02:00","am 07:00");
        items[8] = new RecordsListItem(1,"식사","이유식 1회","am 13:22");
        items[9] = new RecordsListItem(2,"기저귀","그냥 교체","am 07:00");
        recordsData.add(items[0]);
        recordsData.add(items[1]);
        recordsData.add(items[2]);
        recordsData.add(items[3]);
        recordsData.add(items[4]);
        recordsData.add(items[5]);
        recordsData.add(items[6]);
        recordsData.add(items[7]);
        recordsData.add(items[8]);
        recordsData.add(items[9]);

        // 어댑터로 리스트에 아이템 뿌려주기
        adapter = new ListAdapter(getContext(), recordsData);
        recordsList.setAdapter(adapter);


    }

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







}
