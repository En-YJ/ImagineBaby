package com.example.imaginebaby;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

// 아기 기저귀 통계
public class DiaperFragment extends Fragment {

    private View view;
    private BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diaper_fragment, container, false);

        barChart = (BarChart) view.findViewById(R.id.diaperBarChart);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 1f));
        entries.add(new BarEntry(1, 2f));
        entries.add(new BarEntry(2, 3f));
        entries.add(new BarEntry(3, 4f));
        entries.add(new BarEntry(4, 5f));
        entries.add(new BarEntry(5, 2f));
        entries.add(new BarEntry(6, 5f));


        XAxis xAxis = barChart.getXAxis();

        xAxis.setValueFormatter(new GraphAxisValueFormatter()); //요일 설정


        YAxis yAxisRight = barChart.getAxisRight(); //Y축의 오른쪽면 설정
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        barChart.setDescription(null); //디스크립션


        BarDataSet set = new BarDataSet(entries, "일일 기저귀 교체 횟수");

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        barChart.setData(data);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh


        return view;
    }


}
