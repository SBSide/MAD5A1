package com.example.iveci.mad5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RMain3Activity extends AppCompatActivity {
    TextView m0,m1,m2,m3,tel,url,dat;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain3);
        init();
        Intent intent = getIntent();
        Rest info = intent.getParcelableExtra("info");
        if(info.catnumber == 1) img.setImageResource(R.drawable.chicken);
        else if(info.catnumber == 2) img.setImageResource(R.drawable.pizza);
        else img.setImageResource(R.drawable.hamburger);
        m0.setText(info.name);
        m1.setText(info.menu.get(0));
        m2.setText(info.menu.get(1));
        m3.setText(info.menu.get(2));
        tel.setText(info.tel);
        url.setText(info.homepage);
        dat.setText(info.date);
    }

    public void init(){
        img = (ImageView) findViewById(R.id.imgno);
        m0 = (TextView) findViewById(R.id.txtname);
        m1 = (TextView) findViewById(R.id.etmenu1);
        m2 = (TextView) findViewById(R.id.etmenu2);
        m3 = (TextView) findViewById(R.id.etmenu3);
        tel = (TextView) findViewById(R.id.tvTel);
        url = (TextView) findViewById(R.id.tvURL);
        dat = (TextView) findViewById(R.id.tvRegdate);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.imageView2 : {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tel.getText().toString()));
                startActivity(intent);
                break;
            }
            case R.id.imageView3 : {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+url.getText().toString()));
                startActivity(intent);
                break;
            }
            case R.id.btnback : {
                finish();
                break;
            }
        }
    }
}
