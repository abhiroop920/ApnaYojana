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

public class disablitydetails extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
    EditText e1;
    FirebaseDatabase database;
    DatabaseReference myref300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disablitydetails);
        t1=(TextView)findViewById(R.id.name000);
        t2=(TextView)findViewById(R.id.fathername000);
        t3=(TextView)findViewById(R.id.gender000);
        t4=(TextView)findViewById(R.id.age000);
        t5=(TextView)findViewById(R.id.phone000);
        t6=(TextView)findViewById(R.id.house000);
        t7=(TextView)findViewById(R.id.income000);
        t8=(TextView)findViewById(R.id.city000);
        t9=(TextView)findViewById(R.id.state000);
        t10=(TextView)findViewById(R.id.phyno000);
        t11=(TextView)findViewById(R.id.statuss);
        database=FirebaseDatabase.getInstance();
        e1=(EditText)findViewById(R.id.aadh);
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
    public void scheme2users(View v){
        Intent i=new Intent(disablitydetails.this,Listofchild.class);
        startActivity(i);
    }
    public void details22(View v){
        final String key=e1.getText().toString();
        myref300=database.getReference().child("scheme2");
        if(!key.isEmpty() && key.length()==12){
            myref300.addListenerForSingleValueEvent(new ValueEventListener() {
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
                        String s10=dataSnapshot.child(key).child("Certificate No").getValue().toString();
                        String s11=dataSnapshot.child(key).child("Status").getValue().toString();
                        t1.setText("Name    :  "+s1);
                        t2.setText("Father Name   :  "+s2);
                        t3.setText("Gender    :  "+s3);
                        t4.setText("Age    :  "+s4);
                        t5.setText("Phone    :  "+s5);
                        t6.setText("Income    :  "+s6);
                        t7.setText("House Number    :  "+s7);
                        t8.setText("State  :  "+s8);
                        t9.setText("City    :  "+s9);
                        t10.setText("Certificate Number   :  "+s10);
                        t11.setText("Application Status  :  "+s11);

                    }
                    else
                    {


                        Toast.makeText(disablitydetails.this,"Aadhar Number Not Exists",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
            Toast.makeText(disablitydetails.this,"Enter Valid Details",Toast.LENGTH_LONG).show();
        }
    }
}