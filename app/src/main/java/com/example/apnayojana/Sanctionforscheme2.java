package com.example.apnayojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;

public class Sanctionforscheme2 extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref55;
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanctionforscheme2);
        e1=(EditText)findViewById(R.id.sanction);
        database=FirebaseDatabase.getInstance();
    }
    public void button000(View v){
        Intent i1=getIntent();
        final String numberval=i1.getStringExtra("numbers");
        final String num=e1.getText().toString();
        myref55=database.getReference().child("scheme2");
        if(!num.isEmpty()){
            myref55.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(numberval).exists()){
                        final String str=dataSnapshot.child(numberval).child("Sanctioned").getValue().toString();
                            myref55.child(numberval).child("Sanctioned").setValue(num);
                            myref55.child(numberval).child("Status").setValue("Verified");
                            Toast.makeText(Sanctionforscheme2.this, "Sanctioned Successfully", Toast.LENGTH_LONG).show();
                            e1.setText("");

                    }
                    else
                    {
                        Toast.makeText(Sanctionforscheme2.this,"Not Registered For this Scheme",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
            Toast.makeText(Sanctionforscheme2.this,"Enter Valid Number",Toast.LENGTH_LONG).show();
        }

    }
}
