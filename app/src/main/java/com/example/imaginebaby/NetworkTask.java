package com.example.imaginebaby;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class NetworkTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private String url;
    private String data;
    private int selection;
    ProgressDialog asyncDialog;
    List<RecordsListItem> items = new ArrayList<>();
    DBConnection dbConnection = new DBConnection();

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
       /* try
        {
            Connection conn = dbConnection.connectionClass();
            Log.e("너의이름은?",conn.getMetaData().getUserName());
            //Log.e("시발",conn.getMetaData().getCatalogs().getString(0));
            //conn.getMetaData().
            String query="SELECT * FROM TestBaby;";
            Statement stmt=conn.createStatement();
            Log.e("tlqkf", String.valueOf(stmt));
            ResultSet rs=stmt.executeQuery(query);
            Log.e("tlqkf", String.valueOf(rs));

            while(rs.next())
            {
                if ( isCancelled() ) break;
                Log.d("성공이야",rs.getString(1));
                Log.d("성공이야",rs.getString(2));
                Log.d("성공이야",rs.getString(3));
                Log.d("성공이야",rs.getString(4));
                //1~4 행을 보여주는거같다 0은 없다
            }
            conn.close();
        }
        catch (Exception ex)
        {
            Log.e("시발..?","ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
        }*/

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


    @Override
    protected void onPostExecute(String result) {

        try {
            //경우에 따라 결과 값을 받아 일어났으면 하는 작업
            switch (this.selection) {

                //리스트 받아오는 곳
                case 1:
                    try {

                        Connection conn = dbConnection.connectionClass();
                        String query="SELECT * FROM TestBaby;"; //여기서 원하는 쿼리문을 넣고
                        Statement stmt=conn.createStatement();
                        ResultSet rs=stmt.executeQuery(query);
                        while(rs.next()) //없을때까지 도는거지
                        {
                            if ( isCancelled() ) break;
                            //rs.get원하는형식("데이터이름")
                            RecordsListItem item = new RecordsListItem(rs.getInt("baby_image"),rs.getString("baby_title"),rs.getString("baby_desc"),rs.getString("real_time"));
                            items.add(item);
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

                case 3:
                    try {
                        Connection con = dbConnection.connectionClass();
                        String query = "DELETE FROM TestBaby WHERE baby_desc ="+"'"+data+"'" ;
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

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






}
