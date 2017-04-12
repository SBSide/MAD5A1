package com.example.iveci.mad5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RMain3Activity extends AppCompatActivity {
    TextView m1,m2,m3,tel,url,dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain3);
        Intent intent = getIntent();
        Rest info = intent.getParcelableExtra("info");
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.imageView2 : {

            }
            case R.id.imageView3 : {

            }
            case R.id.btnback : {finish();}
        }
    }
}
