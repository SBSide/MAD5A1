package com.example.iveci.mad5;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
import java.util.ArrayList;

public class RMainActivity extends AppCompatActivity {
    final int NEW = 11;
    ListView lv;
    TextView rnum;
    Button brem,bsel;
    ArrayList<Rest> rdata = new ArrayList<>();
    RestAdapter adapter;
    CheckBox c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("나의 맛집");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain);
        rnum = (TextView) findViewById(R.id.tv);
        brem = (Button) findViewById(R.id.bremove);
        bsel = (Button) findViewById(R.id.bselect);
        c1   = (CheckBox) findViewById(R.id.checkBox);
        setListView();
    }
    public void onClick(View v){
        if (v.getId() == R.id.badd){
            Intent intent = new Intent(this,RMain2Activity.class);
            startActivityForResult(intent,NEW);
        }
        else if (v.getId() == R.id.bsname) {
            adapter.setAscSort(true);
        }
        else if (v.getId() == R.id.bscat) {
            adapter.setAscSort(false);
        }
        else if (v.getId() == R.id.bremove) {
            createDialog();
        }
        else if (v.getId() == R.id.bselect) {
            bsel.setVisibility(View.INVISIBLE);
            brem.setVisibility(View.VISIBLE);
            adapter.ViewCheckbox(true);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW){
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
        lv = (ListView) findViewById(R.id.listview);
        adapter = new RestAdapter(this,rdata);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),RMain3Activity.class);
                intent.putExtra("info",rdata.get(position));
                startActivity(intent);
            }
        });
    }
    public void createDialog(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("맛집삭제")
                .setMessage("선택한 맛집을 삭제합니다. 계속합니까?")
                .setNegativeButton("아뇨", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                "취소했습니다..",Toast.LENGTH_SHORT)
                                .show();
                        bsel.setVisibility(View.VISIBLE);
                        brem.setVisibility(View.INVISIBLE);
                        adapter.ViewCheckbox(false);
                    }
                })
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0 ; i < rdata.size();){
                            if (rdata.get(i).checked) rdata.remove(i);
                            else i++;
                        }
                        bsel.setVisibility(View.VISIBLE);
                        brem.setVisibility(View.INVISIBLE);
                        adapter.ViewCheckbox(false);
                        Toast.makeText(getApplicationContext(),
                                "맛집을 삭제했습니다.",Toast.LENGTH_SHORT)
                                .show();
                    }
                }).show();
    }
}
