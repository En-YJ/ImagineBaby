package com.example.imaginebaby;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

// 아기 수면 통계
public class SleepFragment extends Fragment {

    private View view;

    public static SleepFragment newInstance(){
        SleepFragment fragment = new SleepFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sleep_fragment, container, false);

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
        NetworkTask networkTask = new NetworkTask(getContext(), 4, startDate);
        networkTask.execute();

        /*BarChart chart = view.findViewById(R.id.barChart);


        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, networkTask.sun));
        entries.add(new BarEntry(1, NetworkTask.mon));
        entries.add(new BarEntry(2, NetworkTask.tue));
        entries.add(new BarEntry(3, NetworkTask.wed));
        entries.add(new BarEntry(4, NetworkTask.thu));
        entries.add(new BarEntry(5, NetworkTask.fri));
        entries.add(new BarEntry(6, NetworkTask.sat));

        XAxis xAxis = chart.getXAxis();


        xAxis.setValueFormatter(new GraphAxisValueFormatter()); //요일 설정


        YAxis yAxisRight = chart.getAxisRight(); //Y축의 오른쪽면 설정
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)


        chart.setDescription(null); //디스크립션

        BarDataSet set = new BarDataSet(entries, "Sleep");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        chart.setData(data);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh*/

        return view;
    }
}
