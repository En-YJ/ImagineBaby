package com.example.imaginebaby;

import android.graphics.Color;
import android.location.Location;
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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

// 아기 식사 통계
public class MealFragment extends Fragment implements OnChartValueSelectedListener {

    private View view;

    public static MealFragment newInstance(){
        MealFragment fragment = new MealFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.meal_fragment, container, false);

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

        week.setText(startDate+ " ~ " +endDate); // 일주일치 가져오기



        BarChart chart = view.findViewById(R.id.mealBarChart);
        chart.getDescription().setEnabled(false); //디스크립션 삭제

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        chart.setDrawValueAboveBar(false);
        chart.setHighlightFullBarEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        chart.getAxisRight().setEnabled(false);

        XAxis xLabels = chart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);
        xLabels.setValueFormatter(new GraphAxisValueFormatter()); //요일 설정

        // chart.setDrawXLabels(false);
        // chart.setDrawYLabels(false);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);


        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, new float[]{2f, 2f, 3f}));
        entries.add(new BarEntry(1, new float[]{2f, 1f, 0f}));
        entries.add(new BarEntry(2, new float[]{4f, 1f, 0f}));
        entries.add(new BarEntry(3, new float[]{0f, 1f, 5f}));
        entries.add(new BarEntry(4, new float[]{0f, 0f, 5f}));
        entries.add(new BarEntry(5,  new float[]{1f, 2f, 3f}));
        entries.add(new BarEntry(6,  new float[]{0f, 5f, 2f}));


        BarDataSet set1;
        set1 = new BarDataSet(entries, "");
        set1.setDrawIcons(false);
        set1.setColors(getColors());
        set1.setStackLabels(new String[]{"BreastMilk", "Milk", "Food"});

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueFormatter(new StackedValueFormatter(true, "", 0));
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);

        chart.setFitBars(true);
        chart.invalidate();



        return view;
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

        BarEntry entry = (BarEntry) e;

        if (entry.getYVals() != null)
            Log.i("VAL SELECTED", "Value: " + entry.getYVals()[h.getStackIndex()]);
        else
            Log.i("VAL SELECTED", "Value: " + entry.getY());

    }

    @Override
    public void onNothingSelected() {}

    private int[] getColors() {

        // have as many colors as stack-values per entry
        int[] colors = new int[3];
        colors[0] = Color.parseColor("#a8d8ea");
        colors[1] = Color.parseColor("#aa96da");
        colors[2] = Color.parseColor("#fcbad3");


        //System.arraycopy(ColorTemplate.MATERIAL_COLORS, 0, colors, 0, 3);

        return colors;
    }
}
