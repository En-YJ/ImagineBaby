package com.example.imaginebaby;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class NetworkTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private String url;
    private String data;
    private int selection;
    ProgressDialog asyncDialog;
    List<RecordsListItem> items = new ArrayList<>();
    DBConnection dbConnection = new DBConnection();
    private Connection conn;
    public int mon, tue, wed, thu, fri, sat, sun ;
    String startDate, monDate, tueDate, wedDate, thuDate, friDate, endDate;


    public NetworkTask(Context _context, String url, String data, int action){
        this.context = _context;
        this.url = url;
        this.data = data;
        this.selection = action;
        if(_context != null)
            this.asyncDialog = new ProgressDialog(_context);
    }

    public NetworkTask(Context _context, int action){
        this.context = _context;
        this.selection = action;
        if(_context != null)
            this.asyncDialog = new ProgressDialog(_context);
    }

    public NetworkTask(Context _context, int action, String data){
        this.context = _context;
        this.selection = action;
        this.data = data;
        if(_context != null)
            this.asyncDialog = new ProgressDialog(_context);
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        startDate = df.format(calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        monDate = df.format(calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        tueDate = df.format(calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        wedDate = df.format(calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        thuDate = df.format(calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        friDate = df.format(calendar.getTime());
        calendar.add(Calendar.DATE, 1);
        endDate = df.format(calendar.getTime());



        return result;
    }

    @Override
    protected void onPreExecute(){
        //로딩
        if(asyncDialog != null) {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("Loading");
            asyncDialog.show();
            asyncDialog.setCanceledOnTouchOutside(false);
        }
        super.onPreExecute();
    }

    public ResultSet resultSet(String query5) throws SQLException {
        conn = dbConnection.connectionClass();
        String query3 = query5; //여기서 원하는 쿼리문을 넣고
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query5);
        return rs;
    }

    public String SplitDate(String date){

        String splitDate = "";
        splitDate = date.split(" ")[1].split(":")[0] + ":" + date.split(" ")[1].split(":")[1];

        return splitDate;
    }

    @Override
    protected void onPostExecute(String result) {

        try {
            //경우에 따라 결과 값을 받아 일어났으면 하는 작업
            switch (this.selection) {

                //리스트 받아오는 곳
                case 1:
                    try {
                        Date dataDate = Date.valueOf(data); //가져와야할 날짜

                        /*Connection conn = dbConnection.connectionClass();
                        String query="SELECT * FROM TestBaby;"; //여기서 원하는 쿼리문을 넣고
                        Statement stmt=conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);*/

                        ResultSet rs = resultSet("SELECT * FROM BreastMilk");
                        while(rs.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            //rs.get원하는형식("데이터이름")
                            //rs.getString("baby_time").split(" ")[0] ;
                            if( (rs.getString("record_time").contains(data) )) {
                                RecordsListItem item = new RecordsListItem(1, "EAT", rs.getString("baby_special"), SplitDate(rs.getString("record_time")));
                                items.add(item);
                            }
                        }
                        rs = resultSet("SELECT * FROM Meal");
                        while(rs.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            //rs.get원하는형식("데이터이름")
                            //rs.getString("baby_time").split(" ")[0] ;
                            if( (rs.getString("baby_time").contains(data) )) {
                                RecordsListItem item = new RecordsListItem(1, "EAT", rs.getString("baby_special"), SplitDate(rs.getString("baby_time")));
                                items.add(item);
                            }
                        }
                        rs = resultSet("SELECT * FROM Diaper");
                        while(rs.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            //rs.get원하는형식("데이터이름")
                            //rs.getString("baby_time").split(" ")[0] ;
                            if( (rs.getString("baby_time").contains(data) )) {
                                RecordsListItem item = new RecordsListItem(3, "DIAPER", rs.getString("baby_special"), SplitDate(rs.getString("baby_time")));
                                items.add(item);
                            }
                        }
                        rs = resultSet("SELECT * FROM Sleep");
                        while(rs.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            //rs.get원하는형식("데이터이름")
                            //rs.getString("baby_time").split(" ")[0] ;
                            if( (rs.getString("baby_record_time").contains(data) )) {
                                if(rs.getString("baby_start_time") == null)
                                {
                                    RecordsListItem item = new RecordsListItem(rs.getInt("baby_id"),2, "SLEEP "+rs.getString("baby_sleep_time")+"min", rs.getString("baby_special"), SplitDate(rs.getString("baby_end_time")));
                                    items.add(item);
                                }
                                else if(rs.getString("baby_end_time") == null)
                                {
                                    RecordsListItem item = new RecordsListItem(rs.getInt("baby_id"),2, "SLEEP "+rs.getString("baby_sleep_time")+"min", rs.getString("baby_special"), SplitDate(rs.getString("baby_start_time")));
                                    items.add(item);
                                }
                                else
                                {
                                    RecordsListItem item = new RecordsListItem(rs.getInt("baby_id"),2, "SLEEP "+rs.getString("baby_sleep_time")+"min", rs.getString("baby_special"), SplitDate(rs.getString("baby_record_time")));
                                    items.add(item);
                                }

                            }
                        }
                        rs = resultSet("SELECT * FROM HeadSize");
                        while(rs.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            //rs.get원하는형식("데이터이름")
                            //rs.getString("baby_time").split(" ")[0] ;
                            if( (rs.getString("baby_reference_date").contains(data) )) {
                                RecordsListItem item = new RecordsListItem(4, "GROWTH", "Head Size : "+rs.getString("baby_head_size")+" inch ", SplitDate(rs.getString("baby_reference_date")));
                                items.add(item);
                            }
                        }
                        conn.close();

                        // 리스트 설정
                        ListView recordsList = (ListView) ((Activity) context).findViewById(R.id.records_listView);
                        // 어댑터로 리스트에 아이템 뿌려주기
                        ListAdapter adapter = new ListAdapter(context, items);
                        recordsList.setAdapter(adapter);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try{
                        /*Connection con = dbConnection.connectionClass();
                        String query = "INSERT INTO TestBaby(baby_image,baby_title,baby_desc) VALUES(3,'test','test')" ;
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);*/

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                    //삭제하는곳
                case 3:
                    try {
                        Connection con = dbConnection.connectionClass();
                        String query = "DELETE FROM Sleep WHERE baby_id ="+"'"+data+"'" ;
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                    //그래프 Sleep
                case 4:
                    mon = 0;
                    tue = 0;
                    wed = 0;
                    thu = 0;
                    fri = 0;
                    sat = 0;
                    sun = 0;

                    try {
                        ResultSet rs = resultSet("SELECT * FROM Sleep");
                        int sleepTime = 0;
                        int count = 0;
                        while(rs.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            if( (rs.getString("baby_record_time").contains(startDate) )) {
                                sun++;
                            }
                            if( (rs.getString("baby_record_time").contains(monDate) )) {
                                mon++;
                            }
                            if( (rs.getString("baby_record_time").contains(tueDate) )) {
                                tue++;
                            }
                            if( (rs.getString("baby_record_time").contains(wedDate) )) {
                                wed++;
                            }
                            if( (rs.getString("baby_record_time").contains(thuDate) )) {
                                thu++;
                            }
                            if( (rs.getString("baby_record_time").contains(friDate) )) {
                                fri++;
                            }
                            if( (rs.getString("baby_record_time").contains(endDate) )) {
                                sat++;
                            }

                            if( rs.getString("baby_record_time").contains(startDate) || rs.getString("baby_record_time").contains(monDate) || rs.getString("baby_record_time").contains(tueDate) || rs.getString("baby_record_time").contains(wedDate) ||
                                    rs.getString("baby_record_time").contains(thuDate) || rs.getString("baby_record_time").contains(friDate) || rs.getString("baby_record_time").contains(endDate) ){
                                sleepTime += rs.getInt("baby_sleep_time");
                                count++;
                            }

                        }

                        if(count == 0)
                            count++;

                        TextView averageSleepCount = ((Activity) context).findViewById(R.id.average_sleep_count);
                        TextView averageSleepTime = ((Activity) context).findViewById(R.id.average_sleep_time);
                        averageSleepCount.setText(String.valueOf(sun+mon+tue+wed+thu+fri+sat));
                        averageSleepTime.setText(String.valueOf(sleepTime/count));

                        BarChart chart = ((Activity) context).findViewById(R.id.barChart);
                        List<BarEntry> entries = new ArrayList<>();
                        entries.add(new BarEntry(0, sun));
                        entries.add(new BarEntry(1, mon));
                        entries.add(new BarEntry(2, tue));
                        entries.add(new BarEntry(3, wed));
                        entries.add(new BarEntry(4, thu));
                        entries.add(new BarEntry(5, fri));
                        entries.add(new BarEntry(6, sat));

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
                        chart.invalidate(); // refresh

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                    //Diaper
                case 5:
                    mon = 0;
                    tue = 0;
                    wed = 0;
                    thu = 0;
                    fri = 0;
                    sat = 0;
                    sun = 0;

                    int pee=0;
                    int poo=0;


                    try {
                        ResultSet rs = resultSet("SELECT * FROM Diaper");
                        while(rs.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            if( (rs.getString("baby_time").contains(startDate) )) {
                                sun++;
                            }
                            if( (rs.getString("baby_time").contains(monDate) )) {
                                mon++;
                            }
                            if( (rs.getString("baby_time").contains(tueDate) )) {
                                tue++;
                            }
                            if( (rs.getString("baby_time").contains(wedDate) )) {
                                wed++;
                            }
                            if( (rs.getString("baby_time").contains(thuDate) )) {
                                thu++;
                            }
                            if( (rs.getString("baby_time").contains(friDate) )) {
                                fri++;
                            }
                            if( (rs.getString("baby_time").contains(endDate) )) {
                                sat++;
                            }

                            if( rs.getString("baby_time").contains(startDate) || rs.getString("baby_time").contains(monDate) || rs.getString("baby_time").contains(tueDate) || rs.getString("baby_time").contains(wedDate) ||
                                    rs.getString("baby_time").contains(thuDate) || rs.getString("baby_time").contains(friDate) || rs.getString("baby_time").contains(endDate) )
                            {
                                if(rs.getInt("baby_state") == 1){
                                    pee++;
                                }
                                if(rs.getInt("baby_state") == 2){
                                    poo++;
                                }
                                if(rs.getInt("baby_state") == 3){
                                    pee++;
                                    poo++;
                                }
                            }


                        }

                        TextView averagePee = ((Activity) context).findViewById(R.id.average_pee);
                        TextView averagePoo = ((Activity) context).findViewById(R.id.average_poo);

                        averagePee.setText(String.valueOf(pee));
                        averagePoo.setText(String.valueOf(poo));

                        BarChart barChart = (BarChart) ((Activity) context).findViewById(R.id.diaperBarChart);
                        List<BarEntry> entries = new ArrayList<>();
                        entries.add(new BarEntry(0, sun));
                        entries.add(new BarEntry(1, mon));
                        entries.add(new BarEntry(2, tue));
                        entries.add(new BarEntry(3, wed));
                        entries.add(new BarEntry(4, thu));
                        entries.add(new BarEntry(5, fri));
                        entries.add(new BarEntry(6, sat));


                        XAxis xAxis = barChart.getXAxis();

                        xAxis.setValueFormatter(new GraphAxisValueFormatter()); //요일 설정


                        YAxis yAxisRight = barChart.getAxisRight(); //Y축의 오른쪽면 설정
                        yAxisRight.setDrawLabels(false);
                        yAxisRight.setDrawAxisLine(false);
                        yAxisRight.setDrawGridLines(false);

                        YAxis leftAxis = barChart.getAxisLeft();
                        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

                        barChart.setDescription(null); //디스크립션


                        BarDataSet set = new BarDataSet(entries, "Diaper");
                        set.setColors(Color.parseColor("#fcf5b0"));
                        BarData data = new BarData(set);
                        data.setValueFormatter(new StackedValueFormatter(true, "", 0));
                        data.setBarWidth(0.9f); // set custom bar width
                        barChart.setData(data);
                        barChart.setFitBars(true); // make the x-axis fit exactly all bars
                        barChart.invalidate(); // refresh

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;


                    //MEAL
                case 6:
                    mon = 0;
                    tue = 0;
                    wed = 0;
                    thu = 0;
                    fri = 0;
                    sat = 0;
                    sun = 0;

                    int mon2 = 0;
                    int tue2 = 0;
                    int wed2 = 0;
                    int thu2 = 0;
                    int fri2 = 0;
                    int sat2 = 0;
                    int sun2 = 0;


                    try {
                        ResultSet rs = resultSet("SELECT * FROM Meal");
                        while(rs.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            if( (rs.getString("baby_time").contains(startDate) )) {
                                sun++;
                            }
                            if( (rs.getString("baby_time").contains(monDate) )) {
                                mon++;
                            }
                            if( (rs.getString("baby_time").contains(tueDate) )) {
                                tue++;
                            }
                            if( (rs.getString("baby_time").contains(wedDate) )) {
                                wed++;
                            }
                            if( (rs.getString("baby_time").contains(thuDate) )) {
                                thu++;
                            }
                            if( (rs.getString("baby_time").contains(friDate) )) {
                                fri++;
                            }
                            if( (rs.getString("baby_time").contains(endDate) )) {
                                sat++;
                            }
                        }
                        ResultSet rs2 = resultSet("SELECT * FROM BreastMilk");
                        while(rs2.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            if( (rs2.getString("record_time").contains(startDate) )) {
                                sun2++;
                            }
                            if( (rs2.getString("record_time").contains(monDate) )) {
                                mon2++;
                            }
                            if( (rs2.getString("record_time").contains(tueDate) )) {
                                tue2++;
                            }
                            if( (rs2.getString("record_time").contains(wedDate) )) {
                                wed2++;
                            }
                            if( (rs2.getString("record_time").contains(thuDate) )) {
                                thu2++;
                            }
                            if( (rs2.getString("record_time").contains(friDate) )) {
                                fri2++;
                            }
                            if( (rs2.getString("record_time").contains(endDate) )) {
                                sat2++;
                            }
                        }

                        TextView averageBreast = ((Activity) context).findViewById(R.id.average_breast);
                        TextView averageMilkFood = ((Activity) context).findViewById(R.id.average_milk_food);
                        averageBreast.setText(String.valueOf(sun+mon+tue+wed+thu+fri+sat));
                        averageMilkFood.setText(String.valueOf(sun2+mon2+tue2+wed2+thu2+fri2+sat2));

                        BarChart chart = (BarChart) ((Activity) context).findViewById(R.id.mealBarChart);
                        chart.getDescription().setEnabled(false); //디스크립션 삭제
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
                        Legend l = chart.getLegend();
                        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
                        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                        l.setDrawInside(false);
                        l.setFormSize(8f);
                        l.setFormToTextSpace(4f);
                        l.setXEntrySpace(6f);

                        List<BarEntry> entries = new ArrayList<>();
                        entries.add(new BarEntry(0, new float[]{sun, sun2}));
                        entries.add(new BarEntry(1, new float[]{mon, mon2}));
                        entries.add(new BarEntry(2, new float[]{tue, tue2}));
                        entries.add(new BarEntry(3, new float[]{wed, wed2}));
                        entries.add(new BarEntry(4, new float[]{thu, thu2}));
                        entries.add(new BarEntry(5, new float[]{fri, fri2}));
                        entries.add(new BarEntry(6, new float[]{sat, sat2}));



                        BarDataSet set1;
                        set1 = new BarDataSet(entries, "");
                        set1.setDrawIcons(false);

                        int[] colors = new int[3];
                        colors[0] = Color.parseColor("#a8d8ea");
                        colors[1] = Color.parseColor("#aa96da");
                        colors[2] = Color.parseColor("#fcbad3");

                        set1.setColors(colors);
                        set1.setStackLabels(new String[]{"BreastMilk", "Milk or Food"});

                        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
                        dataSets.add(set1);

                        BarData data = new BarData(dataSets);
                        data.setValueFormatter(new StackedValueFormatter(true, "", 0));
                        data.setValueTextColor(Color.WHITE);

                        chart.setData(data);

                        chart.setFitBars(true);
                        chart.invalidate();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;


                case 7:

                    try {
                        ResultSet rs = resultSet("SELECT * FROM Height");
                        // 표시할 데이터 추가 키
                        ArrayList<Entry> heights = new ArrayList<>();
                        String height = "";
                        while(rs.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            if( (rs.getString("baby_reference_date").contains(startDate) )) {
                                heights.add(new Entry(0, rs.getInt("baby_height")));
                            }
                            if( (rs.getString("baby_reference_date").contains(monDate) )) {
                                heights.add(new Entry(1, rs.getInt("baby_height")));
                            }
                            if( (rs.getString("baby_reference_date").contains(tueDate) )) {
                                heights.add(new Entry(2, rs.getInt("baby_height")));
                            }
                            if( (rs.getString("baby_reference_date").contains(wedDate) )) {
                                heights.add(new Entry(3, rs.getInt("baby_height")));
                            }
                            if( (rs.getString("baby_reference_date").contains(thuDate) )) {
                                heights.add(new Entry(4, rs.getInt("baby_height")));
                            }
                            if( (rs.getString("baby_reference_date").contains(friDate) )) {
                                heights.add(new Entry(5, rs.getInt("baby_height")));
                            }
                            if( (rs.getString("baby_reference_date").contains(endDate) )) {
                                heights.add(new Entry(6, rs.getInt("baby_height")));
                            }
                            height = rs.getString("baby_height");
                        }
                        ResultSet rs2 = resultSet("SELECT * FROM Weight");
                        // 표시할 데이터 추가 키
                        ArrayList<Entry> weights = new ArrayList<>();
                        String weight = "";
                        while(rs2.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            if( (rs2.getString("baby_reference_date").contains(startDate) )) {
                                weights.add(new Entry(0, rs2.getInt("baby_weight")));
                            }
                            if( (rs2.getString("baby_reference_date").contains(monDate) )) {
                                weights.add(new Entry(1, rs2.getInt("baby_weight")));
                            }
                            if( (rs2.getString("baby_reference_date").contains(tueDate) )) {
                                weights.add(new Entry(2, rs2.getInt("baby_weight")));
                            }
                            if( (rs2.getString("baby_reference_date").contains(wedDate) )) {
                                weights.add(new Entry(3, rs2.getInt("baby_weight")));
                            }
                            if( (rs2.getString("baby_reference_date").contains(thuDate) )) {
                                weights.add(new Entry(4, rs2.getInt("baby_weight")));
                            }
                            if( (rs2.getString("baby_reference_date").contains(friDate) )) {
                                weights.add(new Entry(5, rs2.getInt("baby_weight")));
                            }
                            if( (rs2.getString("baby_reference_date").contains(endDate) )) {
                                weights.add(new Entry(6, rs2.getInt("baby_weight")));
                            }
                            weight = rs2.getString("baby_weight");
                        }

                        ResultSet rs3 = resultSet("SELECT * FROM HeadSize");
                        String head= "";
                        while(rs3.next()) //없을때까지 도는거지
                        {
                            head = rs3.getString("baby_head_size");
                        }


                        TextView averageHeight = ((Activity) context).findViewById(R.id.average_height);
                        TextView averageWeight = ((Activity) context).findViewById(R.id.average_weight);
                        TextView averageHead = ((Activity) context).findViewById(R.id.average_head_size);
                        averageHeight.setText(height+"cm");
                        averageWeight.setText(weight+"kg");
                        averageHead.setText(head+"inch");

                        // Dataset 설정
                        LineChart chart_height = ((Activity) context).findViewById(R.id.chart_mainchart);

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
                        chart_height.invalidate();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;





            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        //로딩 종료
        if(asyncDialog!=null) {
            asyncDialog.setCanceledOnTouchOutside(true);
            asyncDialog.dismiss();
        }
    }


    public ArrayList<Integer> staticsWeek(){

        ArrayList<Integer> weekDays = new ArrayList<>();

        weekDays.add(sun);
        weekDays.add(mon);
        weekDays.add(tue);
        weekDays.add(wed);
        weekDays.add(thu);
        weekDays.add(fri);
        weekDays.add(sat);

        return weekDays;
    }





}
