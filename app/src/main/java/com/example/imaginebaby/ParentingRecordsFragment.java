package com.example.imaginebaby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

// 육아 기록
public class ParentingRecordsFragment extends Fragment {

    private RecordsListViewAdapter recordsListViewAdapter;  //기록 리스트뷰 어댑터
    private ListView recordsList;         // 기록 리스트
    private ArrayList<RecordsListItem> recordsData;   // 기록 데이터
    private View view;

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
        RecordsListItem[] item = new RecordsListItem[10];
        item[0] = new RecordsListItem(null,"엽","헤헹","13:22");
        item[1] = new RecordsListItem(null,"뭐","므ㅏ","07:00");
        item[2] = new RecordsListItem(null,"엽","헤헹","13:22");
        item[3] = new RecordsListItem(null,"뭐","므ㅏ","07:00");
        item[4] = new RecordsListItem(null,"엽","헤헹","13:22");
        item[5] = new RecordsListItem(null,"뭐","므ㅏ","07:00");
        item[6] = new RecordsListItem(null,"엽","헤헹","13:22");
        item[7] = new RecordsListItem(null,"뭐","므ㅏ","07:00");
        item[8] = new RecordsListItem(null,"엽","헤헹","13:22");
        item[9] = new RecordsListItem(null,"뭐","므ㅏ","07:00");
        recordsData.add(item[0]);
        recordsData.add(item[1]);
        recordsData.add(item[2]);
        recordsData.add(item[3]);
        recordsData.add(item[4]);
        recordsData.add(item[5]);
        recordsData.add(item[6]);
        recordsData.add(item[7]);
        recordsData.add(item[8]);
        recordsData.add(item[9]);

        // 어댑터로 리스트에 아이템 뿌려주기
        recordsListViewAdapter = new RecordsListViewAdapter(getLayoutInflater(), R.layout.records_listview_item, recordsData);
        recordsList.setAdapter(recordsListViewAdapter);

    }



}
