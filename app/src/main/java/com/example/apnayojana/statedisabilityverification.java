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

public class statedisabilityverification extends AppCompatActivity {
    EditText e1;
    FirebaseDatabase database;
    DatabaseReference myref40;
    TextView t1, t2, t3, t4, t5, t6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statedisabilityverification);
        database = FirebaseDatabase.getInstance();
        t1 = (TextView) findViewById(R.id.name6);
        t2 = (TextView) findViewById(R.id.password6);
        t3 = (TextView) findViewById(R.id.age6);
        t4 = (TextView) findViewById(R.id.house6);
        t5 = (TextView) findViewById(R.id.city6);
        t6 = (TextView) findViewById(R.id.state6);
        e1 = (EditText) findViewById(R.id.username6);

    }

    public void button6(View v) {
        final String number = e1.getText().toString();
        myref40 = database.getReference().child("people");
        if (!number.isEmpty()) {
            myref40.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(number).exists()) {
                        String s1 = dataSnapshot.child(number).child("name").getValue().toString();
                        String s2 = dataSnapshot.child(number).child("fathername").getValue().toString();
                        String s3 = dataSnapshot.child(number).child("age").getValue().toString();
                        String s4 = dataSnapshot.child(number).child("houseno").getValue().toString();
                        String s5 = dataSnapshot.child(number).child("city").getValue().toString();
                        String s6 = dataSnapshot.child(number).child("state").getValue().toString();
                        t1.setText(s1);
                        t2.setText(s2);
                        t3.setText(s3);
                        t4.setText(s4);
                        t5.setText(s5);
                        t6.setText(s6);

                    } else {
                        Toast.makeText(statedisabilityverification.this, "Aadhaar Do not Exists", Toast.LENGTH_LONG).show();
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");
                        t5.setText("");
                        t6.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            Toast.makeText(statedisabilityverification.this,"Enter Valid Number",Toast.LENGTH_LONG).show();

        }
    }
    public void button98(View v){
        Intent i1=new Intent(statedisabilityverification.this,Sanctionforscheme2.class);
        String number=e1.getText().toString();
        i1.putExtra("numbers",number);
        startActivity(i1);

    }
}
