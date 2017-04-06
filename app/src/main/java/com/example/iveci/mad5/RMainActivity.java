package com.example.iveci.mad5;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RMainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Rest> rdata = new ArrayList<>();
    ArrayAdapter<Rest> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain);
        setListView();
    }
    public void onClick(View v){
        Intent intent = new Intent(this,RMain2Activity.class);
        startActivityForResult(intent,11);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11){
            if(resultCode == RESULT_OK){
                Rest accept = data.getParcelableExtra("name");
                rdata.add(accept);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"입력했습니다.",Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(),"입력을 취소했습니다.",Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setListView(){
        lv = (ListView) findViewById(R.id.l1);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,rdata);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                rdata.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
