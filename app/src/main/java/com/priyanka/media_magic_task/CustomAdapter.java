package com.priyanka.media_magic_task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<itemModel> arrayList;

    public CustomAdapter(Context context, ArrayList<itemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_single, parent, false);
        }
        TextView name;
        final ImageView image;
        name = (TextView) convertView.findViewById(R.id.grid_text);
        image = (ImageView) convertView.findViewById(R.id.grid_image);

        name.setText(arrayList.get(position).getName());
        String url = "";
        url = arrayList.get(position).getUrl();

        new DownloadImage(image).execute(url);

        return convertView;
        }

    }
