package com.example.apnayojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class verification2 extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myref9;
    EditText n,fn,age,phone,house,income,city,state,chno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification2);
        database=FirebaseDatabase.getInstance();

    }
    public void lastclick1(View v){
        n=(EditText)findViewById(R.id.name41);
        fn=(EditText)findViewById(R.id.fname41);
        RadioGroup rbg=(RadioGroup) findViewById(R.id.radioGroup1);
        int selected=rbg.getCheckedRadioButtonId();
        final RadioButton gender=(RadioButton) findViewById(selected);
        final String gendervalue=gender.getText().toString();
        age=(EditText)findViewById(R.id.year41);
        phone=(EditText)findViewById(R.id.phone41);
        house=(EditText)findViewById(R.id.house41);
        income=(EditText)findViewById(R.id.income41);
        city=(EditText)findViewById(R.id.city41);
        state=(EditText)findViewById(R.id.state41);
        chno=(EditText)findViewById(R.id.no1);
        final String states=state.getText().toString();
        final String inc=income.getText().toString();
        final String no=chno.getText().toString();
        Intent i1=getIntent();
        final String aaa=i1.getStringExtra("aa number");
        myref9=database.getReference().child("scheme2");
        final int incomes=Integer.parseInt(inc);
        if(!states.isEmpty() && !inc.isEmpty() && !no.isEmpty()){
            myref9.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.child(aaa).exists()) {
                        if (states.equals("sikkim") && (incomes < 300000) && (!no.isEmpty())) {
                            myref9.child(aaa).child("name").setValue(n.getText().toString());
                            myref9.child(aaa).child("fathername").setValue(fn.getText().toString());
                            myref9.child(aaa).child("aadhaar").setValue(aaa);
                            myref9.child(aaa).child("gender").setValue(gendervalue);
                            myref9.child(aaa).child("age").setValue(age.getText().toString());
                            myref9.child(aaa).child("phone").setValue(phone.getText().toString());
                            myref9.child(aaa).child("housenumber").setValue(house.getText().toString());
                            myref9.child(aaa).child("income").setValue(income.getText().toString());
                            myref9.child(aaa).child("city").setValue(city.getText().toString());
                            myref9.child(aaa).child("State").setValue(state.getText().toString());
                            myref9.child(aaa).child("Status").setValue("Verification Under process");
                            myref9.child(aaa).child("Sanctioned").setValue("Processing");
                            myref9.child(aaa).child("Certificate No").setValue(chno.getText().toString());
                            Toast.makeText(verification2.this, "Registered Successfullly", Toast.LENGTH_LONG).show();
                            n.setText("");
                            fn.setText("");
                            age.setText("");
                            city.setText("");
                            gender.setChecked(false);
                            phone.setText("");
                            house.setText("");
                            income.setText("");
                            city.setText("");
                            state.setText("");
                            chno.setText("");
                        } else {
                            Toast.makeText(verification2.this, "Criteria not satisfied", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(verification2.this, "Already Exists", Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });
        }
        else {
            Toast.makeText(verification2.this,"Enter valid details",Toast.LENGTH_LONG).show();
        }
    }
}