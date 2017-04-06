package com.example.iveci.mad5;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    ListView lv;
    EditText e1;
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        e1 = (EditText) findViewById(R.id.eaddm);
        setListView();
    }

    public void onClick(View v){
        data.add(e1.getText().toString());
        adapter.notifyDataSetChanged();
    }
    public void setListView(){
    lv = (ListView) findViewById(R.id.l1);
        data.add("사과");
        data.add("포도");
        data.add("복숭아");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                data.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
