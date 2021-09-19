package com.example.apnayojana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Ask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        Button b1 =(Button) findViewById(R.id.enterman);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Ask.this,aadharregister.class);
                startActivity(intent);
            }
        });

        ImageButton b2 =(ImageButton) findViewById(R.id.scanqr);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(Ask.this,Scanning.class);
                startActivity(intent);
            }
        });

    }


}
