package com.example.apnayojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class onefamilyverification extends AppCompatActivity {
    FirebaseDatabase database;
    EditText e1;
    TextView t1,t2,t3,t4,t5,t6;
    DatabaseReference myref888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=FirebaseDatabase.getInstance();
        e1=(EditText)findViewById(R.id.checkno);
        t1=(TextView)findViewById(R.id.textView7);
        t2=(TextView)findViewById(R.id.textView8);
        t3=(TextView)findViewById(R.id.textView9);
        t4=(TextView)findViewById(R.id.textView10);
        t5=(TextView)findViewById(R.id.textView11);
        t6=(TextView)findViewById(R.id.textView12);
        e1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    v.setBackgroundResource(R.drawable.focus_border_style);
                else
                    v.setBackgroundResource(R.drawable.lost_focus_style);
            }
        });
        setContentView(R.layout.activity_onefamilyverification);
    }
    public void clickednow(View view){
        final String check=e1.getText().toString();
        myref888=database.getReference().child("people");
        if(!check.isEmpty() && check.length()!=12){
            myref888.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(check).exists()){
                        String s1=dataSnapshot.child(check).child("name").getValue().toString();
                        String s2=dataSnapshot.child(check).child("fathername").getValue().toString();
                        String s3= dataSnapshot.child(check).child("age").getValue().toString();
                        String s4=dataSnapshot.child(check).child("houseno").getValue().toString();
                        String s5= dataSnapshot.child(check).child("city").getValue().toString();
                        String s6=dataSnapshot.child(check).child("state").getValue().toString();
                        t1.setText(s1);
                        t2.setText(s2);
                        t3.setText(s3);
                        t4.setText(s4);
                        t5.setText(s5);
                        t6.setText(s6);
                    }
                    else
                    {
                        Toast.makeText(onefamilyverification.this,"Aadhaar not in database",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
            Toast.makeText(onefamilyverification.this,"Enter Valid Number",Toast.LENGTH_LONG).show();
        }

    }

}
