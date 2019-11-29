package com.example.imaginebaby;

import com.github.mikephil.charting.formatter.ValueFormatter;

public class GraphAxisValueFormatter extends ValueFormatter{
    private String[] mValues;


    // 생성자 초기화
    GraphAxisValueFormatter(){
        String[] labels = new String[7];
        labels[0] = "Sun";
        labels[1] = "Mon";
        labels[2] = "Tue";
        labels[3] = "Wed";
        labels[4] = "Thu";
        labels[5] = "Fri";
        labels[6] = "Sat";

        this.mValues = labels;
    }

    @Override
    public String getFormattedValue(float value){
        return mValues[(int) value];
    }
}