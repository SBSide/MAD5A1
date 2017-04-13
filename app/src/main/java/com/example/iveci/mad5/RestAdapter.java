package com.example.iveci.mad5;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by iveci on 2017-04-13.
 */

public class RestAdapter extends BaseAdapter {
    ArrayList<Rest> data = new ArrayList<>();
    Context context;
    CheckBox c1;
    Boolean vis = false;

    public RestAdapter(Context context, ArrayList<Rest> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.rinfo, null);
        }
        TextView t1 = (TextView) convertView.findViewById(R.id.trestname);
        TextView t2 = (TextView) convertView.findViewById(R.id.tresttel);
        ImageView img = (ImageView) convertView.findViewById(R.id.cimg);
        c1 = (CheckBox) convertView.findViewById(R.id.checkBox);
        final Rest td = data.get(position);
        t1.setText(td.name);
        t2.setText(td.tel);
        if(td.catnumber == 1) img.setImageResource(R.drawable.chicken);
        else if(td.catnumber == 2) img.setImageResource(R.drawable.pizza);
        else img.setImageResource(R.drawable.hamburger);
        c1.setChecked(td.checked ? true : false);
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                td.checked = isChecked;
            }
        });
        if (vis) c1.setVisibility(View.VISIBLE);
        else c1.setVisibility(View.INVISIBLE);
        return convertView;
    }

    Comparator<Rest> nameAsc = new Comparator<Rest>() {
        @Override
        public int compare(Rest o1, Rest o2) {
            return o1.name.compareToIgnoreCase(o2.name);
        }
    };

    Comparator<Rest> cateAsc = new Comparator<Rest>() {
        @Override
        public int compare(Rest o1, Rest o2) {
            return Integer.toString(o1.catnumber).compareToIgnoreCase(Integer.toString(o2.catnumber));
        }
    };

    public void setAscSort(boolean byname){
        if (byname) Collections.sort(data,nameAsc);
        else Collections.sort(data,cateAsc);
        this.notifyDataSetChanged();
    }

    public void ViewCheckbox(boolean visible){
        vis = visible;
        this.notifyDataSetChanged();
    }
}
