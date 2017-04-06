package com.example.iveci.mad5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        e2 = (EditText) findViewById(R.id.editText2);
        Intent intent = getIntent();
//        String mod = intent.getStringExtra("name");
        Student ss = intent.getParcelableExtra("student1");
        String st = ss.toString();
        e2.setText(st);
    }

    public void onClick(View v){
        Intent intent = new Intent();
        intent.putExtra("name", e2.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}
