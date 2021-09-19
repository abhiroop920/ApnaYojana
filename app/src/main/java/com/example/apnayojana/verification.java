
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

public class verification extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref6;
    EditText n,fn,gen,age,phone,house,income,city,state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=FirebaseDatabase.getInstance();



        setContentView(R.layout.activity_verification);

    }
    public void lastclick(View v){

        n=(EditText)findViewById(R.id.name4);
        fn=(EditText)findViewById(R.id.fname4);
        gen=(EditText)findViewById(R.id.gen4);
        age=(EditText)findViewById(R.id.year4);
        phone=(EditText)findViewById(R.id.phone4);
        house=(EditText)findViewById(R.id.house4);
        income=(EditText)findViewById(R.id.income4);
        city=(EditText)findViewById(R.id.city4);
        state=(EditText)findViewById(R.id.state4);
        final String states=state.getText().toString();
        final String inc=income.getText().toString();
        final String ag=age.getText().toString();
        Intent i1=getIntent();
        final String aaa=i1.getStringExtra("aa number");
        myref6=database.getReference().child("scheme1");

        final int agess=Integer.parseInt(ag);
        final int incomes=Integer.parseInt(inc);

        if(!states.isEmpty() && !inc.isEmpty() && !ag.isEmpty())
        {
            myref6.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(!dataSnapshot.child(aaa).exists()) {
                        if (states.equals("sikkim") && (agess >= 8 && agess < 56) && (incomes < 300000)) {
                            myref6.child(aaa).child("name").setValue(n.getText().toString());
                            myref6.child(aaa).child("fathername").setValue(fn.getText().toString());
                            myref6.child(aaa).child("aadhaar").setValue(aaa);
                            myref6.child(aaa).child("gender").setValue(gen.getText().toString());
                            myref6.child(aaa).child("age").setValue(age.getText().toString());
                            myref6.child(aaa).child("phone").setValue(phone.getText().toString());
                            myref6.child(aaa).child("housenumber").setValue(house.getText().toString());
                            myref6.child(aaa).child("income").setValue(income.getText().toString());
                            myref6.child(aaa).child("city").setValue(city.getText().toString());
                            myref6.child(aaa).child("State").setValue(state.getText().toString());
                            myref6.child(aaa).child("Status").setValue("Verification Under process");
                            myref6.child(aaa).child("Sanctioned").setValue("Processing");
                            Toast.makeText(verification.this, "Registered Successfullly", Toast.LENGTH_LONG).show();
                            n.setText("");
                            fn.setText("");
                            gen.setText("");
                            age.setText("");
                            city.setText("");
                            phone.setText("");
                            house.setText("");
                            income.setText("");
                            city.setText("");
                            state.setText("");
                        } else {
                            Toast.makeText(verification.this, "Criteria not satisfied", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(verification.this,"Already Exists",Toast.LENGTH_LONG).show(); n.setText("");

                    }
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else {
            Toast.makeText(verification.this,"Enter valid details",Toast.LENGTH_LONG).show();
        }


    }
}