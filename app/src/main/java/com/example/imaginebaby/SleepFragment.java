package com.example.imaginebaby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

// 아기 수면 통계
public class SleepFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sleep_fragment, container, false);

        BarChart chart = view.findViewById(R.id.barChart);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 3));
        entries.add(new BarEntry(1, 3));
        entries.add(new BarEntry(2, 5));
        entries.add(new BarEntry(3, 6));
        entries.add(new BarEntry(4, 5));
        entries.add(new BarEntry(5, 7));
        entries.add(new BarEntry(6, 5));

        XAxis xAxis = chart.getXAxis();


        xAxis.setValueFormatter(new GraphAxisValueFormatter()); //요일 설정


        YAxis yAxisRight = chart.getAxisRight(); //Y축의 오른쪽면 설정
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)


        chart.setDescription(null); //디스크립션

        BarDataSet set = new BarDataSet(entries, "일일 수면 횟수");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        chart.setData(data);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh

        return view;
    }
}
