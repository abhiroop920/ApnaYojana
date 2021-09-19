package com.example.apnayojana;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Schemepage extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemepage);
    }

    public void schemeregister(View v)
    {
        Intent itnt =new Intent(Schemepage.this,Ask.class);
        startActivity(itnt);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
