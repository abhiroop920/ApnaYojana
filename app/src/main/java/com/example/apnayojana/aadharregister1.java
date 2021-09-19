package com.example.apnayojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class aadharregister1 extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref5,myref000;
    EditText e1;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadharregister1);
        database=FirebaseDatabase.getInstance();
        e1=(EditText)findViewById(R.id.number1);
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
    public void verify(View c){
        final String str2=e1.getText().toString();
        myref5=database.getReference().child("scheme2");
        myref000=database.getReference().child("people");
        if(str2.length()==12 && !str2.isEmpty()){
            myref000.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(str2).exists()){
                        count=1;
                    }
                    else
                    {
                        count=0;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            myref5.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(str2).exists() && count==1){
                        Toast.makeText(aadharregister1.this,"Already registered with scheme",Toast.LENGTH_LONG).show();
                        e1.setText("");
                    }
                    else if (count==1)
                    {
                        Intent i2=new Intent(aadharregister1.this,verification2.class);
                        i2.putExtra("aa number",str2);
                        startActivity(i2);
                        e1.setText("");
                    }
                    else
                    {
                        Toast.makeText(aadharregister1.this,"Enter Valid aadhar number",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else
        {
            Toast.makeText(aadharregister1.this,"Enter Valid Aadhaar number",Toast.LENGTH_LONG).show();
        }
    }
}