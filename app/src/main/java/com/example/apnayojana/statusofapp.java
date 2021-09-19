package com.example.apnayojana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class statusofapp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusofapp);
    }
    public void one_jobapp(View v){
        Intent i=new Intent(statusofapp.this,scheme1status.class);
        startActivity(i);
    }
    public void state_disabilityapp(View v){
        Intent i=new Intent(statusofapp.this,scheme2status.class);
        startActivity(i);
    }
    public void scheme3app(View v){
        Toast.makeText(statusofapp.this,"porcessing",Toast.LENGTH_LONG).show();
    }
    public void scheme4app(View v){
        Toast.makeText(statusofapp.this,"porcessing",Toast.LENGTH_LONG).show();
    }
    public void scheme5app(View v){
        Toast.makeText(statusofapp.this,"porcessing",Toast.LENGTH_LONG).show();
    }
}
