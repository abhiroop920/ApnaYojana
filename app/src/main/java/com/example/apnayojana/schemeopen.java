package com.example.apnayojana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class schemeopen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemeopen);

    }
    public void one_job(View v){
        Intent i=new Intent(schemeopen.this,scheme1choice.class);
        startActivity(i);
    }
    public void state_disability(View v){
        Intent i=new Intent(schemeopen.this,scheme2choice.class);
        startActivity(i);
    }
    public void scheme3(View v){
        Toast.makeText(schemeopen.this,"porcessing",Toast.LENGTH_LONG).show();
    }
    public void scheme4(View v){
        Toast.makeText(schemeopen.this,"porcessing",Toast.LENGTH_LONG).show();
    }
    public void scheme5(View v){
        Toast.makeText(schemeopen.this,"porcessing",Toast.LENGTH_LONG).show();
    }
}