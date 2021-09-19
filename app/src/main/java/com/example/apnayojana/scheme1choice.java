package com.example.apnayojana;

        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Toast;

public class scheme1choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme1choice);
    }
    public void pressed1(View v){
        Intent i=new Intent(scheme1choice.this,onefamily.class);
        startActivity(i);
    }
    public void verifynow(View v){
       Toast.makeText(scheme1choice.this,"processing",Toast.LENGTH_LONG).show();
    }
}


