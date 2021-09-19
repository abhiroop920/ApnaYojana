package com.example.apnayojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class onefamily extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    EditText e1;
    FirebaseDatabase database;
    DatabaseReference myref33;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onefamily);
        t1=(TextView)findViewById(R.id.name0);
        t2=(TextView)findViewById(R.id.fathername0);
        t3=(TextView)findViewById(R.id.gender0);
        t4=(TextView)findViewById(R.id.age0);
        t5=(TextView)findViewById(R.id.phone0);
        t6=(TextView)findViewById(R.id.house0);
        t7=(TextView)findViewById(R.id.income0);
        t8=(TextView)findViewById(R.id.city0);
        t9=(TextView)findViewById(R.id.state0);
        database=FirebaseDatabase.getInstance();
        e1=(EditText)findViewById(R.id.aano);
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
    public void schemeusers(View v){
        Toast.makeText(onefamily.this,"processing",Toast.LENGTH_LONG).show();
    }
    public void details1(View v){
        final String key=e1.getText().toString();
        myref33=database.getReference().child("scheme1");
        if(!key.isEmpty()&& key.length()==12){
            myref33.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(key).exists()){
                        String s1=dataSnapshot.child(key).child("name").getValue().toString();
                        String s2=dataSnapshot.child(key).child("fathername").getValue().toString();
                        String s3= dataSnapshot.child(key).child("gender").getValue().toString();
                        String s4=dataSnapshot.child(key).child("age").getValue().toString();
                        String s5=dataSnapshot.child(key).child("phone").getValue().toString();
                        String s6=dataSnapshot.child(key).child("income").getValue().toString();
                        String s7= dataSnapshot.child(key).child("housenumber").getValue().toString();
                        String s8=dataSnapshot.child(key).child("State").getValue().toString();
                        String s9=dataSnapshot.child(key).child("city").getValue().toString();
                        t1.setText("Name    :  "+s1);
                        t2.setText("Father Name   :  "+s2);
                        t3.setText("Gender    :  "+s3);
                        t4.setText("Age    :  "+s4);
                        t5.setText("Phone    :  "+s5);
                        t6.setText("Income    :  "+s6);
                        t7.setText("House Number    :  "+s7);
                        t8.setText("State  :  "+s8);
                        t9.setText("City    :  "+s9);

                    }
                    else
                    {
                        Toast.makeText(onefamily.this,"Aadhaar Number Not Exists",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
            Toast.makeText(onefamily.this,"Enter Valid Details",Toast.LENGTH_LONG).show();
        }
    }

}