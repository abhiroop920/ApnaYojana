package com.example.apnayojana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Schemepage1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemepage1);
    }
    public void registration(View v){
        Intent i=new Intent(Schemepage1.this,aadharregister1.class);
        startActivity(i);

    }
}