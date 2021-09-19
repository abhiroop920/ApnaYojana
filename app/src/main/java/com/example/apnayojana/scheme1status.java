package com.example.apnayojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class scheme1status extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref9990;
    TextView t1;
    EditText e1;
    TextView t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme1status);
        database=FirebaseDatabase.getInstance();
        t1=(TextView)findViewById(R.id.contents);
        t2=(TextView)findViewById(R.id.contents1);
        e1=(EditText)findViewById(R.id.editTextaano1);
        myref9990=database.getReference().child("scheme1");
    }
    public void scheme1click(View v){
        final String number=e1.getText().toString();
        myref9990.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(number.length()==12 && !number.isEmpty()) {
                    if (dataSnapshot.child(number).exists()) {
                        final String status = dataSnapshot.child(number).child("Status").getValue().toString();
                        final String amounts = dataSnapshot.child(number).child("Sanctioned").getValue().toString();
                        t1.setText("Status Of Application : " + status);
                        t2.setText("Sanctioned Amount  :  " + amounts);
                    } else {
                        Toast.makeText(scheme1status.this, "Scheme not applied", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(scheme1status.this,"Enter Valid Aadhaar Number",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
