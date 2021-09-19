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

import org.w3c.dom.Text;

public class scheme2status extends AppCompatActivity {
TextView t1,t2;
EditText e1;
FirebaseDatabase database;
DatabaseReference myref456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme2status);
        database=FirebaseDatabase.getInstance();
        t1=(TextView)findViewById(R.id.contents12);
        t2=(TextView)findViewById(R.id.contents123);
        e1=(EditText)findViewById(R.id.editTextaano12);
        myref456=database.getReference().child("scheme2");
    }
    public void scheme2click(View v){
        final String str4=e1.getText().toString();
        myref456.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(str4.length()==12 && !str4.isEmpty()){
                    if(dataSnapshot.child(str4).exists()){
                        final String status1 = dataSnapshot.child(str4).child("Status").getValue().toString();
                        final String amounts1 = dataSnapshot.child(str4).child("Sanctioned").getValue().toString();
                        t1.setText("Status Of Application : " + status1);
                        t2.setText("Sanctioned Amount  :  " + amounts1);
                    } else {
                        Toast.makeText(scheme2status.this, "Scheme not applied", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(scheme2status.this,"Enter valid aadhaar number",Toast.LENGTH_LONG).show();
                }

                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
