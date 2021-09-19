package com.example.apnayojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class myprofile extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref;
    TextView e1,e2,e3,e4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        Intent i=getIntent();
        e1=(TextView)findViewById(R.id.proname);
        e2=(TextView)findViewById(R.id.propass);
        e3=(TextView)findViewById(R.id.procontact);
        e4=(TextView)findViewById(R.id.prosecurity);
        final String strs=i.getStringExtra("username");
        database=FirebaseDatabase.getInstance();
        myref=database.getReference().child("users");
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s1=dataSnapshot.child(strs).child("Name").getValue().toString();
                String s2=dataSnapshot.child(strs).child("password").getValue().toString();
                String s3= dataSnapshot.child(strs).child("mobile").getValue().toString();
                String s4=dataSnapshot.child(strs).child("answer").getValue().toString();
                e1.setText("Name    :  "+s1);
                e2.setText("Password    :  "+s2);
                e3.setText("Mobile    :  "+s3);
                e4.setText("Answer    :  "+s4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}
