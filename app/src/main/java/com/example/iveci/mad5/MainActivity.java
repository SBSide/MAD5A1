package com.example.iveci.mad5;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText);
    }

    public void onClick(View v){
        if (v.getId() == R.id.button) {
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("name",e1.getText().toString());
            startActivityForResult(intent,59);
        }
        else if (v.getId() == R.id.button3){
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent,0);
        }
        else if (v.getId() == R.id.button4){
            Intent intent = new Intent(this, Main2Activity.class);
            Student s1 = new Student("KIM","2017001122",20,1);
            intent.putExtra("student1",s1);
            startActivityForResult(intent,1);
        }
        else if (v.getId() == R.id.button5){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:/+821031508740"));
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 59){
            if(resultCode == RESULT_OK){
                String msg = data.getStringExtra("name");
                e1.setText(msg);
            }
        }
        else if (requestCode == 0) ;
        else if (requestCode == 1) {
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
