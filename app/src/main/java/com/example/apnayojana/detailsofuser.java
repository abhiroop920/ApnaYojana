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

public class detailsofuser extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    EditText e1;
    FirebaseDatabase database;
    DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsofuser);
        t1 = (TextView)findViewById(R.id.name);
        e1=(EditText)findViewById(R.id.username);
        t2=(TextView)findViewById(R.id.password);
        t3=(TextView)findViewById(R.id.phone);
        t4=(TextView)findViewById(R.id.secans);
        database=FirebaseDatabase.getInstance();
        e1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    v.setBackgroundResource(R.drawable.focus_border_style);
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });

    }
    public void details(View v){
        final String key=e1.getText().toString();
        myref=database.getReference().child("users");
        if(!key.isEmpty()){
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(key).exists()){
                        String s1=dataSnapshot.child(key).child("Name").getValue().toString();
                        String s2=dataSnapshot.child(key).child("password").getValue().toString();
                        String s3= dataSnapshot.child(key).child("mobile").getValue().toString();
                        String s4=dataSnapshot.child(key).child("answer").getValue().toString();
                        t1.setText(s1);
                        t2.setText(s2);
                        t3.setText(s3);
                        t4.setText(s4);
                    }
                    else
                    {
                        Toast.makeText(detailsofuser.this,"User Name Invalid",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
            Toast.makeText(detailsofuser.this,"Enter details completely",Toast.LENGTH_LONG).show();
        }

    }
}