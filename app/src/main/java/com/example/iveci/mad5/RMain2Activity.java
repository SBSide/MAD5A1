package com.example.iveci.mad5;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RMain2Activity extends Activity {
    EditText name,tel,menu1,menu2,menu3,hp;
    RadioButton r1,r2,r3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmain2);
        init();
    }
    public void init(){
        name  = (EditText) findViewById(R.id.etname);
        tel   = (EditText) findViewById(R.id.ettel);
        menu1 = (EditText) findViewById(R.id.etmenu1);
        menu2 = (EditText) findViewById(R.id.etmenu2);
        menu3 = (EditText) findViewById(R.id.etmenu3);
        hp    = (EditText) findViewById(R.id.etaddr);
        r1    = (RadioButton) findViewById(R.id.radio1);
        r2    = (RadioButton) findViewById(R.id.radio2);
        r3    = (RadioButton) findViewById(R.id.radio3);

    }

    public void onClick(View v){
        Intent intent = new Intent();
        if (v.getId() == R.id.btnAdd){
            ArrayList<String> Menu = new ArrayList<>();
            Menu.add(menu1.getText().toString());
            Menu.add(menu2.getText().toString());
            Menu.add(menu3.getText().toString());
            int cat;
            if (r1.isChecked()) cat = 1;
            else if (r2.isChecked()) cat = 2;
            else cat = 3;
            Rest rest = new Rest(name.getText().toString(),
                    tel.getText().toString(),
                    Menu,
                    hp.getText().toString(),
                    " " + new SimpleDateFormat("yyyyMMdd HH:mm").format(new Date(System.currentTimeMillis())),
                    cat);
            intent.putExtra("name", rest);
            setResult(RESULT_OK,intent);
            finish();
        }
        else if (v.getId() == R.id.btnCancel){
            setResult(RESULT_CANCELED,intent);
            finish();
        }

    }
}
