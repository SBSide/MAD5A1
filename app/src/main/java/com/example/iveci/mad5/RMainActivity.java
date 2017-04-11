package com.example.iveci.mad5;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RMainActivity extends AppCompatActivity {
    ListView lv;
    TextView rnum;
    ArrayList<Rest> rdata = new ArrayList<>();
    ArrayAdapter<Rest> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain);
        rnum = (TextView) findViewById(R.id.tv);
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
                rnum.setText("맛집 리스트("+rdata.size()+"개)");
                Toast.makeText(getApplicationContext(),"입력했습니다.",Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(),"입력을 취소했습니다.",Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setListView(){
        lv = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,rdata);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                createDialog(position);
                return false;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    public void createDialog(int pos){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        final int position = pos;
        dlg.setTitle("맛집삭제")
                .setMessage("맛집을 삭제합니다. 계속합니까?")
                .setNegativeButton("아뇨", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                "취소했습니다..",Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rdata.remove(position);
                        adapter.notifyDataSetChanged();
                        rnum.setText("맛집 리스트("+rdata.size()+"개)");
                        Toast.makeText(getApplicationContext(),
                                "맛집을 삭제했습니다.",Toast.LENGTH_SHORT)
                                .show();
                    }
                }).show();
    }
}
