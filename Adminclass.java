package com.example.disastermanagementfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Adminclass extends AppCompatActivity {
Map<String,String> passwords;
EditText passtext;
Button b;
String stations[]={"old palace","chattarpur","modinagar"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminclass);
        passwords=new HashMap<>();
        passtext=findViewById(R.id.password);
        b=findViewById(R.id.admin);
        passwords.put("old palace","110003@admin_aajtak");
        passwords.put("chattarpur","110070@admin_rachit");
        passwords.put("modinagar","201204@admin_yash");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(passtext.getText().toString())) {
                      for(int i=0; i<passwords.size(); i++) {
                             if(passtext.getText().toString().equals(passwords.get(stations[i]))) {
                                             if(stations[i].equals("old palace")) {
                                                        // old palace activity
                                                 Intent ix=new Intent(Adminclass.this,Oldpalace.class);
                                                 startActivity(ix);

                                             }

                                             if(stations[i].equals("chattarpur")) {
                                                 // chattarpur activity
                                                 Intent in=new Intent(Adminclass.this,Chattarpur.class);
                                                   startActivity(in);
                                             }

                                             if(stations[i].equals("modinagar")) {
                                                             Intent i2=new Intent(Adminclass.this,Modinagar.class);
                                                             startActivity(i2);
                                                       // modinagar activity
                                             }
                             }

                      }

                }
            }
        });
    }
}
