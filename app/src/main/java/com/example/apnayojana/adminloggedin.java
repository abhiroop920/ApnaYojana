package com.example.apnayojana;


        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

public class adminloggedin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminloggedin);
    }
    public void detailswant(View v){
        Intent i1=new Intent(adminloggedin.this,detailsofuser.class);
        startActivity(i1);
    }
    public void updatewant(View v){
        Intent i2=new Intent(adminloggedin.this,updatedetailsofuser.class);
        startActivity(i2);
    }
}