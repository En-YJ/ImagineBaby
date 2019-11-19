package com.example.imaginebaby;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NetworkTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private String url;
    private String data;
    private int selection;
    ProgressDialog asyncDialog;
    List<RecordsListItem> items = new ArrayList<>();

    public NetworkTask(Context _context, String url, String data, int action){
        this.context = _context;
        this.url = url;
        this.data = data;
        this.selection = action;
        if(_context != null)
            this.asyncDialog = new ProgressDialog(_context);
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;

        try {
            //items = GetXmlData();
            /*RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.Request(url, data);*/

        }catch (Exception e){
            result = "Error";
        }
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

                case 1:
                    try {
                        /*RecyclerView recyclerView = (RecyclerView) ((Activity) context).findViewById(R.id.real_estate_list);
                        recyclerView.setAdapter(new RealEstateRecyclerAdapter(context, items, R.layout.content_main));
                        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(layoutManager);*/
                        /*JSONObject jsonObject = new JSONObject(result);
                        String resultMsg = jsonObject.getString("resultCode");
                        if (resultMsg.equals("00")) {
                        } else {
                        }*/
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
