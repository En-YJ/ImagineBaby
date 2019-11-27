package com.example.imaginebaby;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

// 아기 성장 통계
public class GrowthFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.growth_fragment, container, false);

        // chart_height
        // x축 라벨 추가
        ArrayList<String> dates = new ArrayList<>();
        dates.add("5/22");
        dates.add("6/22");
        dates.add("7/22");
        dates.add("8/22");

        // 표시할 데이터 추가
        ArrayList<Entry> heights = new ArrayList<>();
        heights.add(new Entry(140, 0));
        heights.add(new Entry(142, 1));
        heights.add(new Entry(142, 2));
        heights.add(new Entry(144, 3));

        // Dataset 설정
        LineChart chart_height = (LineChart)view.findViewById(R.id.chart_mainchart);

        LineDataSet lineDataSet_h = new LineDataSet(heights, "");
        lineDataSet_h.setColors(ColorTemplate.VORDIPLOM_COLORS);
        lineDataSet_h.setDrawCircles(true);
        lineDataSet_h.setDrawFilled(false);        // 선 아래로 색상 표시
        lineDataSet_h.setDrawValues(false);
        lineDataSet_h.setLineWidth(3);
        lineDataSet_h.setCircleSize(5);
        lineDataSet_h.setCircleColor(Color.parseColor("#f24141"));
        lineDataSet_h.setColor(Color.parseColor("#f24141"));
        lineDataSet_h.setDrawCircleHole(true);
        lineDataSet_h.setDrawCircles(true);

        XAxis xAxis_h = chart_height.getXAxis();
        xAxis_h.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis_h.setTextColor(Color.BLACK);

        YAxis LyAxis_h = chart_height.getAxisLeft();
        LyAxis_h.setTextColor(Color.BLACK);

        YAxis RyAxis_h = chart_height.getAxisRight();
        RyAxis_h.setDrawLabels(false);
        RyAxis_h.setDrawAxisLine(false);
        RyAxis_h.setDrawGridLines(false);

        chart_height.getAxisLeft().setStartAtZero(false);

        LineData lineData_h = new LineData(lineDataSet_h, lineDataSet_h);
        chart_height.setData(lineData_h);

        chart_height.animateY(2000, Easing.EaseInCubic);
        chart_height.invalidate();

        //chart_weight
        // 표시할 데이터 추가
        ArrayList<Entry> weights = new ArrayList<>();
        weights.add(new Entry(50, 0));
        weights.add(new Entry(52, 1));
        weights.add(new Entry(52, 2));
        weights.add(new Entry(54, 3));


        return view;
    }
}
