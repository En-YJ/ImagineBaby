package com.example.imaginebaby;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

// 아기 성장 통계
public class GrowthFragment extends Fragment {

    private View view;

    public static GrowthFragment newInstance(){
        GrowthFragment fragment = new GrowthFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.growth_fragment, container, false);

        TextView week = view.findViewById(R.id.chart_tv_weekdate);

        Calendar calendar = Calendar.getInstance();

        // get the starting and ending date
        // Set the calendar to sunday of the current week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        // Print dates of the current week starting on Sunday
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String startDate = "", endDate = "";

        startDate = df.format(calendar.getTime());
        calendar.add(Calendar.DATE, 6);
        endDate = df.format(calendar.getTime());

        week.setText(startDate+ " ~ " +endDate); // 일주일 설정

        //서버 연결
        NetworkTask networkTask = new NetworkTask(getContext(), 7, startDate);
        networkTask.execute();


       /* // 표시할 데이터 추가 키
        ArrayList<Entry> heights = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            heights.add(new Entry(i, 15));
        }

        // 표시할 데이터 추가 몸무게
        ArrayList<Entry> weights = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            weights.add(new Entry(i, 10));
        }

        // Dataset 설정
        LineChart chart_height = (LineChart)view.findViewById(R.id.chart_mainchart);

        LineDataSet lineDataSet_h = new LineDataSet(heights, "height");
        LineDataSet lineDataSet_w = new LineDataSet(weights, "weight");

        //lineDataSet_h.setColors(ColorTemplate.VORDIPLOM_COLORS);
        lineDataSet_h.setDrawCircles(true);
        lineDataSet_h.setDrawFilled(false);        // 선 아래로 색상 표시
        lineDataSet_h.setDrawValues(true);
        lineDataSet_h.setLineWidth(2);
        //lineDataSet_h.setCircleColor(Color.parseColor("#f24141"));
        //lineDataSet_h.setColor(Color.parseColor("#f24141"));
        lineDataSet_h.setDrawCircleHole(true);
        lineDataSet_h.setDrawCircles(true);

        lineDataSet_w.setLineWidth(2);
        lineDataSet_w.setColors(Color.parseColor("#F78181"));

        XAxis xAxis_h = chart_height.getXAxis();
        xAxis_h.setPosition(XAxis.XAxisPosition.TOP);
        xAxis_h.setTextColor(Color.BLACK);
        xAxis_h.setValueFormatter(new GraphAxisValueFormatter()); //요일 설정

        YAxis LyAxis_h = chart_height.getAxisLeft();
        LyAxis_h.setTextColor(Color.BLACK);

        YAxis RyAxis_h = chart_height.getAxisRight();
        RyAxis_h.setDrawLabels(false);
        RyAxis_h.setDrawAxisLine(false);
        RyAxis_h.setDrawGridLines(false);

        LineData lineData_h = new LineData(lineDataSet_h, lineDataSet_w);
        chart_height.setData(lineData_h);

        chart_height.setDescription(null); //디스크립션
        //chart_height.animateY(2000, Easing.EaseInCubic);
        chart_height.invalidate();*/




        return view;
    }
}
