package com.example.imaginebaby;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class GraphAxisValueFormatter extends ValueFormatter{
    private String[] mValues;


    // 생성자 초기화
    GraphAxisValueFormatter(){
        String[] labels = new String[7];
        labels[0] = "일";
        labels[1] = "월";
        labels[2] = "화";
        labels[3] = "수";
        labels[4] = "목";
        labels[5] = "금";
        labels[6] = "토";

        this.mValues = labels;
    }

    @Override
    public String getFormattedValue(float value){
        return mValues[(int) value];
    }
}