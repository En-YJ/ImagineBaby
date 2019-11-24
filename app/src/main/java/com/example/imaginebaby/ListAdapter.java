package com.example.imaginebaby;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
/**
 * 기록 리스트뷰 어댑터 진짜 사용하는거!
 */
public class ListAdapter extends ArrayAdapter<RecordsListItem> {
    private final LayoutInflater mInflater;
    private final ViewBinderHelper binderHelper;

    public ListAdapter(Context context, List<RecordsListItem> recordsListItems) {
        super(context, R.layout.records_listview_item, recordsListItems);
        mInflater = LayoutInflater.from(context);
        binderHelper = new ViewBinderHelper();
        binderHelper.setOpenOnlyOne(true);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        Drawable image = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.records_listview_item, parent, false);

            holder = new ViewHolder();
            holder.swipeLayout = (SwipeRevealLayout) convertView.findViewById(R.id.swipe_layout_1);
            holder.frontView = convertView.findViewById(R.id.front_layout);
            holder.deleteView = convertView.findViewById(R.id.item_delete);
            holder.editView = convertView.findViewById(R.id.item_edit);

            holder.reccords_image = (CircleImageView) convertView.findViewById(R.id.records_list_image);
            holder.records_title = (TextView) convertView.findViewById(R.id.records_list_title);
            holder.records_desc = (TextView) convertView.findViewById(R.id.records_list_desc);
            holder.records_time = (TextView) convertView.findViewById(R.id.records_list_time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final RecordsListItem item = getItem(position);
        if (item != null) {
            binderHelper.bind(holder.swipeLayout, String.valueOf(item));

            switch (item.getRecords_image())
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
            
            //holder.reccords_image.setImageResource();
            Glide.with(getContext())
                    .load(image)
                    .into(holder.reccords_image);
            holder.records_title.setText(item.getRecords_title());
            holder.records_desc.setText(item.getRecords_desc());
            holder.records_time.setText(item.getRecords_time());

            //삭제
            holder.deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(item);
                }
            });

            //수정
            holder.editView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), RecordsEditActivity.class);
                    getContext().startActivity(intent);
                }
            });

            //리스트뷰 자세히 보기
            holder.frontView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "클릭..!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return convertView;
    }

    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }


    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

    private class ViewHolder {
        SwipeRevealLayout swipeLayout;
        View frontView;
        View deleteView;
        View editView;
        CircleImageView reccords_image;
        TextView records_title;
        TextView records_desc;
        TextView records_time;
    }
}
