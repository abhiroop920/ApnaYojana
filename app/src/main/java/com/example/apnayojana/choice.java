package com.example.apnayojana;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Toast;

public class choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }
    public void pressed(View v){
        Intent i00=new Intent(choice.this,adminloggedin.class);
        startActivity(i00);

    }
    public void press(View v){
        Intent i=new Intent(choice.this,schemeopen.class);
        startActivity(i);

    }
}