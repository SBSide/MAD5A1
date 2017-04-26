package com.example.iveci.mad5;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by iveci on 2017-04-13.
 */

public class RestAdapter extends BaseAdapter implements Filterable {
    ArrayList<Rest> data = new ArrayList<>();
    ArrayList<Rest> filtereddata = data;
    Context context;
    CheckBox c1;
    Boolean vis = false;
    Filter restFilter;

    public RestAdapter(Context context, ArrayList<Rest> data) {
        this.context = context;
        this.data = data;
        this.filtereddata = data;
    }

    @Override
    public int getCount() {
        return filtereddata.size();
    }

    @Override
    public Object getItem(int position) {
        return filtereddata.get(position);
    }

    public void addItem(Rest rest) {

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
        final Rest td = filtereddata.get(position);
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


    @Override
    public Filter getFilter() {
        if (restFilter == null) restFilter = new RestFilter();
        return restFilter;
    }

    private class RestFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = data;
                results.count = data.size();
            }
            else {
                ArrayList<Rest> restlist = new ArrayList<>();
                for (Rest rest : data) {
                    if (rest.name.contains(constraint.toString())) {
                        restlist.add(rest);
                    }
                }
                if (restlist.size() == 0){
                    results.values = data;
                    results.count = data.size();
                }
                else {
                    results.values = restlist;
                    results.count = restlist.size();
                }
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtereddata = (ArrayList<Rest>) results.values;
            if (results.count > 0) notifyDataSetChanged();
            else notifyDataSetInvalidated();
        }
    }
}
