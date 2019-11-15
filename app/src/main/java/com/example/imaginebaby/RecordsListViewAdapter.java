package com.example.imaginebaby;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 기록 리스트뷰 어댑터 클래스
 */
public class RecordsListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<RecordsListItem> recordsData;
    private int layout;

    private CircleImageView reccords_image;
    private TextView records_title;
    private TextView records_desc;
    private TextView records_time;

    private Drawable image;

    // 생성자
    public RecordsListViewAdapter(LayoutInflater inflater, int layout, ArrayList<RecordsListItem> recordsData){
        this.inflater = inflater;
        this.recordsData = recordsData;
        this.layout=layout;
    }

    public ArrayList<RecordsListItem> getRecordsList(){
        return recordsData;
    }

    public void addItem(RecordsListItem item){
        recordsData.add(item);
    }

    @Override
    public int getCount(){return recordsData.size();}
    @Override
    public String getItem(int position){return recordsData.get(position).getRecords_title();}
    @Override
    public long getItemId(int position){return position;}
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView=inflater.inflate(layout,parent,false);
        }

        RecordsListItem recordsListItem= recordsData.get(position);

        switch (recordsListItem.getRecords_image())
        {
            case 1:
                image = convertView.getResources().getDrawable(R.drawable.ic_bottle);
                break;
            case 2:
                image=convertView.getResources().getDrawable(R.drawable.ic_diaper);
                break;
            case 3:
                image=convertView.getResources().getDrawable(R.drawable.ic_growth);
                break;
            case 4:
                image=convertView.getResources().getDrawable(R.drawable.ic_sleep2);
                break;
        }

        reccords_image= (CircleImageView)convertView.findViewById(R.id.records_list_image);
            Glide.with(convertView)
                .load(image)
                .into(reccords_image);

        records_title=(TextView)convertView.findViewById(R.id.records_list_title);
        records_title.setText(recordsListItem.getRecords_title());

        records_desc=(TextView)convertView.findViewById(R.id.records_list_desc);
        records_desc.setText(recordsListItem.getRecords_desc());

        records_time=(TextView)convertView.findViewById(R.id.records_list_time);
        records_time.setText(recordsListItem.getRecords_time());

        //삭제하는 부분 고민해야함 아직 이상해
        TextView delete = convertView.findViewById(R.id.item_delete);
        delete.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = position;
                //ListView recordsList =(ListView) convertView.findViewById(R.id.records_listView);
                //position = recordsList.getCheckedItemPosition();
                if(pos != ListView.INVALID_POSITION)
                {
                    recordsData.remove(pos);
                    //recordsList.clearChoices();
                    RecordsListViewAdapter.this.notifyDataSetChanged();
                }
                return;
            }
        });



        return convertView;
    }
}
