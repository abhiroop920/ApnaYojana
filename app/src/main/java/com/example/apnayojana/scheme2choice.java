package com.example.apnayojana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class scheme2choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme2choice);
    }
    public void pressed10(View v){
        Intent i1=new Intent(scheme2choice.this,disablitydetails.class);
        startActivity(i1);
    }
    public void press10(View v){
       Intent i=new Intent(scheme2choice.this,statedisabilityverification.class);
       startActivity(i);

    }
}